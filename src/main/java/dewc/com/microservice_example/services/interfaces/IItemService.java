package dewc.com.microservice_example.services.interfaces;

public class IItemService {
    
    public List<Item> getAllItems();

    public Optional<Item> getItemById(String id);

    public Item createOrUpdateItem(Item item);

    public void deleteItem(String id);
}