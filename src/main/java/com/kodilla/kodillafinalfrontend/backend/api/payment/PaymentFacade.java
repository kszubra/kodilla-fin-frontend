package com.kodilla.kodillafinalfrontend.backend.api.payment;

import com.kodilla.kodillafinalfrontend.Payment;
import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto.PaymentDto;
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
        return paymentMapper.mapToPayment( paymentClient.getPayment(id) ) ;
    }

    public List<Payment> getPayments() {
        return paymentMapper.mapToPaymentListFromPaymentListDto( paymentClient.getPayments() );
    }

    public List<Payment> getPaymentsByDate(String date) {
        return paymentMapper.mapToPaymentListFromPaymentListDto( paymentClient.getPaymentsByDate(date) );
    }

    public Integer addPayment(Payment payment) {
        return paymentClient.addPayment( paymentMapper.mapToPaymentDto( payment ) );
    }

    public PaymentDto updatePayment(Payment payment) {
        return paymentClient.updatePayment( paymentMapper.mapToPaymentDto( payment ) );
    }
}
