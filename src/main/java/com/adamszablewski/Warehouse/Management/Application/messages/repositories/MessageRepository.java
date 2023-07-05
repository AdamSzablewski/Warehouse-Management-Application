package com.adamszablewski.Warehouse.Management.Application.messages.repositories;

import com.adamszablewski.Warehouse.Management.Application.messages.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findBySender(String sender);
    Optional<Message> findByReceiver(String sender);
}
