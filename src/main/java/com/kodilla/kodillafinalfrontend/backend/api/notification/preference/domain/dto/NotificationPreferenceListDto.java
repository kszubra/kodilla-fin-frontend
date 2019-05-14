package com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationPreferenceListDto {

    @JsonProperty("preferences")
    private List<NotificationPreferenceDto> preferences;
}
