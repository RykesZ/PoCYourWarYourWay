package com.yourcaryourway.pocmessagerie.controller;

import com.yourcaryourway.pocmessagerie.model.Message;
import com.yourcaryourway.pocmessagerie.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public List<Message> getMessages(
            @RequestParam UUID idExpediteur,
            @RequestParam UUID idDestinataire) {
        return messageService.getMessages(idExpediteur, idDestinataire);
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message savedMessage = messageService.sendMessage(message.getIdExpediteur(), message.getIdDestinataire(), message.getContenu());
        return ResponseEntity.ok(message);
    }
}
