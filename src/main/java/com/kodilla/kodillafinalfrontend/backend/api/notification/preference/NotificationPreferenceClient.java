package com.kodilla.kodillafinalfrontend.backend.api.notification.preference;

import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceDto;
import com.kodilla.kodillafinalfrontend.config.AdminConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
}
