package com.kodilla.kodillafinalfrontend.backend.api.reservation.domain;

import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.Payment;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Reservation {

    private Long id;
    private String thereFlightDepartureCity;
    private String thereFlightDepartureAirportCode;
    private String thereFlightDestinationCity;
    private String thereFlightDestinationAirportCode;
    private LocalDate thereFlightDate;
    private String returnFlightDepartureCity;
    private String returnFlightDepartureAirportCode;
    private String returnFlightDestinationCity;
    private String returnFlightDestinationAirportCode;
    private LocalDate returnFlightDate;
    private String name;
    private String surname;
    private String email;
    private BigDecimal price;
    private Payment payment;

    @Override
    public String toString() {
        return "Reservation{" +
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
                ", payment=" + payment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return thereFlightDepartureCity.equals(that.thereFlightDepartureCity) &&
                thereFlightDepartureAirportCode.equals(that.thereFlightDepartureAirportCode) &&
                thereFlightDestinationCity.equals(that.thereFlightDestinationCity) &&
                thereFlightDestinationAirportCode.equals(that.thereFlightDestinationAirportCode) &&
                thereFlightDate.equals(that.thereFlightDate) &&
                returnFlightDepartureCity.equals(that.returnFlightDepartureCity) &&
                returnFlightDepartureAirportCode.equals(that.returnFlightDepartureAirportCode) &&
                returnFlightDestinationCity.equals(that.returnFlightDestinationCity) &&
                returnFlightDestinationAirportCode.equals(that.returnFlightDestinationAirportCode) &&
                returnFlightDate.equals(that.returnFlightDate) &&
                name.equals(that.name) &&
                surname.equals(that.surname) &&
                email.equals(that.email) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thereFlightDepartureCity, thereFlightDepartureAirportCode, thereFlightDestinationCity, thereFlightDestinationAirportCode, thereFlightDate, returnFlightDepartureCity, returnFlightDepartureAirportCode, returnFlightDestinationCity, returnFlightDestinationAirportCode, returnFlightDate, name, surname, email, price);
    }
}
