package com.adamszablewski.Warehouse.Management.Application.messages.controllers.conversationControllers;

import com.adamszablewski.Warehouse.Management.Application.messages.Conversation;
import com.adamszablewski.Warehouse.Management.Application.messages.service.ConversationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class ConversationControllerGET {

    private final ConversationService conversationService;

    @GetMapping("/conversations/user/{user}")
    @PreAuthorize("hasAuthority('ADMIN') or principal.username == #user")
    public Optional<Conversation> getConversationByVendor(@PathVariable String user){
        return conversationService.getConversationByVendor(user);
    }

   
}
