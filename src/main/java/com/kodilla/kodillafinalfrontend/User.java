package com.kodilla.kodillafinalfrontend;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {

    private String id;
    private String name;
    private String surname;
    private String email;
    private String securePassword;
    private String registered;
    private Set<String> notificationIds;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", securePassword='" + securePassword + '\'' +
                ", registered='" + registered + '\'' +
                ", notificationIds=" + notificationIds +
                '}';
    }
}
