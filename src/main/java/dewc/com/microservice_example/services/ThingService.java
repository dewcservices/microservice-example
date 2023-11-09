package dewc.com.microservice_example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import dewc.com.microservice_example.repositories.ThingRepository;
import dewc.com.microservice_example.repositories.entities.Thing;
import dewc.com.microservice_example.services.interfaces.IThingService;

@Service
public class ThingService implements IThingService {

  private final ThingRepository repository;

  public ThingService(ThingRepository repository){
    this.repository = repository;
  }

  @Override
  public List<Thing> getAllThings() {
    return repository.findAll();
  }

  @Override
  public Optional<Thing> getThingById(long id) {
    return repository.findById(id);
  }

  @Override
  public Thing createOrUpdateThing(Thing item) {
            try {
            return repository.save(item);
        } catch (DuplicateKeyException e) {
            // Handle the case when the save operation is attempted with a duplicate ID
            throw new RuntimeException("An item with the same ID already exists.");
        }
  }

  @Override
  public void deleteThing(long id) {
    repository.deleteById(id);
  }
  
}
