package com.adamszablewski.Warehouse.Management.Application.messages.controllers.conversationControllers;

import com.adamszablewski.Warehouse.Management.Application.messages.Conversation;
import com.adamszablewski.Warehouse.Management.Application.messages.service.ConversationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ConversationControllerPOST {

    ConversationService conversationService;

    @PostMapping("/conversations/create/customer/{customer}")
    public ResponseEntity<String> createConversationByVendor(@PathVariable String customer){
        return conversationService.createConversationByVendor(customer);
    }
}
