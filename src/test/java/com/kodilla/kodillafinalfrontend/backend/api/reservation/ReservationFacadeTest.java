package com.kodilla.kodillafinalfrontend.backend.api.reservation;

import com.kodilla.kodillafinalfrontend.domain.Reservation;
import org.junit.Test;
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
        Reservation reservation = Reservation.builder()
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
                .price(BigDecimal.valueOf(555,66).toString())
                .build();

        //When
        int result = reservationFacade.addReservation( reservation );

        //Then
        assertEquals(200, result);
    }

    @Test
    public void testUpdatingReservation() {
        //Given
        Reservation reservationToUpdate = reservationFacade.getReservationById(6L);
        reservationToUpdate.setEmail("newemail@email.com");

        //When
        Reservation result = reservationFacade.updateReservation(reservationToUpdate);

        //Then
        assertEquals("newemail@email.com", result.getEmail());
        System.out.println(result);;
    }

    @Test
    public void testDeleteingReservation() {
        //Given
        reservationFacade.deleteReservation(35L);
    }

    @Test
    public void testCountingReservationsInCity() {
        //Given
        Reservation reservation = Reservation.builder()
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
                .price(BigDecimal.valueOf(555,66).toString())
                .build();
        reservationFacade.addReservation( reservation );

        //when
        int result = reservationFacade.countReservationsInCity("Hanover");

        //Then
        assertTrue(result > 0);

    }
}