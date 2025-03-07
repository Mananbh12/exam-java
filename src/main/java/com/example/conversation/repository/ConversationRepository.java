package com.example.conversation.repository;


import com.example.conversation.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
	List<Conversation> findByUsername(String username);
}