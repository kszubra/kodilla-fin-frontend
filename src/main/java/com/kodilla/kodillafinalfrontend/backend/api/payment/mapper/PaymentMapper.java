package com.kodilla.kodillafinalfrontend.backend.api.payment.mapper;

import com.kodilla.kodillafinalfrontend.Payment;
import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentStatus;
import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto.PaymentDto;
import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto.PaymentListDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class PaymentMapper {

    public List<Payment> mapToPaymentListFromPaymentListDto(PaymentListDto dto) {

        return dto.getPayments().stream()
                .map(this::mapToPayment)
                .collect(Collectors.toList());
}

    public Payment mapToPayment(PaymentDto dto) {
        return Payment.builder()
                .id( dto.getId().toString() )
                .paymentDate( (dto.hasValidDate())? dto.getPaymentDate() : "UNPAID" )
                .status( dto.getStatus() )
                .value( dto.getValue().setScale(2, RoundingMode.HALF_EVEN).toString() )
                .build();
    }

    public List<Payment> mapToPaymentList(List<PaymentDto> dtoList ) {
        return dtoList.stream()
                .map(this::mapToPayment)
                .collect(Collectors.toList());
    }

    public PaymentDto mapToPaymentDto(Payment payment) {
        return PaymentDto.builder()
                .id( (payment.getId() == null)? null : Long.parseLong(payment.getId()) )
                .value( BigDecimal.valueOf( Long.parseLong( payment.getValue() ) ) )
                .status( payment.getStatus() )
                .paymentDate( payment.getPaymentDate() )
                .build();
    }

}
