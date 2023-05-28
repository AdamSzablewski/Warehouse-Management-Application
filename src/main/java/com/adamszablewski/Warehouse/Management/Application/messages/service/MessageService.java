package com.adamszablewski.Warehouse.Management.Application.messages.service;

import com.adamszablewski.Warehouse.Management.Application.messages.Conversation;
import com.adamszablewski.Warehouse.Management.Application.messages.Message;
import com.adamszablewski.Warehouse.Management.Application.messages.repositories.ConversationRepository;
import com.adamszablewski.Warehouse.Management.Application.messages.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MessageService {

   MessageRepository messageRepository;

   ConversationService conversationService;
   ConversationRepository conversationRepository;

    public ResponseEntity<Message> createMessageBySender(Message message, String customer) {
       Optional<Conversation> optionalConversation =  conversationRepository.findBySender(customer);
       if (optionalConversation.isEmpty()){
           conversationService.createConversationByVendor(customer);
       }
        Conversation conversation =  conversationRepository.findBySender(customer).get();
        Message newMessage = Message.builder()
                .sender(customer)
                .receiver("support")
                .message(message.getMessage())
                .conversation(conversation)
                .dateSent(LocalDateTime.now())
                .build();
         addMessageToConversation(newMessage, conversation);

         return ResponseEntity.status(HttpStatus.CREATED).body(newMessage);

    }

    private void addMessageToConversation(Message message, Conversation conversation){
        List<Message> messages = conversation.getMessages();
        messages.add(message);
        messageRepository.save(message);
    }

    public ResponseEntity<Message> createMessageBySupport(Message message, String vendor) {
        Optional<Conversation> optionalConversation =  conversationRepository.findBySender(vendor);
        if (optionalConversation.isEmpty()){
            conversationService.createConversationByVendor(vendor);
        }
        Conversation conversation =  conversationRepository.findBySender(vendor).get();
        Message newMessage = Message.builder()
                .sender("support")
                .receiver(vendor)
                .message(message.getMessage())
                .conversation(conversation)
                .dateSent(LocalDateTime.now())
                .build();
        addMessageToConversation(newMessage, conversation);

        return ResponseEntity.status(HttpStatus.CREATED).body(newMessage);
    }
}
