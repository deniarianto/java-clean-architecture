package com.example.clean.architecture.provider.product.config;

import com.example.clean.architecture.provider.config.LogRequestExchangeFilterFunction;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class ProductOutboundConfiguration {

    @Bean
    public ClientHttpConnector defaultClientHttpConnector() {
        return new ReactorClientHttpConnector(HttpClient.create()
                .tcpConfiguration(client -> client
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20000)
                        .doOnConnected(conn -> conn
                                .addHandlerLast(new ReadTimeoutHandler(20000,
                                        TimeUnit.MILLISECONDS)
                                )
                                .addHandlerLast(
                                        new WriteTimeoutHandler(20000, TimeUnit.MILLISECONDS)
                                )
                        )
                )
        );
    }

    @Bean
    public ExchangeFilterFunction logRequestExchangeFilterFunction() {
        return new LogRequestExchangeFilterFunction();
    }


    @Bean
    public WebClient productWebClient(
            ExchangeFilterFunction loadBalancerExchangeFilterFunction,
            ExchangeFilterFunction logRequestExchangeFilterFunction,
            ClientHttpConnector defaultClientHttpConnector
    ){
        String baseUrl = "http://localhost:8084";
        return WebClient.builder()
                .baseUrl(baseUrl)
                .filter(loadBalancerExchangeFilterFunction)
                .filter(logRequestExchangeFilterFunction)
                .clientConnector(defaultClientHttpConnector)
                .build();
    }
}
