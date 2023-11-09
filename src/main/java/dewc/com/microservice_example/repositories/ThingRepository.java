package dewc.com.microservice_example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dewc.com.microservice_example.repositories.entities.Item;
import dewc.com.microservice_example.repositories.entities.Thing;

public interface ThingRepository extends JpaRepository<Thing, Long> {
  public Optional<Item> findByName(String name);
}
