package com.example.clean.architecture.provider.transaction.gateway;

import com.example.clean.architecture.core.booking.entity.ContactDataRequest;
import com.example.clean.architecture.core.booking.entity.InsuranceBookingRequest;
import com.example.clean.architecture.core.transaction.entity.TransactionDetailResponse;
import com.example.clean.architecture.core.transaction.entity.TransactionResponse;
import com.example.clean.architecture.core.transaction.gateway.TransactionGateway;
import com.example.clean.architecture.provider.transaction.mongodb.entity.TransactionDetailMongo;
import com.example.clean.architecture.provider.transaction.mongodb.entity.TransactionMongo;
import com.example.clean.architecture.provider.transaction.mongodb.repository.TransactionDetailMongoRepository;
import com.example.clean.architecture.provider.transaction.mongodb.repository.TransactionMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Objects;

public class TransactionMongoGatewayImpl implements TransactionGateway {

    private final TransactionMongoRepository transactionMongoRepository;
    private final TransactionDetailMongoRepository transactionDetailMongoRepository;

    public TransactionMongoGatewayImpl(TransactionMongoRepository transactionMongoRepository, TransactionDetailMongoRepository transactionDetailMongoRepository) {
        this.transactionMongoRepository = transactionMongoRepository;
        this.transactionDetailMongoRepository = transactionDetailMongoRepository;
    }

    @Override
    public Mono<TransactionResponse> save(InsuranceBookingRequest request) {
        return saveTransactionMongo(request);
    }

    @Override
    public Mono<TransactionResponse> findById(Long id) {
        return transactionMongoRepository.findByTransactionId(id).map(this::mapResponse);
    }

    private Mono<TransactionResponse> saveTransactionMongo(InsuranceBookingRequest request) {
        Long transactionId = new Date().getTime();
        Long transactionDetailId = new Date().getTime();
        TransactionDetailMongo transactionDetail = TransactionDetailMongo.builder()
                .currency("IDR")
                .qty(request.getBookRequests().get(0).getQty())
                .productCode(request.getBookRequests().get(0).getProductCode())
                .totalPrice(request.getBookRequests().get(0).getBookData().getTotalPrice().getPrice())
                .transactionDetailId(transactionDetailId)
                .transactionId(transactionId)
                .build();

        TransactionMongo transactionMongo = TransactionMongo.builder()
                .title(request.getContact().getTitle())
                .fullName(generateFullName(request.getContact()))
                .currency("IDR")
                .totalPrice(transactionDetail.getTotalPrice())
                .email(request.getContact().getEmail())
                .phone(request.getContact().getPhone())
                .transactionId(transactionId)
                .build();
        return Mono.zip(transactionMongoRepository.save(transactionMongo),
                transactionDetailMongoRepository.save(transactionDetail), this::mapResponse);
    }

    private String generateFullName(ContactDataRequest contact) {
        if(Objects.nonNull(contact.getLastName()) && !"".equalsIgnoreCase(contact.getLastName().trim())){
            return contact.getFirstName() + " " + contact.getLastName();
        }
        return contact.getFirstName() + " " + contact.getFirstName();
    }

    private TransactionResponse mapResponse (TransactionMongo transaction, TransactionDetailMongo transactionDetailMongo){
            return TransactionResponse.builder()
                    .fullName(transaction.getFullName())
                    .currency(transaction.getCurrency())
                    .id(transaction.getTransactionId())
                    .email(transaction.getEmail())
                    .lang(transaction.getLang())
                    .phone(transaction.getPhone())
                    .totalPrice(transaction.getTotalPrice())
                    .transactionDetail(
                            TransactionDetailResponse.builder()
                                    .id(transactionDetailMongo.getTransactionDetailId())
                                    .currency(transactionDetailMongo.getCurrency())
                                    .productCode(transactionDetailMongo.getProductCode())
                                    .totalPrice(transactionDetailMongo.getTotalPrice())
                                    .qty(transactionDetailMongo.getQty())
                                    .build()
                    )
                    .build();
    }

    private TransactionResponse mapResponse (TransactionMongo transaction){
        return TransactionResponse.builder()
                .fullName(transaction.getFullName())
                .currency(transaction.getCurrency())
                .id(transaction.getTransactionId())
                .email(transaction.getEmail())
                .lang(transaction.getLang())
                .phone(transaction.getPhone())
                .totalPrice(transaction.getTotalPrice())
//                .transactionDetail(
////                        TransactionDetailResponse.builder()
////                                .id(transactionDetailMongo.getTransactionDetailId())
////                                .currency(transactionDetailMongo.getCurrency())
////                                .productCode(transactionDetailMongo.getProductCode())
////                                .totalPrice(transactionDetailMongo.getTotalPrice())
////                                .qty(transactionDetailMongo.getQty())
////                                .build()
//                )
                .build();
    }
}

