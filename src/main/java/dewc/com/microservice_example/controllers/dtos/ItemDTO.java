package dewc.com.microservice_example.controllers.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


public class ItemDTO extends ItemCreateDTO {

    public ItemDTO(String id, String label, String description){
        super(label, description);
        this.id = id;
    }

    @Getter
    @Setter
    @NotNull(message = "ID cannot be null")
    private String id;

}