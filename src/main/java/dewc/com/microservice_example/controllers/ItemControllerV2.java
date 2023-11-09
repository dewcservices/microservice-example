package dewc.com.microservice_example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dewc.com.microservice_example.adapters.ItemAdapter;
import dewc.com.microservice_example.controllers.dtos.ItemCreateDTO;
import dewc.com.microservice_example.controllers.dtos.ItemDTO;
import dewc.com.microservice_example.services.interfaces.IItemService;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v2/items")
public class ItemControllerV2{

    private final IItemService itemService;

    @Autowired
    public ItemControllerV2(@Qualifier("itemService") IItemService itemService) {
        this.itemService = itemService;
    }

    // You could make a choice to leave this on api/v1
    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
                var itemDTOs = itemService.getAllItems().stream()
                    .map(ItemAdapter::toDTO)
                    .collect(Collectors.toList());
        return ResponseEntity.ok(itemDTOs);
    }

    // You could make a choice to leave this on api/v1
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable String id) {
        return itemService.getItemById(id)
        .map(ItemAdapter::toDTO)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@Valid @RequestBody ItemCreateDTO itemDTO) {
        var item = itemService.createOrUpdateItem(ItemAdapter.toEntity(itemDTO));
        return ResponseEntity.ok(ItemAdapter.toDTO(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable String id, @RequestBody ItemDTO itemDTO) {
        itemDTO.setId(id);
        var item = itemService.createOrUpdateItem(ItemAdapter.toEntity(itemDTO));
        return ResponseEntity.ok(ItemAdapter.toDTO(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}