package dewc.com.microservice_example.repositorities.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "item")
public class Item {
    @Id
    private String id;
    private String label;
    private String description;

    // Constructors, getters, and setters
}