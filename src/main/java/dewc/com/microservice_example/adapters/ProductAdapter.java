package dewc.com.microservice_example.adapters;

import dewc.com.microservice_example.controllers.dtos.ProductDTO;
import dewc.com.microservice_example.repositories.entities.Product;

public class ProductAdapter {
      public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return product;
    }
  
}
