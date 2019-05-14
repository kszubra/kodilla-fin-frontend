package com.kodilla.kodillafinalfrontend.backend.api.payment;

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

    public PaymentDto getPayment(Long id) {
        return paymentClient.getPayment(id);
    }

    public List<PaymentDto> getPayments() {
        return paymentMapper.mapToPaymentListFromPaymentListDto( paymentClient.getPayments() );
    }

    public List<PaymentDto> getPaymentsByDate(String date) {
        return paymentMapper.mapToPaymentListFromPaymentListDto( paymentClient.getPaymentsByDate(date) );
    }

    public Integer addPayment(PaymentDto dto) {
        return paymentClient.addPayment(dto);
    }

    public PaymentDto updatePayment(PaymentDto dto) {
        return paymentClient.updatePayment(dto);
    }
}
