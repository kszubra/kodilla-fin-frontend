package com.kodilla.kodillafinalfrontend.backend.api.payment;

import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto.PaymentDto;
import com.kodilla.kodillafinalfrontend.backend.api.payment.domain.dto.PaymentListDto;
import com.kodilla.kodillafinalfrontend.config.AdminConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

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

    public PaymentListDto getPayments(){
        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/payments")
                .build().encode().toUri();

        try{
            PaymentListDto response = restTemplate.getForObject(url, PaymentListDto.class);
            return Optional.ofNullable(response).orElse(new PaymentListDto());
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return new PaymentListDto();
        }

    }

    public PaymentListDto getPaymentsByDate(final String date){
        Pattern datePattern = Pattern.compile("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");

        if( datePattern.matcher( date ).matches() ) {

            URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress() + "/payments/")
                    .queryParam("date", date)
                    .build().encode().toUri();

            try{
                PaymentListDto response = restTemplate.getForObject(url, PaymentListDto.class);
                return Optional.ofNullable(response).orElse(new PaymentListDto());
            } catch(RestClientException e) {
                log.error(e.getMessage(), e);
                return new PaymentListDto();
            }
        }

        return new PaymentListDto( new ArrayList<>());
    }

    public Integer addPayment(final PaymentDto dto){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentDto> request = new HttpEntity<>(dto, headers);

        URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                .path("/payments")
                .build().encode().toUri();

        try{
            return restTemplate.exchange(url, HttpMethod.POST, request, Integer.class).getStatusCodeValue();
        } catch(RestClientException e) {
            log.error(e.getMessage(), e);
            return -1;
        }

    }

    public PaymentDto updatePayment(final PaymentDto updatingDto){

        if(updatingDto.getId() > 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<PaymentDto> request = new HttpEntity<>(updatingDto, headers);

            URI url = UriComponentsBuilder.fromHttpUrl(adminConfig.getBackendHostAddress())
                    .path("/payments")
                    .build().encode().toUri();

            try{
                return restTemplate.exchange(url, HttpMethod.PUT, request, PaymentDto.class).getBody();
            } catch(RestClientException e) {
                log.error(e.getMessage(), e);
                return new PaymentDto();
            }
        }

        return new PaymentDto();
    }





}
