package dewc.com.microservice_example.repositories;

// import org.springframework.data.mongodb.repository.MongoRepository;

import dewc.com.microservice_example.repositories.entities.Item;
import dewc.com.microservice_example.repositories.interfaces.IItemRepository;

public interface ItemRepository extends IItemRepository<Item, String> { // MongoRepository<Item, String> {

}