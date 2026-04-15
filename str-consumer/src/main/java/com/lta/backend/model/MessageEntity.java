package com.lta.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime receivedAt;

    public MessageEntity() {}

    public MessageEntity(String content) {
        this.content = content;
        this.receivedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getContent() { return content; }
    public LocalDateTime getReceivedAt() { return receivedAt; }
}
//linea para actualizar en GitHub