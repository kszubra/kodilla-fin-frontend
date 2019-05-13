package com.kodilla.kodillafinalfrontend.backend.api.payment.mapper;

import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentStatus;
import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.Payment;
import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto.PaymentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    private Payment mapToCPaymentFromBackend(Map<String, Object> response) {
        Payment payment = new Payment();
        payment.setId( Long.valueOf( (Integer)response.get("id") ) );

        if ( ((String)response.get("paymentDate")).length() == 10 ) {
            payment.setPaymentDate( LocalDate.parse( (String)response.get("paymentDate") ) );
        } else {
            payment.setPaymentDate(null);
        }
        payment.setStatus( PaymentStatus.valueOf( (String)response.get("status") ) );
        payment.setValue( BigDecimal.valueOf( (Double)response.get("value") ) );

        return payment;
    }

    public List<Payment> mapToPaymentListFromBackend(final Object object) {
        ArrayList<Map<String, Object>> dtoList = (ArrayList<Map<String, Object>>) object;
        List<Payment> paymentsToReturn = new ArrayList<>();

        for( Map<String, Object> paymentMap : dtoList ) {
            paymentsToReturn.add( this.mapToCPaymentFromBackend(paymentMap) );
        }

        return paymentsToReturn;

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
