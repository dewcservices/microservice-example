package dewc.com.microservice_example.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dewc.com.microservice_example.adapters.ThingAdapter;
import dewc.com.microservice_example.controllers.dtos.ThingCreateDTO;
import dewc.com.microservice_example.controllers.dtos.ThingDTO;
import dewc.com.microservice_example.services.interfaces.IThingService;

@RestController
@RequestMapping("/api/v1/things")
public class ThingController {

  private final IThingService service;


    public ThingController(IThingService service) {
        this.service = service;
    }

        // You could make a choice to leave this on api/v1
    @GetMapping
    public ResponseEntity<List<ThingDTO>> getAllItems() {
        var thingDTOs = service.getAllThings().stream()
            .map(ThingAdapter::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(thingDTOs);
    }

    // You could make a choice to leave this on api/v1
    @GetMapping("/{id}")
    public ResponseEntity<ThingDTO> getItemById(@PathVariable long id) {
        return service.getThingById(id)
        .map(ThingAdapter::toDTO)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ThingDTO> createItem(@Valid @RequestBody ThingCreateDTO itemDTO) {
        var item = service.createOrUpdateThing(ThingAdapter.toEntity(itemDTO));
        return ResponseEntity.ok(ThingAdapter.toDTO(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThingDTO> updateItem(@PathVariable long id, @RequestBody ThingDTO itemDTO) {
        itemDTO.setId(id);
        var item = service.createOrUpdateThing(ThingAdapter.toEntity(itemDTO));
        return ResponseEntity.ok(ThingAdapter.toDTO(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        service.deleteThing(id);
        return ResponseEntity.ok().build();
    }

}
