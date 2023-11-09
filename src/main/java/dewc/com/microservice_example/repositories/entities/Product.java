package dewc.com.microservice_example.repositories.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Product {
  @Id
  private String id;
  private String name;
  private Double price;
}
