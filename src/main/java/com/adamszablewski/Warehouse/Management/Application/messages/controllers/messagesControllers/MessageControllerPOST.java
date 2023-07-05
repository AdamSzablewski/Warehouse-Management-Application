package com.adamszablewski.Warehouse.Management.Application.messages.controllers.messagesControllers;

import com.adamszablewski.Warehouse.Management.Application.messages.Message;
import com.adamszablewski.Warehouse.Management.Application.messages.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/conversations/send")
public class MessageControllerPOST {

    MessageService messageService;

    @PostMapping("/user/{user}")
    @PreAuthorize("principal.username == #user")
    public ResponseEntity<Message> createConversationByVendor(@RequestBody Message message, @PathVariable String user){
        return messageService.createMessageBySender(message, user);
    }

    @PostMapping("/support/{user}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public ResponseEntity<Message> createConversationBySupport(@RequestBody Message message, @PathVariable String user){
        return messageService.createMessageBySupport(message, user);
    }
}
