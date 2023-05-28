package com.adamszablewski.Warehouse.Management.Application.messages.controllers.messagesControllers;

import com.adamszablewski.Warehouse.Management.Application.messages.Message;
import com.adamszablewski.Warehouse.Management.Application.messages.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class MessageControllerPOST {

    MessageService messageService;

    @PostMapping("/conversations/send/vendor/{vendor}")
    public ResponseEntity<Message> createConversationByVendor(@RequestBody Message message, @PathVariable String vendor){
        return messageService.createMessageBySender(message, vendor);
    }

    @PostMapping("/conversations/send/support/{vendor}")
    public ResponseEntity<Message> createConversationBySupport(@RequestBody Message message, @PathVariable String vendor){
        return messageService.createMessageBySupport(message, vendor);
    }
}
