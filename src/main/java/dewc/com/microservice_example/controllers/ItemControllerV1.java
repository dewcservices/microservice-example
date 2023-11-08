package dewc.com.microservice_example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemControllerV1 {

 private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemDtoV1> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDtoV1> getItemById(@PathVariable String id) {
        return itemService.getItemById(id)
                .map(item -> new ItemDtoV1(item.getId(), item.getLabel(), item.getDescription()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemDtoV1> createItem(@RequestBody ItemDtoV1 itemDTO) {
        Item item = new Item(); // convert from ItemDTO to Item entity
        // Set properties from DTO to entity
        item.setLabel(itemDTO.getLabel());
        item.setDescription(itemDTO.getDescription());
        
        Item createdItem = itemService.createOrUpdateItem(item);
        return itemService.createOrUpdateItem(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDtoV1> updateItem(@PathVariable String id, @RequestBody ItemDtoV1 itemDTO) {
        Item item = new Item(); // convert from ItemDTO to Item entity
        // Set properties from DTO to entity
        item.setId(id);
        item.setLabel(itemDTO.getLabel());
        item.setDescription(itemDTO.getDescription());
        return itemService.createOrUpdateItem(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}