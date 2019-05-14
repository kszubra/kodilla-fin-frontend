package com.kodilla.kodillafinalfrontend.backend.api.reservation;

import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.dto.ReservationCreationDto;
import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.dto.ReservationDto;
import com.kodilla.kodillafinalfrontend.backend.api.reservation.domain.dto.ReservationListDto;
import com.kodilla.kodillafinalfrontend.config.AdminConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class ReservationClient {
    private final RestTemplate restTemplate;
    private final AdminConfig adminConfig;

    public ReservationDto getReservationById(final Long id){
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/reservations/" + id)
                .build().encode().toUri();

        try{
            ReservationDto response = restTemplate.getForObject(url, ReservationDto.class);
            return Optional.ofNullable(response).orElse(new ReservationDto());
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new ReservationDto();
        }
    }

    public ReservationListDto getReservations(){
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/reservations")
                .build().encode().toUri();

        try{
            ReservationListDto response = restTemplate.getForObject(url, ReservationListDto.class);
            return Optional.ofNullable(response).orElse(new ReservationListDto(new ArrayList<>()));
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new ReservationListDto(new ArrayList<>());
        }
    }

    public ReservationListDto getReservationsBySurname(final String surname){
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress() + "/reservations/")
                .queryParam("surname", surname)
                .build().encode().toUri();

        try{
            ReservationListDto response = restTemplate.getForObject(url, ReservationListDto.class);
            return Optional.ofNullable(response).orElse(new ReservationListDto(new ArrayList<>()));
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new ReservationListDto(new ArrayList<>());
        }
    }

    public Integer addReservation(final ReservationCreationDto dto){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ReservationCreationDto> request = new HttpEntity<>(dto, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/reservations")
                .build().encode().toUri();

        try{
            return restTemplate.exchange(url, HttpMethod.POST, request, Integer.class).getStatusCodeValue();
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return -1;
        }
    }

    public ReservationDto updateReservation(final ReservationDto updatingDto){

        if(updatingDto.getId() > 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<ReservationDto> request = new HttpEntity<>(updatingDto, headers);

            URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                    .path("/reservations")
                    .build().encode().toUri();

            try{
                return restTemplate.exchange(url, HttpMethod.PUT, request, ReservationDto.class).getBody();
            } catch(RestClientException e) {
                log.error(e.getMessage(), e);
                return new ReservationDto();
            }
        }
        return new ReservationDto();
    }
}
