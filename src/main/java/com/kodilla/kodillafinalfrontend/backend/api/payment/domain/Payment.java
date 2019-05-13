package com.kodilla.kodillafinalfrontend.backend.api.payment.domain;

import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentStatus;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Payment {

    private Long id;
    private PaymentStatus status;
    private BigDecimal value;
    private LocalDate paymentDate;

    public boolean hasPaymentDate() {
        return !(paymentDate==null);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", status=" + status +
                ", value=" + value +
                ", paymentDate=" + paymentDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id.equals(payment.id) &&
                status == payment.status &&
                value.equals(payment.value) &&
                paymentDate.equals(payment.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, value, paymentDate);
    }
}
