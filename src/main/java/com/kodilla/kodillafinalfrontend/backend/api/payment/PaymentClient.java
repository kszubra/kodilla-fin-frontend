package com.kodilla.kodillafinalfrontend.backend.api.payment;

import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto.PaymentDto;
import com.kodilla.kodillafinalfrontend.config.AdminConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class PaymentClient {
    private final RestTemplate restTemplate;
    private final AdminConfig adminConfig;

    public PaymentDto getPayment(final Long id){
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/payments/" + id)
                .build().encode().toUri();

        try{
            PaymentDto response = restTemplate.getForObject(url, PaymentDto.class);
            return Optional.ofNullable(response).orElse(new PaymentDto());
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new PaymentDto();
        }

    }

    public Object getPayments(){
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/payments")
                .build().encode().toUri();

        try{
            Object response = restTemplate.getForObject(url, Object.class);
            return Optional.ofNullable(response).orElse(new Object());
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new Object();
        }

    }

}
