package com.kodilla.kodillafinalfrontend.backend.api.notification.preference;

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
        NotificationPreferenceDto testPreference = preferenceFacade.getPreferenceById(12L);

        //Then
        System.out.println(testPreference);
    }

    @Test
    public void testAddPreference() {
        //Given & When
        NotificationPreferenceCreationDto creationDto = NotificationPreferenceCreationDto.builder()
                .userId(26L)
                .minTemperature(15)
                .maxPrice(BigDecimal.valueOf(700))
                .departureCity("Wrocław")
                .destinationCity("Paris")
                .build();

        //When
        int result = preferenceFacade.addPreference(creationDto);

        //Then
        assertEquals(200, result);
    }

    @Test
    public void testUpdatingPreference() {
        //Given & When
        NotificationPreferenceDto updatingDto = preferenceFacade.getPreferenceById(27L);
        updatingDto.setMaxPrice(BigDecimal.valueOf(900));

        //When
        NotificationPreferenceDto result = preferenceFacade.updatePreference(updatingDto);

        //Then
        assertEquals(BigDecimal.valueOf(900).setScale(2, RoundingMode.HALF_EVEN), result.getMaxPrice().setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void testGettingAllPreference() {
        //Given & When
        List<NotificationPreferenceDto> preferences = preferenceFacade.getAllPreferences();

        //Then
        preferences.forEach(System.out::println);
    }

    @Test
    public void testGettingPreferencesByCity() {
        //Given & When
        List<NotificationPreferenceDto> preferences = preferenceFacade.getPreferencesByDestinationCity("Paris");

        //Then
        preferences.forEach(System.out::println);
    }

    @Test
    public void testDeletingPreference() {
        //Given & When
        preferenceFacade.deletePreference(28L);

    }

}