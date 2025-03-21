package com.yourcaryourway.pocmessagerie.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("messages")
public class Message {
    @Id
    private UUID id;
    private UUID idExpediteur;
    private UUID idDestinataire;
    private String contenu;
    private LocalDateTime creeLe;
    private LocalDateTime modifieLe;

    // Constructeur vide (obligatoire pour Spring)
    public Message() {}

    // Constructeur sans ID (généré par la base)
    public Message(UUID idExpediteur, UUID idDestinataire, String contenu) {
        this.idExpediteur = idExpediteur;
        this.idDestinataire = idDestinataire;
        this.contenu = contenu;
    }

    // Getters et Setters
    public UUID getId() {
        return id;
    }

    public UUID getIdExpediteur() {
        return idExpediteur;
    }

    public void setIdExpediteur(UUID idExpediteur) {
        this.idExpediteur = idExpediteur;
    }

    public UUID getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(UUID idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getCreeLe() {
        return creeLe;
    }

    public LocalDateTime getModifieLe() {
        return modifieLe;
    }

    public void setCreeLe(LocalDateTime creeLe) {
        this.creeLe = creeLe;
    }

    public void setModifieLe(LocalDateTime modifieLe) {
        this.modifieLe = modifieLe;
    }
}
