package com.example.clean.architecture.provider.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

public class LogRequestExchangeFilterFunction implements ExchangeFilterFunction {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(LogRequestExchangeFilterFunction.class);

    private static final String LOG_MESSAGE_REQUEST = "Request: [{}] {}";

    private static final String LOG_MESSAGE_HEADER_REQUEST = "Request Header:";

    @NonNull
    @Override
    public Mono<ClientResponse> filter(@NonNull ClientRequest request,
                                       @NonNull ExchangeFunction next) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(LOG_MESSAGE_REQUEST, request.method(), request.url());

            LOGGER.debug(LOG_MESSAGE_HEADER_REQUEST);
            request.headers().forEach(
                    (name, values) -> values.forEach(value -> LOGGER.debug("{}: {}", name, value))
            );
        }

        return next.exchange(request);
    }
}