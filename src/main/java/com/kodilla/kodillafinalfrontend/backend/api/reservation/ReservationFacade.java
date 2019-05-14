package com.kodilla.kodillafinalfrontend.backend.api.reservation;

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

    public ReservationDto getReservationById(final Long id) {
        return reservationClient.getReservationById(id);
    }

    public List<ReservationDto> getReservationBySurname(final String surname) {
        return reservationMapper.mapToReservationListFromReservationListDto( reservationClient.getReservationsBySurname(surname) );
    }

    public List<ReservationDto> getAllReservations() {
        return reservationMapper.mapToReservationListFromReservationListDto( reservationClient.getReservations() );
    }

    public Integer addReservation(final ReservationCreationDto dto) {
        return reservationClient.addReservation(dto);
    }

    public ReservationDto updateReservation(final ReservationDto updatingDto) {
        return reservationClient.updateReservation(updatingDto);
    }

    public void deleteReservation(final Long id) {
        reservationClient.deleteReservation(id);
    }

    public Integer countReservationsInCity(final String city) {
       return reservationClient.countReservationsInCity(city);
    }
}
