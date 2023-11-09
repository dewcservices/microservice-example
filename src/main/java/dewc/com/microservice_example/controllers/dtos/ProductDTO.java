package dewc.com.microservice_example.controllers.dtos;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class ProductDTO extends ProductCreateDTO {
  @Getter
  @Setter
  @NotNull(message = "ID cannot be null")
  private String id;
}
