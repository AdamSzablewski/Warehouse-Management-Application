package com.adamszablewski.Warehouse.Management.Application.messages.repositories;

import com.adamszablewski.Warehouse.Management.Application.messages.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Optional<Conversation> findBySender(String sender);
}
