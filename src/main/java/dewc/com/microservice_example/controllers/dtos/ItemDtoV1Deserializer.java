package dewc.com.microservice_example.controllers.dtos;

import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemDtoV1Deserializer implements Deserializer<Item> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ItemDtoV1 deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Item.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize Item Dto V1", e);
        }
    }
}