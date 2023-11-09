package dewc.com.microservice_example.services.interfaces;

import java.util.List;
import java.util.Optional;

import dewc.com.microservice_example.repositories.entities.Thing;

public interface IThingService {

      public List<Thing> getAllThings();

    public Optional<Thing> getThingById(long id);

    public Thing createOrUpdateThing(Thing item);

    public void deleteThing(long id);
}
