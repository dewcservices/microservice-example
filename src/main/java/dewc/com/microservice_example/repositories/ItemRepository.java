package dewc.com.microservice_example.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import dewc.com.microservice_example.repositories.entities.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

}