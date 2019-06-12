package com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationPreferenceCreationDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("departureCity")
    private String departureCity;

    @JsonProperty("destinationCity")
    private String destinationCity;

    @JsonProperty("minTemperature")
    private Integer minTemperature;

    @JsonProperty("maxPrice")
    private BigDecimal maxPrice;
}
