# microservice-example

The repo aims to detail the minimum expected development features of a microservice.

This repo implements the features using springboot, however they and be achieved in almost any other language.

## Layers

In a microservice architecture, the separation of concerns is achieved by organising code into distinct layers, each with a defined role and responsibility:

**Controller Layer:** This is the entry point for external communications with the microservice. It handles incoming network requests, interprets client inputs, authenticates requests, and delegates business processing to the service layer. Controllers then send back the appropriate responses to the client. They may also handle various protocols and data formats, ensuring that the client's needs are interpreted correctly for the service layer to process.

**Service Layer:** At the heart of the microservice is the service layer, where the core business logic resides. This layer takes inputs from the controller, performs business operations according to the application's rules and logic, and may interact with the repository layer to retrieve and persist data. It acts as a transaction boundary and ensures business rules are enforced before any changes are committed to the database.

**Repository Layer:** This layer is responsible for data access and manipulation. It abstracts the interaction with the database, providing a clean separation from the core business logic. Through methods for saving, updating, deleting, and querying data, this layer ensures that the service layer remains agnostic of the underlying data storage mechanisms.

Together, these layers work in concert to create a maintainable and scalable microservice, with clear pathways and boundaries between external inputs, business processing, and data management.

### Controller Layer


1. **Function:** Interfacing with clients and handling incoming requests. It translates client requests into actions to be performed by the service layer.
2. **Example:** A REST endpoint for fetching resources, a WebSocket endpoint for real-time communication, or an API endpoint for sending messages to a message broker.
3. **What resides in the Controller Layer**

    -  Controller Implementations
    -  Data Transfer Objects: objects which describe an external interface. Typically these objects include json and validation decorators providing a stable and well defined contract.

4. **Owning folder naming conventions:** controllers, endpoints (examples {project-src-path}/controllers )
5. **File naming conventions:** Controller, Endpoint (example: ItemController.java)

### Service Layer:

1. **Function:** Containing the business logic of the microservice. It processes the data received from the controller layer, applies business rules, and performs operations.
2. **Example:** A service method that validates and processes input data, interacts with the repository layer to persist data, or handles the business logic associated with a Kafka message.

3. **What resides in the Service Layer**

    - Service Interfaces: An interface used by the controller layer to co-ordinate the execution of business logic. The interface enables dependency inject defer the implementation of the business logic enabling test driven development and support api versioning.
    - Service Implementations: implementation of the business logic against a defined interface. Implementation method/functions take entity (repository) objects, conduct some logic/processing and return entity objects
    - Models: these are specific intermediate objects uses to support business logic processing. They are used when an object other than an entity is required

**Owning folder naming conventions:** services (examples {project-src-path}/services )
**File naming conventions:** Service (example: ItemService.java)


### Repository Layer:

1. **Function:** Interacting directly with the data store. It provides methods for querying and manipulating persisted data.
2. **Example:** A repository interface providing methods to find, save, or delete data objects in the database.
3. **What resides in the Repository Layer**

    - Repository Interfaces: An interface used by the service layer defining data persistance/storage
    - Repository Implementations: implementation of the repository which could be SQL via JPA or NOSQL via MongoDB. Either implementation should work against the interface
    - Entities: objects which describe database tables or collections. Typically these objects include decorators for the chosen database technology mapping table/collection fields to the object.
    - (Optional) Repository Mocks: Typically during the early stages of developing a micro service to confirm integration between the controller and service layer, a repository mock can enable development without the need of a real database or associated connection issues.

4. **Owning folder naming conventions:** repositories (examples {project-src-path}/repositories )
5. **File naming conventions:** Repository  (example: ItemRepository.java)

### Adapter Layer

1. **Function:** The Service Layer consumes and returns entity objects and the controller layer consumes and returns DTOs. To support interfacing between the controller and service layer Adapters are used.
2. **Example:** Provides static method/fuctions to map a DTO to and from an Entity
3. **What resides in the Repository Layer**

    - Adapter Implementions

4. **Owning folder naming conventions:** adapters (examples {project-src-path}/adapters )
5. **File naming conventions:** Adapter  (example: ItemAdapter.java)


### Exception Handling

1. **Function:** You should always implement custom exception handle to return an ErrorDTO standardised across your microservices. The will allow you to define custom exceptions and handle them appropriately, but also handle the desired response from unhandled exceptions 

## Typical Project Scaffolding

