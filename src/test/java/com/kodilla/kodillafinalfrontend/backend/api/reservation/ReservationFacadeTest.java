package com.kodilla.kodillafinalfrontend.backend.api.reservation;

import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentStatus;
import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto.PaymentDto;
import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.Reservation;
import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.dto.ReservationCreationDto;
import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.dto.ReservationDto;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationFacadeTest {
    @Autowired
    private ReservationFacade reservationFacade;

    @Test
    public void testGetReservation() {
        //When
        Reservation testReservation = reservationFacade.getReservationById(4L);

        //Then
        System.out.println(testReservation);
    }

    @Test
    public void testGetAllReservations() {
        //When
        List<Reservation> testReservations = reservationFacade.getAllReservations();

        //Then
        testReservations.forEach(System.out::println);
    }

    @Test
    public void testGetReservationsBySurname() {
        //When
        List<Reservation> testReservations = reservationFacade.getReservationBySurname("seed");

        //Then
        testReservations.forEach(System.out::println);
    }

    @Test
    public void testAddReservation() {
        //Given
        ReservationCreationDto creationDto = ReservationCreationDto.builder()
                .returnFlightDate("2019-04-15")
                .thereFlightDate("2019-08-20")
                .thereFlightDepartureCity( "Warsaw" )
                .thereFlightDepartureAirportCode( "WAW" )
                .thereFlightDestinationCity( "Hanover" )
                .thereFlightDestinationAirportCode( "HAJ" )
                .returnFlightDepartureCity( "Hanover" )
                .returnFlightDepartureAirportCode( "HAJ" )
                .returnFlightDestinationCity( "Warsaw" )
                .returnFlightDestinationAirportCode( "WAW" )
                .name( "John" )
                .surname( "Rambo" )
                .email( "rambo@rambo.com" )
                .price(BigDecimal.valueOf(555,66))
                .build();

        //When
        int result = reservationFacade.addReservation( creationDto );

        //Then
        assertEquals(200, result);
    }

    @Test
    public void testUpdatingReservation() {
        //Given
        PaymentDto paymentDto = PaymentDto.builder()
                .id(20L)
                .status(PaymentStatus.AWAITING)
                .paymentDate("UNPAID")
                .value( BigDecimal.valueOf(250.55) )
                .build();

        ReservationDto dto = ReservationDto.builder()
                .id(21L)
                .returnFlightDate("2019-04-15")
                .thereFlightDate("2019-08-20")
                .thereFlightDepartureCity( "Warsaw" )
                .thereFlightDepartureAirportCode( "WAW" )
                .thereFlightDestinationCity( "Hanover" )
                .thereFlightDestinationAirportCode( "HAJ" )
                .returnFlightDepartureCity( "Hanover" )
                .returnFlightDepartureAirportCode( "HAJ" )
                .returnFlightDestinationCity( "Warsaw" )
                .returnFlightDestinationAirportCode( "WAW" )
                .name( "John JOHN" )
                .surname( "Rambo EDITED" )
                .email( "rambo@rambo.com" )
                .price(BigDecimal.valueOf(555,66))
                .paymentDto(paymentDto)
                .build();

        //When
        Reservation result = reservationFacade.updateReservation(dto);

        //Then
        System.out.println(result);;
    }
}