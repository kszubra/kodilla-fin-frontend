package com.kodilla.kodillafinalfrontend.form;

import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.NotificationPreferenceFacade;
import com.kodilla.kodillafinalfrontend.domain.NotificationPreference;
import com.kodilla.kodillafinalfrontend.view.NotificationPreferencesView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class NotificationPreferencesForm extends FormLayout {
    private final NotificationPreferenceFacade preferencesFacade;
    private NotificationPreferencesView preferencesView;

    private TextField id = new TextField("id");
    private TextField userId = new TextField("userId");
    private TextField departureCity = new TextField("departureCity");
    private TextField destinationCity = new TextField("destinationCity");
    private TextField minTemperature = new TextField("minTemperature");
    private TextField maxPrice = new TextField("maxPrice");
    private Binder<NotificationPreference> binder = new Binder<>(NotificationPreference.class);

    Button save = new Button("Save", VaadinIcon.CHECK.create());

    @Autowired
    public NotificationPreferencesForm(NotificationPreferenceFacade facade, NotificationPreferencesView view) {
        this.preferencesFacade = facade;
        this.preferencesView = view;
        HorizontalLayout buttons = new HorizontalLayout(save);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(id, userId, departureCity,destinationCity, minTemperature, maxPrice, buttons);
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
    }

    public void setPreference(NotificationPreference preference) {
        binder.setBean(preference);

        if (preference == null) {
            setVisible(false);
        } else {
            setVisible(true);
            id.focus();
        }
    }

    void save() {
        NotificationPreference preference = binder.getBean();

        if(preference.getId().equals("")) {
            preferencesFacade.addPreference(preference);
        } else {
            preferencesFacade.updatePreference(preference);
        }

        preferencesView.refresh("");
        setPreference(null);
    }

}

