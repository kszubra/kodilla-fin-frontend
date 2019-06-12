package com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto.PaymentDto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("thereFlightDepartureCity")
    private String thereFlightDepartureCity;

    @JsonProperty("thereFlightDepartureAirportCode")
    private String thereFlightDepartureAirportCode;

    @JsonProperty("thereFlightDestinationCity")
    private String thereFlightDestinationCity;

    @JsonProperty("thereFlightDestinationAirportCode")
    private String thereFlightDestinationAirportCode;

    @JsonProperty("thereFlightDate")
    private String thereFlightDate;

    @JsonProperty("returnFlightDepartureCity")
    private String returnFlightDepartureCity;

    @JsonProperty("returnFlightDepartureAirportCode")
    private String returnFlightDepartureAirportCode;

    @JsonProperty("returnFlightDestinationCity")
    private String returnFlightDestinationCity;

    @JsonProperty("returnFlightDestinationAirportCode")
    private String returnFlightDestinationAirportCode;

    @JsonProperty("returnFlightDate")
    private String returnFlightDate;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("paymentDto")
    private PaymentDto paymentDto;

    @Override
    public String toString() {
        return "ReservationDto{" +
                "id=" + id +
                ", thereFlightDepartureCity='" + thereFlightDepartureCity + '\'' +
                ", thereFlightDepartureAirportCode='" + thereFlightDepartureAirportCode + '\'' +
                ", thereFlightDestinationCity='" + thereFlightDestinationCity + '\'' +
                ", thereFlightDestinationAirportCode='" + thereFlightDestinationAirportCode + '\'' +
                ", thereFlightDate=" + thereFlightDate +
                ", returnFlightDepartureCity='" + returnFlightDepartureCity + '\'' +
                ", returnFlightDepartureAirportCode='" + returnFlightDepartureAirportCode + '\'' +
                ", returnFlightDestinationCity='" + returnFlightDestinationCity + '\'' +
                ", returnFlightDestinationAirportCode='" + returnFlightDestinationAirportCode + '\'' +
                ", returnFlightDate=" + returnFlightDate +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", price=" + price +
                ", paymentDto=" + paymentDto +
                '}';
    }
}
