package com.example.conversation.controller;

import com.example.conversation.model.Conversation;
import com.example.conversation.repository.ConversationRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class ConversationController {

    private final ConversationRepository conversationRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper; // Ajouté
    @Value("${api.url}")
    private String apiUrl;

    @Autowired
    public ConversationController(ConversationRepository conversationRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.conversationRepository = conversationRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper; // Injecté
    }

    @GetMapping("/conversation")
    public String showConversationForm() {
        return "conversation";
    }

    @PostMapping("/conversation")
    public String submitMessage(String username, String message, Model model) {
        try {
        	System.out.println("Appel à l'URL : " + apiUrl);
            String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
            System.out.println("Réponse JSON brute : " + jsonResponse);
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            String reponse = jsonNode.get("contenu").asText(); // Extrait "contenu"
            System.out.println("Citation extraite : " + reponse);
            Conversation conversation = new Conversation(username, message, reponse);
            conversationRepository.save(conversation);
            model.addAttribute("username", username);
            model.addAttribute("message", message);
            model.addAttribute("reponse", reponse);
            model.addAttribute("timestamp", conversation.getTimestamp());
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération de la citation : " + e.getMessage());
            System.err.println("Erreur dans submitMessage : " + e.getMessage());
        }
        return "conversation";
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        List<String> users = conversationRepository.findAll().stream()
            .map(Conversation::getUsername)
            .distinct()
            .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/admin/conversations")
    public String showUserConversations(@RequestParam String username, Model model) {
        try {
            System.out.println("Recherche des conversations pour l'utilisateur : " + username);
            List<Conversation> conversations = conversationRepository.findByUsername(username);
            System.out.println("Conversations trouvées : " + conversations.size());
            for (Conversation conv : conversations) {
                System.out.println("Conversation : username=" + conv.getUsername() + ", message=" + conv.getMessage() + ", reponse=" + conv.getReponse() + ", timestamp=" + conv.getTimestamp());
            }
            model.addAttribute("conversations", conversations);
            model.addAttribute("selectedUser", username);
            List<String> users = conversationRepository.findAll().stream()
                .map(Conversation::getUsername)
                .distinct()
                .collect(Collectors.toList());
            System.out.println("Utilisateurs trouvés : " + users);
            model.addAttribute("users", users);
        } catch (Exception e) {
            System.err.println("Erreur dans showUserConversations : " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Erreur lors de la récupération des conversations : " + e.getMessage());
            model.addAttribute("conversations", Collections.emptyList()); // Liste vide pour éviter null
            model.addAttribute("selectedUser", username);
            model.addAttribute("users", conversationRepository.findAll().stream()
                .map(Conversation::getUsername)
                .distinct()
                .collect(Collectors.toList()));
        }
        return "admin";
    }
}