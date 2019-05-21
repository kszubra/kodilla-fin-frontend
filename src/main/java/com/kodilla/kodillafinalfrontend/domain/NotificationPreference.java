package com.kodilla.kodillafinalfrontend.domain;

import lombok.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class NotificationPreference {

    private String id;
    private String userId;
    private String departureCity;
    private String destinationCity;
    private String minTemperature;
    private String maxPrice;

    public boolean isSafeToUpdate() {
        return userId != null && this.isSafeToSave();
    }

    public boolean isSafeToSave() {
        return userId != null &&
                departureCity != null &&
                destinationCity != null &&
                minTemperature != null &&
                maxPrice != null;
    }

    @Override
    public String toString() {
        return "NotificationPreference{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                ", minTemperature='" + minTemperature + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationPreference that = (NotificationPreference) o;
        return id.equals(that.id) &&
                userId.equals(that.userId) &&
                departureCity.equals(that.departureCity) &&
                destinationCity.equals(that.destinationCity) &&
                minTemperature.equals(that.minTemperature) &&
                maxPrice.equals(that.maxPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, departureCity, destinationCity, minTemperature, maxPrice);
    }
}
