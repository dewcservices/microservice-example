package dewc.com.microservice_example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dewc.com.microservice_example.adapters.ItemAdapter;
import dewc.com.microservice_example.controllers.dtos.ItemDTO;
import dewc.com.microservice_example.services.interfaces.IItemService;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/items")
public class ItemControllerV1 {

 private final IItemService itemService;

    public ItemControllerV1(@Qualifier("itemService") IItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        var itemDTOs = itemService.getAllItems().stream()
                    .map(ItemAdapter::toDTO)
                    .collect(Collectors.toList());
        return ResponseEntity.ok(itemDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable String id) {
        return itemService.getItemById(id)
                .map(ItemAdapter::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@Valid @RequestBody ItemDTO itemDTO) {
        // you could return created with a location of the resource in the header
        return ResponseEntity.ok(createOrUpdateItem(itemDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable String id, @Valid  @RequestBody ItemDTO itemDTO) {
        itemDTO.setId(id);
        return ResponseEntity.ok(createOrUpdateItem(itemDTO));
    }

    private ItemDTO createOrUpdateItem(ItemDTO itemDTO){
        var item = itemService.createOrUpdateItem(ItemAdapter.toEntity(itemDTO));
        return ItemAdapter.toDTO(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}