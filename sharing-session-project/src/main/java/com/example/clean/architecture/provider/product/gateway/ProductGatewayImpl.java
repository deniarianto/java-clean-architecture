package com.example.clean.architecture.provider.product.gateway;

import com.example.clean.architecture.core.product.entity.ProductResponse;
import com.example.clean.architecture.core.product.gateway.ProductGateway;
import com.example.clean.architecture.provider.product.config.CircuitBreakerException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.timelimiter.TimeLimiterOperator;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class ProductGatewayImpl implements ProductGateway {

    private final WebClient productWebClient;
    private final CircuitBreaker circuitBreaker;
    private final TimeLimiter timeLimiter;

    public ProductGatewayImpl(WebClient productWebClient, CircuitBreakerRegistry circuitBreakerRegistry,
                              TimeLimiterRegistry timeLimiterRegistry) {
        this.productWebClient = productWebClient;
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("productGatewayCircuit");
        this.timeLimiter = timeLimiterRegistry.timeLimiter("productGatewayTimeLimiter");
    }

    @Override
    public Mono<List<ProductResponse>> findAllProduct() {
        String uri = "/product/findAll";
        //return monoWithFallback(
            return productWebClient
                .get().uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductResponse>>() {
                }).log();
       // );
    }

    protected <T> Mono<T> monoWithFallback(Mono<T> publisher) {
        return publisher
                .transform(TimeLimiterOperator.of(timeLimiter))
                .transform(CircuitBreakerOperator.of(circuitBreaker))
                .onErrorResume(TimeoutException.class, fallback())
                .onErrorResume(CallNotPermittedException.class, fallback());
    }

    private <T> Function<Throwable, Mono<T>> fallback() {
        return (Throwable) -> Mono
                .error(new CircuitBreakerException("Circuit Breaker",
                        "Circuit Breaker", Throwable.toString()
                ));
    }
}
