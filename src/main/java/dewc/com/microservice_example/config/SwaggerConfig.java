package dewc.com.microservice_example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
  
  @Bean
  OpenAPI openApi(){
    return new OpenAPI().info(new Info().title("Microservice Example")
    );
  }

}
