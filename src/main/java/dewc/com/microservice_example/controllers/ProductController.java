package dewc.com.microservice_example.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dewc.com.microservice_example.controllers.dtos.ProductDTO;
import dewc.com.microservice_example.services.interfaces.IProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/products/")
public class ProductController {

    private final IProductService service;

    public ProductController(@Qualifier("productService") IProductService service) {
        this.service = service;
    }

    @GetMapping
    // flux returns http 200
    public Flux<ProductDTO> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductDTO>> getProductById(@PathVariable String id) {
        return service.getProductById(id)
                .map(product -> ResponseEntity.ok(product))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<ProductDTO>> createProduct(@RequestBody ProductDTO dto) {
        return service.createProduct(dto)
                .map(product -> ResponseEntity
                        .created(URI.create("/products/" + product.getId()))
                        .body(product));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductDTO>> updateProduct(@PathVariable String id, @RequestBody ProductDTO dto) {
        return service.updateProduct(id, dto)
                .map(product -> ResponseEntity.ok(product))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String id) {
        return service.deleteProduct(id)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}

