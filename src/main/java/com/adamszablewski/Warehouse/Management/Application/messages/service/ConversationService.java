package com.adamszablewski.Warehouse.Management.Application.messages.service;

import com.adamszablewski.Warehouse.Management.Application.messages.Conversation;
import com.adamszablewski.Warehouse.Management.Application.messages.Message;
import com.adamszablewski.Warehouse.Management.Application.messages.repositories.ConversationRepository;
import com.adamszablewski.Warehouse.Management.Application.users.UserInfo;
import com.adamszablewski.Warehouse.Management.Application.users.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ConversationService {
    ConversationRepository conversationRepository;

    UserInfoRepository userInfoRepository;
    public Optional<Conversation> getConversationByVendor(String vendor) {
        Optional<Conversation> optionalConversation = conversationRepository.findBySender(vendor);
       return optionalConversation;

    }

    public ResponseEntity<String> createConversationByVendor(String vendor) {
        if (conversationRepository.findBySender(vendor).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A conversation with this user already exists");
        }
        Conversation conversation = Conversation.builder()
                .sender(vendor)
                .messages(new ArrayList<Message>())
                .replier("support")
                .build();

        conversationRepository.save(conversation);

        return ResponseEntity.status(HttpStatus.CREATED).body("Conversation created");
    }
}
