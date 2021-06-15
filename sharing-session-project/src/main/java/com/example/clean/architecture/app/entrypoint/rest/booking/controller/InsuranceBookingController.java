package com.example.clean.architecture.app.entrypoint.rest.booking.controller;

import com.example.clean.architecture.app.entrypoint.rest.booking.entity.InsuranceBookingResponseRest;
import com.example.clean.architecture.app.entrypoint.rest.booking.presenter.BookingPresenter;
import com.example.clean.architecture.core.booking.entity.InsuranceBookingRequest;
import com.example.clean.architecture.core.booking.usecase.CreateBookingUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/insurance")
public class InsuranceBookingController {

    private final CreateBookingUseCase createBookingUseCase;

    public InsuranceBookingController(CreateBookingUseCase createBookingUseCase) {
        this.createBookingUseCase = createBookingUseCase;
    }

    @PostMapping("/booking")
    public Mono<InsuranceBookingResponseRest> booking(@RequestBody InsuranceBookingRequest insuranceBookingRequest){
        return Mono.defer(
                () -> {
                    BookingPresenter presenter = new BookingPresenter();
                    return createBookingUseCase
                            .execute(insuranceBookingRequest, presenter)
                            .then(presenter.getResponse());
                });
    }
}
