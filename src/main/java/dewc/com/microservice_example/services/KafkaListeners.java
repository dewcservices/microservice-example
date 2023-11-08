package dewc.com.microservice_example.services;

@Service
public class KafkaListeners {

    @KafkaListener(topics = "topicV1", containerFactory = "itemV1KafkaListenerContainerFactory")
    public void listenToTopicV1(@Payload ItemV1 itemV1) {
        // Process message
    }

    @KafkaListener(topics = "topicV2", containerFactory = "itemV2KafkaListenerContainerFactory")
    public void listenToTopicV2(@Payload ItemV2 itemV2) {
        // Process message
    }
}