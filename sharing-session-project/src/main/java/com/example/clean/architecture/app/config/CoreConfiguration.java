package com.example.clean.architecture.app.config;

import com.example.clean.architecture.core.booking.usecase.CreateBookingImpl;
import com.example.clean.architecture.core.booking.usecase.CreateBookingUseCase;
import com.example.clean.architecture.core.product.gateway.ProductGateway;
import com.example.clean.architecture.core.product.usecase.ProductFindAllImpl;
import com.example.clean.architecture.core.product.usecase.ProductFindAllUseCase;
import com.example.clean.architecture.core.transaction.gateway.TransactionGateway;
import com.example.clean.architecture.core.transaction.usecase.TransactionFindByIdImpl;
import com.example.clean.architecture.core.transaction.usecase.TransactionFindByIdUseCase;
import com.example.clean.architecture.provider.product.gateway.ProductGatewayImpl;
import com.example.clean.architecture.provider.transaction.gateway.TransactionGatewayImpl;
import com.example.clean.architecture.provider.transaction.gateway.TransactionMongoGatewayImpl;
import com.example.clean.architecture.provider.transaction.mongodb.repository.TransactionDetailMongoRepository;
import com.example.clean.architecture.provider.transaction.mongodb.repository.TransactionMongoRepository;
import com.example.clean.architecture.provider.transaction.mysql.repository.TransactionRepository;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Configuration(proxyBeanMethods = true)
@ComponentScan("com.example.clean.architecture.*")
@EnableJpaRepositories(value = "com.example.clean.architecture.*")
public class CoreConfiguration {

    /**
     * Using PostgreSQL
     * @param transactionRepository
     * @return
     */
    //@Bean
    //public TransactionGateway transactionGateway(TransactionRepository transactionRepository){
    //    return new TransactionGatewayImpl(transactionRepository);
    //}

    /**
     * Using MongoDB
     * @param transactionMongoRepository
     * @param transactionDetailMongoRepository
     * @return
     */
    @Bean
    public TransactionGateway transactionGateway(TransactionMongoRepository transactionMongoRepository,
                                                 TransactionDetailMongoRepository transactionDetailMongoRepository){
        return new TransactionMongoGatewayImpl(transactionMongoRepository, transactionDetailMongoRepository);
    }

    @Bean
    public CreateBookingUseCase createBookingUseCase(TransactionGateway transactionGateway){
        return new CreateBookingImpl(transactionGateway);
    }

    @Bean
    public TransactionFindByIdUseCase transactionFindByIdUseCase(TransactionGateway transactionGateway){
        return new TransactionFindByIdImpl(transactionGateway);
    }

    @Bean
    public CircuitBreakerConfig circuitBreakerConfig(){
        return CircuitBreakerConfig.custom()
                .slidingWindowSize(10)
                .minimumNumberOfCalls(20)
                .permittedNumberOfCallsInHalfOpenState(1)
                .waitDurationInOpenState(Duration.ofSeconds(20))
                .failureRateThreshold(50)
                .recordExceptions(HttpServerErrorException.class, TimeoutException.class, IOException.class)
                .build();
    }

    @Bean
    public TimeLimiterConfig timeLimiterConfig(){
        return TimeLimiterConfig.custom().cancelRunningFuture(false).timeoutDuration(Duration.ofSeconds(15)).build();
    }

    @Bean
    public ProductGateway productGateway(WebClient productWebClient){
        return new ProductGatewayImpl(productWebClient, CircuitBreakerRegistry.of(circuitBreakerConfig()), TimeLimiterRegistry.of(timeLimiterConfig()));
    }

    @Bean
    public ProductFindAllUseCase productFindAllUseCase(ProductGateway productGateway){
        return new ProductFindAllImpl(productGateway);
    }
}
