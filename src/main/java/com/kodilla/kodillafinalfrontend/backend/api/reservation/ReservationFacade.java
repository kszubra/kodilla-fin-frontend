package com.kodilla.kodillafinalfrontend.backend.api.reservation;

import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.Reservation;
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

    public Integer addReservation(final ReservationCreationDto dto) {
        return reservationClient.addReservation(dto);
    }

    public Reservation updateReservation(ReservationDto updatingDto) {
        return reservationMapper.mapToReservation( reservationClient.updateReservation(updatingDto) );
    }
}
