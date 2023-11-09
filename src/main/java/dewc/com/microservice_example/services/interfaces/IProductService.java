package dewc.com.microservice_example.services.interfaces;

import dewc.com.microservice_example.controllers.dtos.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Flux<ProductDTO> getAllProducts();

    Mono<ProductDTO> getProductById(String id);

    Mono<ProductDTO> createProduct(ProductDTO dto);

    Mono<ProductDTO> updateProduct(String id, ProductDTO dto);

    Mono<Void> deleteProduct(String id);
}
