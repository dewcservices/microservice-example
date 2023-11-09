package dewc.com.microservice_example.repositories.interfaces;

import java.util.List;
import java.util.Optional;


public interface IItemRepository<T, idType> {
  List<T> findAll();
  Optional<T> findById(idType _id);
  T save(T item);
  void deleteById(idType id);
}
