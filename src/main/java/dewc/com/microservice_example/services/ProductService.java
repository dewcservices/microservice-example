package dewc.com.microservice_example.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dewc.com.microservice_example.adapters.ProductAdapter;
import dewc.com.microservice_example.controllers.dtos.ProductDTO;
import dewc.com.microservice_example.repositories.ProductRepository;
import dewc.com.microservice_example.services.interfaces.IProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Qualifier("productService")
public class ProductService implements IProductService {
      private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Flux<ProductDTO> getAllProducts() {
        return repository.findAll().map(ProductAdapter::toDTO);
    }

    public Mono<ProductDTO> getProductById(String id) {
        return repository.findById(id).map(ProductAdapter::toDTO);
    }

    public Mono<ProductDTO> createProduct(ProductDTO dto) {
        return repository.save(ProductAdapter.toEntity(dto)).map(ProductAdapter::toDTO);
    }

    public Mono<ProductDTO> updateProduct(String id, ProductDTO dto) {
        return repository.findById(id)
                .flatMap(product -> {
                    product.setName(dto.getName());
                    product.setPrice(dto.getPrice());
                    return repository.save(product);
                })
                .map(ProductAdapter::toDTO);
    }

    public Mono<Void> deleteProduct(String id) {
        return repository.deleteById(id);
    }
  
}
