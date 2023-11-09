package dewc.com.microservice_example.exceptions;

import org.springframework.http.HttpStatus;

public class HttpRequestException extends RuntimeException {
      private final HttpStatus status;

    public HttpRequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
