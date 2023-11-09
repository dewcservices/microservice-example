package dewc.com.microservice_example.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ItemCreateDTO {

    @NotBlank(message = "Label is required")
    @Length(max = 100, message = "Label must be less than 100 characters")
    @JsonProperty("label") // This is actually redundant unless you want to change the name
    private String label;

    @NotBlank(message = "Description is required")
    @Length(max = 500, message = "Description must be less than 500 characters")
    @JsonProperty("description") // This is actually redundant unless you want to change the name
    private String description;

}