package dewc.com.microservice_example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

package dewc.com.microservice_example.services.interfaces;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceV1 {
    
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(String id) {
        return itemRepository.findById(id);
    }

    public Item createOrUpdateItem(Item item) {
        try {
            return itemRepository.save(item);
        } catch (DuplicateKeyException e) {
            // Handle the case when the save operation is attempted with a duplicate ID
            throw new RuntimeException("An item with the same ID already exists.");
        }
    }

    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }
}