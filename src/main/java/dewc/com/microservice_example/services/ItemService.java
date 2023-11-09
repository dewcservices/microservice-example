package dewc.com.microservice_example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.dao.DuplicateKeyException;
import dewc.com.microservice_example.repositories.ItemRepository;
import dewc.com.microservice_example.repositories.entities.Item;
import dewc.com.microservice_example.services.interfaces.IItemService;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("itemService")
public class ItemService implements IItemService {
    
    private final ItemRepository itemRepository;

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