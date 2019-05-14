package com.kodilla.kodillafinalfrontend.backend.api.notification.preference;

import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceCreationDto;
import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceDto;
import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.mapper.NotificationPreferenceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NotificationPreferenceFacade {
    private final NotificationPreferenceClient notificationClient;
    private final NotificationPreferenceMapper notificationMapper;

    public NotificationPreferenceDto getPreferenceById(final Long id) {
        return notificationClient.getPreferenceById(id);
    }

    public Integer addPreference(NotificationPreferenceCreationDto dto) {
        return notificationClient.addPayment(dto);
    }

    public NotificationPreferenceDto updatePreference(NotificationPreferenceDto updatingDto) {
        return notificationClient.updatePreference(updatingDto);
    }

    public List<NotificationPreferenceDto> getAllPreferences() {
        return notificationMapper.mapToNotificationPreferenceListFromNotificationPreferenceListDto( notificationClient.getAllPreferences());
    }

    public List<NotificationPreferenceDto> getPreferencesByDestinationCity(final String city) {
        return notificationMapper.mapToNotificationPreferenceListFromNotificationPreferenceListDto( notificationClient.getPreferencesByDestinationCity(city));
    }

    public void deletePreference(final Long id) {
        notificationClient.deletePreference(id);
    }
}
