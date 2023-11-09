package dewc.com.microservice_example.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThingCreateDTO {

  @NotBlank(message = "Name is required")
  @Length(max = 100, message = "Name must be less than 100 characters")
  @JsonProperty("name")
  private String name = "";

  @NotBlank(message = "Description is required")
  @Length(max = 500, message = "Description must be less than 500 characters")
  @JsonProperty("description")
  private String description = "";
}
