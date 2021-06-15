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
import com.example.clean.architecture.provider.transaction.mysql.entity.Transaction;
import com.example.clean.architecture.provider.transaction.mysql.entity.TransactionDetail;
import com.example.clean.architecture.provider.transaction.mysql.repository.TransactionRepository;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class TransactionGatewayImpl implements TransactionGateway {
    private static final boolean useMongo = true;
    private final TransactionRepository transactionRepository;

    public TransactionGatewayImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Mono<TransactionResponse> save(InsuranceBookingRequest request) {
        return Mono.defer(() -> {
            return Mono.justOrEmpty(transactionRepository.saveAndFlush(map(request)))
                    .map(this::mapResponse);
        });
    }

    @Override
    public Mono<TransactionResponse> findById(Long id) {
        return Mono.justOrEmpty(transactionRepository.findById(id))
                .map(this::mapResponse);
    }

    private Transaction map(InsuranceBookingRequest request){
        TransactionDetail transactionDetail = TransactionDetail.builder()
                .currency("IDR")
                .qty(request.getBookRequests().get(0).getQty())
                .productCode(request.getBookRequests().get(0).getProductCode())
                .totalPrice(request.getBookRequests().get(0).getBookData().getTotalPrice().getPrice())
                .build();

        Transaction transaction = Transaction.builder()
                .title(request.getContact().getTitle())
                .fullName(generateFullName(request.getContact()))
                .currency("IDR")
                .totalPrice(transactionDetail.getTotalPrice())
                .email(request.getContact().getEmail())
                .phone(request.getContact().getPhone())
                .transactionDetails(Arrays.asList(transactionDetail))
                .build();

        transaction.getTransactionDetails().forEach(transactionDetail1 -> transactionDetail1.setTransaction(transaction));

        return transaction;
    }

    private String generateFullName(ContactDataRequest contact) {
        if(Objects.nonNull(contact.getLastName()) && !"".equalsIgnoreCase(contact.getLastName().trim())){
            return contact.getFirstName() + " " + contact.getLastName();
        }
        return contact.getFirstName() + " " + contact.getFirstName();
    }

    private TransactionResponse mapResponse(Transaction transaction){

        return TransactionResponse.builder()
                .fullName(transaction.getFullName())
                .currency(transaction.getCurrency())
                .id(transaction.getId())
                .email(transaction.getEmail())
                .lang(transaction.getLang())
                .phone(transaction.getPhone())
                .totalPrice(transaction.getTotalPrice())
                .transactionDetail(
                        TransactionDetailResponse.builder()
                                .id(transaction.getTransactionDetails().get(0).getId())
                                .currency(transaction.getTransactionDetails().get(0).getCurrency())
                                .productCode(transaction.getTransactionDetails().get(0).getProductCode())
                                .totalPrice(transaction.getTransactionDetails().get(0).getTotalPrice())
                                .qty(transaction.getTransactionDetails().get(0).getQty())
                                .build()
                )
                .build();
    }

    private TransactionResponse mapResponse(TransactionMongo transaction, TransactionDetailMongo transactionDetailMongo){
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
}
