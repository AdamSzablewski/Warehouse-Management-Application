package com.adamszablewski.Warehouse.Management.Application.messages.service;

import com.adamszablewski.Warehouse.Management.Application.messages.Message;
import com.adamszablewski.Warehouse.Management.Application.messages.helpers.MessageSender;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MessageService {
   private final MessageSender messageSender;

    public ResponseEntity<Message> createMessageBySender(Message message, String user) {
        Message newMessagge =  messageSender.createMessage(message, user, false);

        return ResponseEntity.status(HttpStatus.CREATED).body(newMessagge);
    }

    public ResponseEntity<Message> createMessageBySupport(Message message, String user) {
        Message newMessagge =  messageSender.createMessage(message, user, true);

        return ResponseEntity.status(HttpStatus.CREATED).body(newMessagge);
    }

}
