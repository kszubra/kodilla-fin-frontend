package com.kodilla.kodillafinalfrontend.backend.api.notification.preference;

import com.kodilla.kodillafinalfrontend.NotificationPreference;

import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.mapper.NotificationPreferenceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NotificationPreferenceFacade {
    private final NotificationPreferenceClient notificationClient;
    private final NotificationPreferenceMapper notificationMapper;

    public NotificationPreference getPreferenceById(final Long id) {
        return notificationMapper.mapToNotificationPreference( notificationClient.getPreferenceById(id) );
    }

    public Integer addPreference(NotificationPreference preference) {
        return notificationClient.addPreference( notificationMapper.mapToCreationDto(preference) );
    }

    public NotificationPreference updatePreference(NotificationPreference preference) {
        return notificationMapper.mapToNotificationPreference(notificationClient.updatePreference(notificationMapper.mapToDto(preference)));
    }

    public List<NotificationPreference> getAllPreferences() {
        return notificationMapper.mapToNotificationPreferenceListFromNotificationPreferenceListDto( notificationClient.getAllPreferences());
    }

    public List<NotificationPreference> getPreferencesByDestinationCity(final String city) {
        return notificationMapper.mapToNotificationPreferenceListFromNotificationPreferenceListDto( notificationClient.getPreferencesByDestinationCity(city));
    }

    public void deletePreference(final Long id) {
        notificationClient.deletePreference(id);
    }
}
