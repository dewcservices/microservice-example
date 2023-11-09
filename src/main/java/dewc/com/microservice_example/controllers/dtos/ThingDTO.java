package dewc.com.microservice_example.controllers.dtos;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class ThingDTO extends ThingCreateDTO {

    @Getter
    @Setter
    @NotNull(message = "ID cannot be null")
    private Long id;


}
