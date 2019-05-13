package com.kodilla.kodillafinalfrontend.backend.api.payment.mapper;

import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.Payment;
import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto.PaymentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PaymentMapper {

    public Payment mapToPayment(final PaymentDto dto) {
        return Payment.builder()
                .id( dto.getId() )
                .paymentDate( dto.hasValidDate()? LocalDate.parse( dto.getPaymentDate() ) : null )
                .status( dto.getStatus() )
                .value( dto.getValue() )
                .build();
    }

    public List<Payment> mapToPaymentList(final Object object) {
        List<PaymentDto> dtoList = (ArrayList<PaymentDto>) object;
        return dtoList.stream()
                .map(this::mapToPayment)
                .collect(Collectors.toList());
    }

    /**
     * NOTE FOR REVIEW:
     * Method checks if result is Null or not to not cause NullPointerException.
     * Way better solution would be Optional<LocalDate>, but it cannot be saved to database and would require
     * refactoring whole code to 3 layers (database entities, domain, dto) that would consume too much time.
     * Possible refactoring in the future with more time.
     *
     * @param payment
     * @return
     */
    private String getPaymentDateString(final Payment payment) {
        if(payment.hasPaymentDate()) {
            return payment.getPaymentDate().toString();
        }

        return "UNPAID";
    }

    public PaymentDto mapToDto(final Payment payment) {
        return PaymentDto.builder()
                .id( payment.getId() )
                .paymentDate( this.getPaymentDateString(payment) )
                .status( payment.getStatus() )
                .value( payment.getValue() )
                .build();
    }

    public List<PaymentDto> mapToDtoList(final List<Payment> paymentList) {
        return paymentList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
