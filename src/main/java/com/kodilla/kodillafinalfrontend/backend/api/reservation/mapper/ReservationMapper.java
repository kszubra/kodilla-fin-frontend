package com.kodilla.kodillafinalfrontend.backend.api.reservation.mapper;

import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.dto.ReservationDto;
import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.dto.ReservationListDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class ReservationMapper {

    public List<ReservationDto> mapToReservationListFromReservationListDto(ReservationListDto dto) {
        return dto.getReservations();
    }
}
