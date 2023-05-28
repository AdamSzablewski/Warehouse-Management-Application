package com.adamszablewski.Warehouse.Management.Application.messages.controllers.conversationControllers;

import com.adamszablewski.Warehouse.Management.Application.messages.Conversation;
import com.adamszablewski.Warehouse.Management.Application.messages.service.ConversationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class ConversationControllerGET {

    ConversationService conversationService;

    @GetMapping("/conversations/vendor/{vendor}")
    public Optional<Conversation> getConversationByVendor(@PathVariable String vendor){
        return conversationService.getConversationByVendor(vendor);
    }
}
