package com.kodilla.kodillafinalfrontend.backend.api.notification.preference.mapper;

import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceDto;
import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceListDto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class NotificationPreferenceMapper {

    public List<NotificationPreferenceDto> mapToNotificationPreferenceListFromNotificationPreferenceListDto(NotificationPreferenceListDto dto) {
        return dto.getPreferences();
    }

}

