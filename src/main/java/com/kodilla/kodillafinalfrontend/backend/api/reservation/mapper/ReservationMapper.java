package com.kodilla.kodillafinalfrontend.backend.api.reservation.mapper;

import com.kodilla.kodillafinalfrontend.domain.Reservation;
import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentClient;
import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.dto.ReservationCreationDto;
import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.dto.ReservationDto;
import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.dto.ReservationListDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class ReservationMapper {
    private final PaymentClient paymentClient;

    public List<Reservation> mapToReservationListFromReservationListDto(ReservationListDto dto) {
        return mapToReservationList( dto.getReservations() );
    }

    public Reservation mapToReservation(ReservationDto dto) {
        return Reservation.builder()
                .id( dto.getId().toString() )
                .paymentId( dto.getPaymentDto().getId().toString() )
                .name( dto.getName() )
                .surname( dto.getSurname() )
                .email( dto.getEmail() )
                .price( dto.getPrice().toString() )
                //THERE FLIGHT DATA
                .thereFlightDate( dto.getThereFlightDate() )
                .thereFlightDepartureCity( dto.getThereFlightDepartureCity() )
                .thereFlightDepartureAirportCode( dto.getThereFlightDepartureAirportCode() )
                .thereFlightDestinationCity( dto.getThereFlightDestinationCity() )
                .thereFlightDestinationAirportCode( dto.getThereFlightDestinationAirportCode() )
                //RETURN FLIGHT DATA
                .returnFlightDate( dto.getReturnFlightDate() )
                .returnFlightDepartureCity( dto.getReturnFlightDepartureCity() )
                .returnFlightDepartureAirportCode( dto.getReturnFlightDepartureAirportCode() )
                .returnFlightDestinationCity( dto.getReturnFlightDestinationCity() )
                .returnFlightDestinationAirportCode( dto.getReturnFlightDestinationAirportCode() )
                .build();
    }

    public List<Reservation> mapToReservationList(List<ReservationDto> dtoList) {
        return dtoList.stream()
                .map(this::mapToReservation)
                .collect(Collectors.toList());
    }

    public ReservationDto mapToDto(Reservation reservation){
        return ReservationDto.builder()
                .id( Long.parseLong( reservation.getId() ) )
                .paymentDto( paymentClient.getPayment( Long.parseLong( reservation.getPaymentId() ) ) )
                .name( reservation.getName() )
                .surname( reservation.getSurname() )
                .email( reservation.getEmail() )
                .price( BigDecimal.valueOf( Double.parseDouble( reservation.getPrice() ) ) )
                //THERE FLIGHT DATA
                .thereFlightDate( reservation.getThereFlightDate() )
                .thereFlightDepartureCity( reservation.getThereFlightDepartureCity() )
                .thereFlightDepartureAirportCode( reservation.getThereFlightDepartureAirportCode() )
                .thereFlightDestinationCity( reservation.getThereFlightDestinationCity() )
                .thereFlightDestinationAirportCode( reservation.getThereFlightDestinationAirportCode() )
                //RETURN FLIGHT DATA
                .returnFlightDate( reservation.getReturnFlightDate() )
                .returnFlightDepartureCity( reservation.getReturnFlightDepartureCity() )
                .returnFlightDepartureAirportCode( reservation.getReturnFlightDepartureAirportCode() )
                .returnFlightDestinationCity( reservation.getReturnFlightDestinationCity() )
                .returnFlightDestinationAirportCode( reservation.getReturnFlightDestinationAirportCode() )
                .build();
    }

    public ReservationCreationDto mapToCreationDto(Reservation reservation){
        return ReservationCreationDto.builder()
                .name( reservation.getName() )
                .surname( reservation.getSurname() )
                .email( reservation.getEmail() )
                .price( BigDecimal.valueOf( Double.parseDouble( reservation.getPrice() ) ) )
                //THERE FLIGHT DATA
                .thereFlightDate( reservation.getThereFlightDate() )
                .thereFlightDepartureCity( reservation.getThereFlightDepartureCity() )
                .thereFlightDepartureAirportCode( reservation.getThereFlightDepartureAirportCode() )
                .thereFlightDestinationCity( reservation.getThereFlightDestinationCity() )
                .thereFlightDestinationAirportCode( reservation.getThereFlightDestinationAirportCode() )
                //RETURN FLIGHT DATA
                .returnFlightDate( reservation.getReturnFlightDate() )
                .returnFlightDepartureCity( reservation.getReturnFlightDepartureCity() )
                .returnFlightDepartureAirportCode( reservation.getReturnFlightDepartureAirportCode() )
                .returnFlightDestinationCity( reservation.getReturnFlightDestinationCity() )
                .returnFlightDestinationAirportCode( reservation.getReturnFlightDestinationAirportCode() )
                .build();
    }
}
