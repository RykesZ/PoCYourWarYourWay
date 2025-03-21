package com.yourcaryourway.pocmessagerie.service;

import com.yourcaryourway.pocmessagerie.model.Message;
import com.yourcaryourway.pocmessagerie.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessages(UUID idExpediteur, UUID idDestinataire) {
        return messageRepository.findByIdExpediteurAndIdDestinataireOrIdExpediteurAndIdDestinataireOrderByCreeLeAsc(idExpediteur, idDestinataire);

        // Trier par date de création (les plus anciens en premier)
        /*return messages.stream()
                .sorted((m1, m2) -> m1.getCreeLe().compareTo(m2.getCreeLe()))
                .collect(Collectors.toList());*/
    }

    public Message sendMessage(UUID idExpediteur, UUID idDestinataire, String contenu) {
        Message message = new Message(idExpediteur, idDestinataire, contenu);
        if (message.getCreeLe() == null) {
            message.setCreeLe(LocalDateTime.now()); // Set the creation date if not provided
        }
        message.setModifieLe(LocalDateTime.now()); // Always update the modification date
        return messageRepository.save(message);
    }
}
