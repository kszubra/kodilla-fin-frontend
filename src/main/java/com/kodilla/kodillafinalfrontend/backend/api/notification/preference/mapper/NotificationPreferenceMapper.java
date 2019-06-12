package com.kodilla.kodillafinalfrontend.backend.api.notification.preference.mapper;

import com.kodilla.kodillafinalfrontend.domain.NotificationPreference;
import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceCreationDto;
import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceDto;
import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceListDto;

import com.kodilla.kodillafinalfrontend.backend.api.user.UserClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class NotificationPreferenceMapper {
    private final UserClient userClient;

    public List<NotificationPreference> mapToNotificationPreferenceListFromNotificationPreferenceListDto(NotificationPreferenceListDto dto) {
        return this.mapToNotificationPreferenceList( dto.getPreferences() );
    }

    public NotificationPreference mapToNotificationPreference(NotificationPreferenceDto dto){
        return NotificationPreference.builder()
                .id( dto.getId().toString() )
                .departureCity( dto.getDepartureCity() )
                .destinationCity( dto.getDestinationCity() )
                .minTemperature( dto.getMinTemperature().toString() )
                .maxPrice( dto.getMaxPrice().setScale(2, RoundingMode.HALF_EVEN).toString() )
                .userId( dto.getUserDto().getId().toString() )
                .build();
    }

    public List<NotificationPreference> mapToNotificationPreferenceList(List<NotificationPreferenceDto> dtoList) {
        return dtoList.stream()
                .map(this::mapToNotificationPreference)
                .collect(Collectors.toList());
    }

    public NotificationPreferenceDto mapToDto(NotificationPreference preference) {
        return NotificationPreferenceDto.builder()
                .id( (preference.getId().equals(""))? null : Long.parseLong(preference.getId()) )
                .departureCity( preference.getDepartureCity() )
                .destinationCity( preference.getDestinationCity() )
                .minTemperature( Integer.parseInt( preference.getMinTemperature() ) )
                .maxPrice( BigDecimal.valueOf( Double.parseDouble( preference.getMaxPrice() ) ) )
                .userDto( userClient.getUserById( Long.parseLong( preference.getUserId() ) ) )
                .build();
    }

    public NotificationPreferenceCreationDto mapToCreationDto(NotificationPreference preference) {
        return NotificationPreferenceCreationDto.builder()
                .departureCity( preference.getDepartureCity() )
                .destinationCity( preference.getDestinationCity() )
                .minTemperature( Integer.parseInt( preference.getMinTemperature() ) )
                .maxPrice( BigDecimal.valueOf( Double.parseDouble( preference.getMaxPrice() ) ) )
                .userId( Long.parseLong( preference.getUserId() ) )
                .build();
    }







}

