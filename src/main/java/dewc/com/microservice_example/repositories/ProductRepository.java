package dewc.com.microservice_example.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import dewc.com.microservice_example.repositories.entities.Product;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    // Custom reactive query methods can be defined here
}