package dewc.com.microservice_example.controllers.dtos;

import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemDTODeserializer implements Deserializer<ItemDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ItemDTO deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, ItemDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize Item Dto V1", e);
        }
    }
}