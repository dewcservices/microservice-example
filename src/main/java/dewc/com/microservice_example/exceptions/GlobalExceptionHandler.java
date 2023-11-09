package dewc.com.microservice_example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import dewc.com.microservice_example.controllers.dtos.ApiErrorDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
  
      @ExceptionHandler(HttpRequestException.class)
    public ResponseEntity<ApiErrorDTO> handleItemCreationException(HttpRequestException ex) {
        var apiError = new ApiErrorDTO(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(apiError, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDTO> handleAllUncaughtException(Exception ex) {
        var apiError = new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // more to come

}
