package com.kodilla.kodillafinalfrontend.domain;

import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentStatus;
import lombok.*;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Payment {

    private String id = "";
    private PaymentStatus status;
    private String value;
    private String paymentDate;

    public boolean isSafeToUpdate() {
        return !id.isEmpty() && this.alwaysRequiredFieldsAreFilled();
    }

    public boolean isSafeToSave() {
        return id.isEmpty() && this.alwaysRequiredFieldsAreFilled();
    }

    private boolean alwaysRequiredFieldsAreFilled() {
        Pattern pricePattern = Pattern.compile("[0-9]+([.][0-9]{1,2})?");
        return !( status == null |
                    !pricePattern.matcher(value).matches() |
                    paymentDate.isEmpty() );
    }

    @Override
    public String toString() {
        return "PaymentDto{" +
                "id=" + id +
                ", status=" + status +
                ", value=" + value +
                ", paymentDate='" + paymentDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment that = (Payment) o;
        return id.equals(that.id) &&
                status == that.status &&
                value.equals(that.value) &&
                paymentDate.equals(that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, value, paymentDate);
    }

    public boolean hasValidDate() {
        Pattern datePattern = Pattern.compile("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
        return datePattern.matcher(paymentDate).matches();
    }

}
