package com.kodilla.kodillafinalfrontend.backend.api.user;

import com.kodilla.kodillafinalfrontend.backend.api.user.domain.dto.UserDto;
import com.kodilla.kodillafinalfrontend.backend.api.user.domain.dto.UserListDto;
import com.kodilla.kodillafinalfrontend.backend.api.user.domain.dto.UserRegistrationDto;
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
public class UserClient {
    private final RestTemplate restTemplate;
    private final AdminConfig adminConfig;

    public Integer registerUser(final UserRegistrationDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserRegistrationDto> request = new HttpEntity<>(dto, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/users")
                .build().encode().toUri();

        try{
            return restTemplate.exchange(url, HttpMethod.POST, request, Integer.class).getStatusCodeValue();
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return -1;
        }
    }

    public UserDto updateUser(final UserDto updatingDto){

        if(updatingDto.getId() > 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<UserDto> request = new HttpEntity<>(updatingDto, headers);

            URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                    .path("/users")
                    .build().encode().toUri();

            try{
                return restTemplate.exchange(url, HttpMethod.PUT, request, UserDto.class).getBody();
            } catch(RestClientException e) {
                log.error(e.getMessage(), e);
                return new UserDto();
            }
        }

        return new UserDto();
    }

    public UserDto getUserById(final Long id){
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/users/" + id)
                .build().encode().toUri();

        try{
            UserDto response = restTemplate.getForObject(url, UserDto.class);
            return Optional.ofNullable(response).orElse(new UserDto());
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new UserDto();
        }
    }

    public UserListDto getAllUsers() {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/users")
                .build().encode().toUri();
        try{
            UserListDto response = restTemplate.getForObject(url, UserListDto.class);
            return Optional.ofNullable(response).orElse(new UserListDto(new ArrayList<>()));
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new UserListDto(new ArrayList<>());
        }
    }

    public void deleteUser(final Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/users/" + id)
                .build().encode().toUri();
        try{
            restTemplate.delete(url);
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