```
root
|- adapters
|   |- {Resource}Adapter
|- controllers
|   | - {Resource}Controller
|   |- dtos
|       |- {Resource}DTO
|       |- ApiErrorDTO
|- exceptions
|   |- GlobalExceptionHandler 
|- repositories
|   | - {Resource}Service
|   |- interfaces
|       |- I{Resource}Service
|   |- entities
|       |- {Resource}Entity
|
|- services
|   | - {Resource}Service
|   |- interfaces
|       |- I{Resource}Service
``````

## Controller Layer

## API Version

API versioning is a strategy used to evolve services without disrupting existing clients. Two common recommended approaches are route versioning and custom header versioning:

**Route Versioning:** This involves including the version number in the URI path. It's transparent and easy for clients to understand because the version is explicitly stated in the URL (e.g., /api/v1/items). It simplifies the routing of requests to different versions of the API on the server side but can lead to URL proliferation as versions increase.

**Custom Header Versioning:** With this method, the version information is included in a custom request header. This approach keeps URLs clean and is less intrusive, as it doesn't require URL changes when upgrading versions. It allows for more flexibility and supports complex versioning scenarios, such as indicating feature-specific versions within the same API version. However, it can be less discoverable and require more effort from clients to include custom headers.

**Recommendation:** Route versioning is generally recommended for its simplicity and visibility, making it easy for developers to understand and use. It's particularly suitable for public APIs or when you have multiple major versions that change over time. Custom header versioning can be reserved for APIs with clients that can handle additional complexity and for scenarios where version changes are frequent or need to support multiple concurrent versions without changing the URI.

It’s essential to maintain consistency and clear documentation on the versioning approach to ensure a good developer and client experience. When choosing between the API versioning strategies, consider the ease of use for API consumers, the overhead for API providers, and the potential impact on existing clients.

### Impact on naming


The recommended approach is the append the version to the controller for example:

```
root
|- controllers
|   | - {Resource}ControllerV1
|   | - {Resource}ControllerV2
|   | - {Resource}ControllerV3
```

#### Examples

**Note:** the following examples only demonstrates the controller level. The version change is typically driven by a business logic change which modifies the DTO.  

#### Route Versioning Example

Route API versioning is considered the simplest method as the version it is clearly visible on the routes.

Here's how you might define two different controller versions based on routes:

```java
@RestController
@RequestMapping("/api/v1/items")
public class ItemControllerV1 {

    // Constructor, endpoints, and other methods using ItemServiceV1 and ItemDTOV1
}

```

```java
@RestController
@RequestMapping("/api/v2/items")
public class ItemControllerV2 {

    // Constructor, endpoints, and other methods using ItemServiceV2 and ItemDTOV2
}
```

#### Custom Header Versioning

Achieving controller-level versioning with custom headers in Spring Boot. It requires specifying the header in the @RequestMapping annotation at the class level. This way, all the methods within the controller will inherit this header requirement.


Here's how you might define two different controller versions based on custom headers:


#### Version 1 Controller

```java
@RestController
@RequestMapping(path = "/api/items", headers = "X-API-Version=1")
public class ItemControllerV1 {
    // All endpoints in this controller will require header "X-API-Version=1"
}
```

#### Version 2 Controller

```java
@RestController
@RequestMapping(path = "/api/items", headers = "X-API-Version=2")
public class ItemControllerV2 {
    // All endpoints in this controller will require header "X-API-Version=2"
}
```

The client will be responsible for adding this header to the request.

Similar implementations can be achieved other communication technologies like kafka.


### Data Transfer Objects

Below are some reasons why DTOs are part of the minimum requirement:

1. Separation of Concerns: DTOs help in separating the external interface of your application from the internal representation of your data. This means you can change one without affecting the other.

2. API Evolution: When you expose your entity classes directly to clients, any change to the entity structure can break the API. With DTOs, you can evolve the internal model independently of the API.

3. Data Hiding: Entities might contain sensitive information or complex data structures that should not be exposed to the API consumers. DTOs allow you to selectively expose data.

4. Validation: DTOs can be used to enforce validation rules specific to incoming API requests, keeping the validation logic separate from the entity model.

5. Customisation: Different API endpoints might require different data representations. DTOs allow you to tailor the data sent to and from clients without changing the domain model.

6. Performance: Entities might contain lazy-loaded associations that can cause performance issues if accidentally serialised. DTOs can contain exactly what needs to be sent over the network, avoiding such issues.

### Json Annotations

### Validation Annotations

## Controllers

### RestApi

### Stomp
