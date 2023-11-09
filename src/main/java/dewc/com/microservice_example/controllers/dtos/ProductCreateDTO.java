package dewc.com.microservice_example.controllers.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class ProductCreateDTO {
  
  @NotBlank(message = "Name is required")
  @Length(max = 100, message = "Name must be less than 100 characters")
  @JsonProperty("name")
  private String name;

  @NotBlank(message = "Price is required")
  @Min(0)
  private Double price;
}
