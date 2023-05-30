package com.adamszablewski.Warehouse.Management.Application.messages.helpers;

import com.adamszablewski.Warehouse.Management.Application.messages.Conversation;
import com.adamszablewski.Warehouse.Management.Application.messages.Message;
import com.adamszablewski.Warehouse.Management.Application.messages.repositories.ConversationRepository;
import com.adamszablewski.Warehouse.Management.Application.messages.repositories.MessageRepository;
import com.adamszablewski.Warehouse.Management.Application.messages.service.ConversationService;
import com.adamszablewski.Warehouse.Management.Application.messages.service.MessageService;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import com.adamszablewski.Warehouse.Management.Application.salesorders.helpers.SalesOrderHelper;
import com.adamszablewski.Warehouse.Management.Application.salesorders.soConfirmation.SalesOrderConfirmation;
import com.adamszablewski.Warehouse.Management.Application.salesorders.soConfirmation.SalesOrderConfirmationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class MessageSender {

    private final SalesOrderHelper salesOrderHelper;
    private final SalesOrderConfirmationRepository salesOrderConfirmationRepository;
    private final ConversationRepository conversationRepository;
    private final ConversationService conversationService;
    private final MessageRepository messageRepository;

    public SalesOrderConfirmation sendSalesOrderConfirmation(SalesOrder salesOrder){
        SalesOrderConfirmation salesOrderConfirmation = SalesOrderConfirmation.builder()
                .description("some text")
                .contactEmail(salesOrder.getContactEmail())
                .contactPerson(salesOrder.getContactPerson())
                .deliveryAddress(salesOrder.getDeliveryAdress())
                .company(salesOrder.getCompany())
                .buyerTrackingId(salesOrder.getBuyerTrackingId() == null ? "" : salesOrder.getBuyerTrackingId())
                .purchaseOrder(salesOrder)
                .deliveryDate(salesOrderHelper.calculateDeliveryDate(14))
                .build();

        salesOrderConfirmationRepository.save(salesOrderConfirmation);

        sendInternalMessageToVendor(salesOrder);
        //further api call here
        return salesOrderConfirmation;
    }

    public void sendInternalMessageToVendor(SalesOrder salesOrder){
        String orderId = salesOrder.getBuyerTrackingId() == null ? salesOrder.getBuyerTrackingId() : String.valueOf(salesOrder.getId());
        String messageContent = "";
        if(!salesOrder.isInDelivery() && salesOrder.isOrderRecieved()){
            messageContent = String.format("Hi, we have received your order %s," +
                            " as soon as the order is ready to ship we will notify you of " +
                            "the exact delivery date. For more information please check your inbox for our email."
                    , orderId);
        } else if (salesOrder.isInDelivery() && salesOrder.isOrderRecieved() && !salesOrder.isTransactionClosed()) {
            messageContent = String.format("Hi, your order %s, is now in delivery." +
                            "For more information please check your inbox for our email."
                    , orderId);
        }
        else if (salesOrder.isTransactionClosed()) {
            messageContent = String.format("Hi, your order %s, is now delivered." +
                            "For more information please check your inbox for our email."
                    , orderId);
        }

        Message message = Message.builder()
                .sender("automated")
                .receiver(salesOrder.getUsername())
                .message(messageContent)
                .build();
        createMessage(message, salesOrder.getUsername(), true);

    }

    public void sendInDeliveryMail(SalesOrder salesOrder){
        SalesOrderConfirmation salesOrderConfirmation = SalesOrderConfirmation.builder()
                .description("some text")
                .contactEmail(salesOrder.getContactEmail())
                .contactPerson(salesOrder.getContactPerson())
                .deliveryAddress(salesOrder.getDeliveryAdress())
                .company(salesOrder.getCompany())
                .buyerTrackingId(salesOrder.getBuyerTrackingId() == null ? "" : salesOrder.getBuyerTrackingId())
                .purchaseOrder(salesOrder)
                .deliveryDate(salesOrderHelper.calculateDeliveryDate(3))
                .build();

        sendInternalMessageToVendor(salesOrder);
        //further api call here
    }
    public void sendInDeliveryMail(SalesOrder salesOrder, LocalDate date){
        SalesOrderConfirmation salesOrderConfirmation = SalesOrderConfirmation.builder()
                .description("some text")
                .contactEmail(salesOrder.getContactEmail())
                .contactPerson(salesOrder.getContactPerson())
                .deliveryAddress(salesOrder.getDeliveryAdress())
                .company(salesOrder.getCompany())
                .buyerTrackingId(salesOrder.getBuyerTrackingId() == null ? "" : salesOrder.getBuyerTrackingId())
                .purchaseOrder(salesOrder)
                .deliveryDate(date)
                .build();

        sendInternalMessageToVendor(salesOrder);
        //further api call here
    }



    public void addMessageToConversation(Message message, Conversation conversation){
        List<Message> messages = conversation.getMessages();
        messages.add(message);
        messageRepository.save(message);
    }

    public Message createMessage(Message message, String user, boolean isSupport) {
        Optional<Conversation> optionalConversation =  conversationRepository.findBySender(user);
        Conversation conversation =  null;
        if (optionalConversation.isEmpty()){
            conversation =  conversationService.createConversationByVendor(user);
        }else {
            conversation = optionalConversation.get();
        }
        String sender = isSupport ? "support" : user;
        String receiver = isSupport ? user : "support";

        Message newMessage = Message.builder()
                .sender(sender)
                .receiver(receiver)
                .message(message.getMessage())
                .conversation(conversation)
                .dateSent(LocalDateTime.now())
                .build();
        addMessageToConversation(newMessage, conversation);

        return newMessage;
    }

}
