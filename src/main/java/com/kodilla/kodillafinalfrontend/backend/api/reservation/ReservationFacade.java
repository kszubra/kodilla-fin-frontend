package com.kodilla.kodillafinalfrontend.backend.api.reservation;

import com.kodilla.kodillafinalfrontend.Reservation;
import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.dto.ReservationCreationDto;
import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.dto.ReservationDto;
import com.kodilla.kodillafinalfrontend.backend.api.reservation.mapper.ReservationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ReservationFacade {
    private final ReservationClient reservationClient;
    private final ReservationMapper reservationMapper;

    public Reservation getReservationById(final Long id) {
        return reservationMapper.mapToReservation( reservationClient.getReservationById(id) );
    }

    public List<Reservation> getReservationBySurname(final String surname) {
        return reservationMapper.mapToReservationListFromReservationListDto( reservationClient.getReservationsBySurname(surname) );
    }

    public List<Reservation> getAllReservations() {
        return reservationMapper.mapToReservationListFromReservationListDto( reservationClient.getReservations() );
    }

    public Integer addReservation(final Reservation reservation) {
        return reservationClient.addReservation( reservationMapper.mapToCreationDto( reservation ) );
    }

    public Reservation updateReservation(final Reservation updatingReservation) {
        return reservationMapper.mapToReservation( reservationClient.updateReservation( reservationMapper.mapToDto( updatingReservation ) ) );
    }

    public void deleteReservation(final Long id) {
        reservationClient.deleteReservation(id);
    }

    public Integer countReservationsInCity(final String city) {
       return reservationClient.countReservationsInCity(city);
    }
}
