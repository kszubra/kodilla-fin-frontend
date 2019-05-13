package com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.regex.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("status")
    private PaymentStatus status;
    @JsonProperty("value")
    private BigDecimal value;
    @JsonProperty("paymentDate")
    private String paymentDate;

    public boolean hasValidDate() {
        Pattern datePattern = Pattern.compile("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
        return datePattern.matcher( paymentDate ).matches();
    }
}
