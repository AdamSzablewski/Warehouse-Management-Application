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
    private final ConversationRepository conversationRepository;
    public Optional<Conversation> getConversationByVendor(String vendor) {
        Optional<Conversation> optionalConversation = conversationRepository.findBySender(vendor);
       return optionalConversation;

    }

    public Conversation createConversationByVendor(String user) {
        Optional<Conversation> optionalConversation =  conversationRepository.findBySender(user);
        if (optionalConversation.isPresent()){
            return optionalConversation.get();
        }
        Conversation conversation = Conversation.builder()
                .sender(user)
                .messages(new ArrayList<Message>())
                .replier("support")
                .build();

        conversationRepository.save(conversation);

        return conversation;
    }
}
