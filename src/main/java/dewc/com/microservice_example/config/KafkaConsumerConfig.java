package dewc.com.microservice_example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private ConsumerFactory<Object, Object> defaultConsumerFactory;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ItemV1> itemV1KafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ItemV1> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(itemV1ConsumerFactory());
        return factory;
    }

    public ConsumerFactory<String, ItemV1> itemV1ConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                defaultConsumerFactory.getConfigurationProperties(),
                new StringDeserializer(),
                new ItemV1Deserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ItemV2> itemV2KafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ItemV2> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(itemV2ConsumerFactory());
        return factory;
    }

    public ConsumerFactory<String, ItemV2> itemV2ConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                defaultConsumerFactory.getConfigurationProperties(),
                new StringDeserializer(),
                new ItemV2Deserializer());
    }

    // Custom deserializers
    public static class ItemV1Deserializer extends JsonDeserializer<ItemV1> {}
    public static class ItemV2Deserializer extends JsonDeserializer<ItemV2> {}
}