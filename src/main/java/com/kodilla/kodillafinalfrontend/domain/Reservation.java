package com.kodilla.kodillafinalfrontend.domain;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Reservation {

    private String id;
    private String paymentId;
    private String thereFlightDepartureCity;
    private String thereFlightDepartureAirportCode;
    private String thereFlightDestinationCity;
    private String thereFlightDestinationAirportCode;
    private String thereFlightDate;
    private String returnFlightDepartureCity;
    private String returnFlightDepartureAirportCode;
    private String returnFlightDestinationCity;
    private String returnFlightDestinationAirportCode;
    private String returnFlightDate;
    private String name;
    private String surname;
    private String email;
    private String price;


    public boolean isSafeToUpdate() {
        return !id.isEmpty() &&
                !paymentId.isEmpty() &&
                this.alwaysRequiredFieldsAreFilled();

    }

    public boolean isSafeToSave() {
        return id.isEmpty() &&
                paymentId.isEmpty() && this.alwaysRequiredFieldsAreFilled();

    }

    private boolean alwaysRequiredFieldsAreFilled() {
        return !( thereFlightDepartureCity.isEmpty() |
                    thereFlightDepartureAirportCode.isEmpty() |
                    thereFlightDestinationCity.isEmpty() |
                    thereFlightDestinationAirportCode.isEmpty() |
                    thereFlightDate.isEmpty() |
                    returnFlightDepartureCity.isEmpty() |
                    returnFlightDepartureAirportCode.isEmpty() |
                    returnFlightDestinationCity.isEmpty() |
                    returnFlightDestinationAirportCode.isEmpty() |
                    returnFlightDate.isEmpty() |
                    name.isEmpty() |
                    surname.isEmpty() |
                    email.isEmpty() |
                    price.isEmpty() );
    }

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
                ", paymentId=" + paymentId +
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
