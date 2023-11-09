package dewc.com.microservice_example.repositories.entities;

import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Document(collection = "item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    private String id;
    private String label;
    private String description;

    // Constructors, getters, and setters
}