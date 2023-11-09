package dewc.com.microservice_example.adapters;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import dewc.com.microservice_example.controllers.dtos.ItemCreateDTO;
import dewc.com.microservice_example.controllers.dtos.ItemDTO;
import dewc.com.microservice_example.repositories.entities.Item;


public class ItemAdapter {

    public static ItemDTO toDTO(Item item) {
        if (item == null) {
            return null;
        }
        return new ItemDTO(item.getId(), item.getLabel(), item.getDescription());
    }

    public static Item toEntity(ItemDTO itemDTO) {
        if (itemDTO == null) {
            return null;
        }
        Item item = new Item();
        item.setId(itemDTO.getId()); // Be cautious with setting ID when converting to entity for create operations
        item.setLabel(itemDTO.getLabel());
        item.setDescription(itemDTO.getDescription());
        return item;
    }

        public static Item toEntity(ItemCreateDTO itemDTO) {
        if (itemDTO == null) {
            return null;
        }
        Item item = new Item();
        item.setLabel(itemDTO.getLabel());
        item.setDescription(itemDTO.getDescription());
        return item;
    }

    // Adapter methods for lists
    public static List<ItemDTO> toDTOList(List<Item> items) {
        if (items == null) {
            return Collections.emptyList();
        }
        return items.stream().map(ItemAdapter::toDTO).collect(Collectors.toList());
    }

    public static List<Item> toEntityList(List<ItemDTO> itemDTOs) {
        if (itemDTOs == null) {
            return Collections.emptyList();
        }
        return itemDTOs.stream().map(ItemAdapter::toEntity).collect(Collectors.toList());
    }
}