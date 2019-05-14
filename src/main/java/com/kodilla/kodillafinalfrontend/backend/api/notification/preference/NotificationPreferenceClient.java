package com.kodilla.kodillafinalfrontend.backend.api.notification.preference;

import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceCreationDto;
import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceDto;
import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceListDto;
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
public class NotificationPreferenceClient {
    private final RestTemplate restTemplate;
    private final AdminConfig adminConfig;

    public NotificationPreferenceDto getPreferenceById(final Long id){
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/preferences/" + id)
                .build().encode().toUri();

        try{
            NotificationPreferenceDto response = restTemplate.getForObject(url, NotificationPreferenceDto.class);
            return Optional.ofNullable(response).orElse(new NotificationPreferenceDto());
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new NotificationPreferenceDto();
        }
    }

    public Integer addPayment(final NotificationPreferenceCreationDto dto){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NotificationPreferenceCreationDto> request = new HttpEntity<>(dto, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/preferences")
                .build().encode().toUri();

        try{
            return restTemplate.exchange(url, HttpMethod.POST, request, Integer.class).getStatusCodeValue();
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return -1;
        }

    }

    public NotificationPreferenceDto updatePreference(final NotificationPreferenceDto updatingDto){

        if(updatingDto.getId() > 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<NotificationPreferenceDto> request = new HttpEntity<>(updatingDto, headers);

            URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                    .path("/preferences")
                    .build().encode().toUri();

            try{
                return restTemplate.exchange(url, HttpMethod.PUT, request, NotificationPreferenceDto.class).getBody();
            } catch(RestClientException e) {
                log.error(e.getMessage(), e);
                return new NotificationPreferenceDto();
            }
        }

        return new NotificationPreferenceDto();
    }

    public NotificationPreferenceListDto getAllPreferences(){
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/preferences")
                .build().encode().toUri();

        try{
            NotificationPreferenceListDto response = restTemplate.getForObject(url, NotificationPreferenceListDto.class);
            return Optional.ofNullable(response).orElse(new NotificationPreferenceListDto(new ArrayList<>()));
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new NotificationPreferenceListDto(new ArrayList<>());
        }
    }

    public NotificationPreferenceListDto getPreferencesByDestinationCity(final String city){
            URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress() + "/preferences/")
                    .queryParam("city", city)
                    .build().encode().toUri();

            try{
                NotificationPreferenceListDto response = restTemplate.getForObject(url, NotificationPreferenceListDto.class);
                return Optional.ofNullable(response).orElse(new NotificationPreferenceListDto(new ArrayList<>()));
            } catch(RestClientException e) {
                log.error(e.getMessage(), e);
                return new NotificationPreferenceListDto(new ArrayList<>());
            }
    }

    public void deletePreference(final Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/preferences/" + id)
                .build().encode().toUri();
        try{
            restTemplate.delete(url);
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

}
