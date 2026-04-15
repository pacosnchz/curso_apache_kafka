package com.lta.backend.listeners;

import com.lta.backend.model.MessageEntity;
import com.lta.backend.repository.MessageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StrConsumerListener {

    @Autowired
    private MessageRepository messageRepository;

    @KafkaListener(groupId = "group-1",
            topicPartitions = @TopicPartition(topic = "str-topic", partitions = {"0"}),
            containerFactory = "validMessageContainerFactory")
    public void listener1(String message) {
        log.info("LISTENER1 ::: Recibiendo un mensaje {}", message);
        MessageEntity entity = new MessageEntity(message);
        messageRepository.save(entity);
        log.info("LISTENER1 ::: Mensaje persistido en base de datos con id: {}", entity.getId());
    }

    @KafkaListener(groupId = "group-1",
            topicPartitions = @TopicPartition(topic = "str-topic", partitions = {"1"}),
            containerFactory = "validMessageContainerFactory")
    public void listener2(String message) {
        log.info("LISTENER2 ::: Recibiendo un mensaje {}", message);
        MessageEntity entity = new MessageEntity(message);
        messageRepository.save(entity);
        log.info("LISTENER2 ::: Mensaje persistido en base de datos con id: {}", entity.getId());
    }

    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory")
    public void listener3(String message) {
        log.info("LISTENER3 ::: Recibiendo un mensaje {}", message);
        MessageEntity entity = new MessageEntity(message);
        messageRepository.save(entity);
        log.info("LISTENER3 ::: Mensaje persistido en base de datos con id: {}", entity.getId());
    }
}