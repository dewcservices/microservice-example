package dewc.com.microservice_example.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import dewc.com.microservice_example.repositories.entities.Item;

@Service
public class ItemRepositoryMock implements ItemRepository {


  private Map<String, Item> mockRepo;
  public ItemRepositoryMock(){
    mockRepo = new HashMap<>();
    for(int i = 1; i < 11; i++){
      var index = String.valueOf(i);
      mockRepo.put(index, new Item(index, String.format("item {0}", index), String.format("this is item {0}", index)));
    }
  }

  @Override
  public List<Item> findAll() {
     return mockRepo.values().stream().collect(Collectors.toList());
  }

  @Override
  public Optional<Item> findById(String _id) {

    return Optional.ofNullable(mockRepo.get(_id));
  }

  @Override
  public Item save(Item item) {
    if(mockRepo.containsKey(item.getId())){
      throw new DuplicateKeyException("Key already exists");
    }
    // for update a hashmap will overwrite
    return mockRepo.put(item.getId(), item);
  }

  @Override
  public void deleteById(String id) {
    if(mockRepo.containsKey(id)){
      mockRepo.remove(id);
    }
  }
  
}
