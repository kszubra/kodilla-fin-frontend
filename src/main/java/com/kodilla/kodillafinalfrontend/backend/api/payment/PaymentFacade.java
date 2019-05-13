package com.kodilla.kodillafinalfrontend.backend.api.payment;

import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.Payment;
import com.kodilla.kodillafinalfrontend.backend.api.payment.mapper.PaymentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PaymentFacade {
    private final PaymentClient paymentClient;
    private final PaymentMapper paymentMapper;

    public Payment getPayment(Long id) {
        return paymentMapper.mapToPayment( paymentClient.getPayment(id) );
    }

    public List<Payment> getPayments() {
        return paymentMapper.mapToPaymentListFromBackend( paymentClient.getPayments() );
    }
}
