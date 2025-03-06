package com.example.API.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Citation")  
public class Quote {
    
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String contenu;
    
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
    
}
