package com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.kodillafinalfrontend.backend.api.user.domain.dto.UserDto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationPreferenceDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("userDto")
    private UserDto userDto;

    @JsonProperty("departureCity")
    private String departureCity;

    @JsonProperty("destinationCity")
    private String destinationCity;

    @JsonProperty("minTemperature")
    private Integer minTemperature;

    @JsonProperty("maxPrice")
    private BigDecimal maxPrice;

    @Override
    public String toString() {
        return "NotificationPreferenceDto{" +
                "id=" + id +
                ", userDto=" + userDto +
                ", departureCity='" + departureCity + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
