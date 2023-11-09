package dewc.com.microservice_example.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import dewc.com.microservice_example.controllers.dtos.ItemCreateDTO;
import dewc.com.microservice_example.controllers.dtos.ItemDTO;

//@Service
public class KafkaListeners {

    @KafkaListener(topics = "topicV1", containerFactory = "itemKafkaListenerContainerFactory")
    public void listenToTopicV1(@Payload ItemDTO itemV1) {
        // Process message
    }

    @KafkaListener(topics = "topicV2", containerFactory = "itemCreateKafkaListenerContainerFactory")
    public void listenToTopicV2(@Payload ItemCreateDTO itemV2) {
        // Process message
    }
}