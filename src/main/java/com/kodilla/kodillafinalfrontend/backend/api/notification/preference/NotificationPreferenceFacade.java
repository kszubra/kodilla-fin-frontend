package com.kodilla.kodillafinalfrontend.backend.api.notification.preference;

import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceDto;
import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.mapper.NotificationPreferenceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationPreferenceFacade {
    private final NotificationPreferenceClient notificationClient;
    private final NotificationPreferenceMapper notificationMapper;

    public NotificationPreferenceDto getPreferenceById(final Long id) {
        return notificationClient.getPreferenceById(id);
    }
}
