package com.kodilla.kodillafinalfrontend.backend.api.payment.mapper;

import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto.PaymentDto;
import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto.PaymentListDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class PaymentMapper {

    public List<PaymentDto> mapToPaymentListFromPaymentListDto(PaymentListDto dto) {
        return dto.getPayments();
}








}
