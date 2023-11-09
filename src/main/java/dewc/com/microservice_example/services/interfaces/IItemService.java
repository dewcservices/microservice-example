package dewc.com.microservice_example.services.interfaces;

import java.util.List;
import java.util.Optional;

import dewc.com.microservice_example.repositories.entities.Item;

public interface IItemService {
    
    public List<Item> getAllItems();

    public Optional<Item> getItemById(String id);

    public Item createOrUpdateItem(Item item);

    public void deleteItem(String id);
}