package com.kodilla.kodillafinalfrontend.domain;

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

    public boolean isSafeToUpdate() {
        return !id.isEmpty() &&
                !registered.isEmpty() &&
                this.alwaysRequiredFieldsAreFilled();
    }

    public boolean isSafeToSave() {
        return id.isEmpty() &&
                registered.isEmpty() &&
                this.alwaysRequiredFieldsAreFilled();
    }

    private boolean alwaysRequiredFieldsAreFilled() {
        return !( name.isEmpty() |
                    surname.isEmpty() |
                    email.isEmpty() |
                    securePassword.isEmpty()
                );
    }

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
