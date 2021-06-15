package com.example.clean.architecture.app.entrypoint.rest.common;

import reactor.core.publisher.Mono;

public interface ResponsePresenter<T> {
    Mono<T> getResponse();
}
