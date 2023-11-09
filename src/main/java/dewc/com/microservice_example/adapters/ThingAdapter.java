package dewc.com.microservice_example.adapters;


import dewc.com.microservice_example.controllers.dtos.ThingCreateDTO;
import dewc.com.microservice_example.controllers.dtos.ThingDTO;
import dewc.com.microservice_example.repositories.entities.Product;
import dewc.com.microservice_example.repositories.entities.Thing;

public class ThingAdapter {
    
    public static ThingDTO toDTO(Thing product) {
        ThingDTO dto = new ThingDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        return dto;
    }

    public static Thing toEntity(ThingDTO dto) {
        Thing thing = new Thing();
        thing.setId(dto.getId());
        thing.setName(dto.getName());
        thing.setDescription(dto.getDescription());
        return thing;
    }

    public static Thing toEntity(ThingCreateDTO dto) {
        Thing thing = new Thing();
        thing.setName(dto.getName());
        thing.setDescription(dto.getDescription());
        return thing;
    }
}
