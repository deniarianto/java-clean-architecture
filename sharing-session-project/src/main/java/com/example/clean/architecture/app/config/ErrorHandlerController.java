package com.example.clean.architecture.app.config;

import com.example.clean.architecture.provider.product.config.CircuitBreakerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ErrorHandlerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerController.class);

    @ExceptionHandler(CircuitBreakerException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<Object> circuitBreaker(CircuitBreakerException exception) {
        return Mono.defer(() -> {
            LOGGER.info("CircuitBreakerException : {}", exception.toString());
            return Mono.just(exception.getCode());
        });
    }

}
