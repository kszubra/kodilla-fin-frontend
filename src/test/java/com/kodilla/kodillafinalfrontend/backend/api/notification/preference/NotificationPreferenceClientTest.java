package com.kodilla.kodillafinalfrontend.backend.api.notification.preference;

import com.kodilla.kodillafinalfrontend.NotificationPreference;
import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceCreationDto;
import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.domain.dto.NotificationPreferenceDto;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationPreferenceClientTest {
    @Autowired
    private NotificationPreferenceFacade preferenceFacade;

    @Test
    public void testGetPreferenceById() {
        //Given & When
        NotificationPreference testPreference = preferenceFacade.getPreferenceById(12L);

        //Then
        System.out.println(testPreference);
    }

    @Test
    public void testAddPreference() {
        //Given & When
        NotificationPreference preferenceToAdd = NotificationPreference.builder()
                .userId("27")
                .minTemperature("15")
                .maxPrice("700")
                .departureCity("Warsaw")
                .destinationCity("Paris")
                .build();

        //When
        int result = preferenceFacade.addPreference(preferenceToAdd);

        //Then
        assertEquals(200, result);
    }

    @Test
    public void testUpdatingPreference() {
        //Given & When
        NotificationPreference updating = preferenceFacade.getPreferenceById(27L);
        updating.setMaxPrice("900");

        //When
        NotificationPreference result = preferenceFacade.updatePreference(updating);

        //Then
        assertEquals("900", result.getMaxPrice());
    }

    @Test
    public void testGettingAllPreference() {
        //Given & When
        List<NotificationPreference> preferences = preferenceFacade.getAllPreferences();

        //Then
        preferences.forEach(System.out::println);
    }

    @Test
    public void testGettingPreferencesByCity() {
        //Given & When
        List<NotificationPreference> preferences = preferenceFacade.getPreferencesByDestinationCity("Paris");

        //Then
        preferences.forEach(System.out::println);
    }

    @Test
    public void testDeletingPreference() {
        //Given & When
        preferenceFacade.deletePreference(28L);

    }

}