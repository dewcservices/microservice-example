package dewc.com.microservice_example.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import dewc.com.microservice_example.controllers.dtos.ItemCreateDTO;
import dewc.com.microservice_example.controllers.dtos.ItemDTO;

//@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private ConsumerFactory<Object, Object> defaultConsumerFactory;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ItemDTO> itemKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ItemDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(itemConsumerFactory());
        return factory;
    }

    public ConsumerFactory<String, ItemDTO> itemConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                defaultConsumerFactory.getConfigurationProperties(),
                new StringDeserializer(),
                new ItemDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ItemCreateDTO> itemCreateKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ItemCreateDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(itemCreateConsumerFactory());
        return factory;
    }

    public ConsumerFactory<String, ItemCreateDTO> itemCreateConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                defaultConsumerFactory.getConfigurationProperties(),
                new StringDeserializer(),
                new ItemCreateDeserializer());
    }

    // Custom deserializers
    public static class ItemDeserializer extends JsonDeserializer<ItemDTO> {}
    public static class ItemCreateDeserializer extends JsonDeserializer<ItemCreateDTO> {}
}