package com.kodilla.kodillafinalfrontend.view;

import com.kodilla.kodillafinalfrontend.form.NotificationPreferencesForm;
import com.kodilla.kodillafinalfrontend.backend.api.notification.preference.NotificationPreferenceFacade;
import com.kodilla.kodillafinalfrontend.domain.NotificationPreference;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
@UIScope
@Route()
public class NotificationPreferencesView extends VerticalLayout {
    private final NotificationPreferenceFacade preferencesFacade;
    private NotificationPreferencesForm preferencesForm;
    private Grid<NotificationPreference> grid;
    private Button addNewPreference;

    TextField filterField = new TextField();

    public void refresh(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(preferencesFacade.getAllPreferences());
        }
        else if ( filterText.chars().allMatch(Character::isDigit) ) {
            grid.setItems(preferencesFacade.getPreferenceById( Long.parseLong( filterText ) ));
        } else {
            grid.setItems(preferencesFacade.getPreferencesByDestinationCity( filterText ));
        }
    }

    public NotificationPreferencesView(NotificationPreferenceFacade preferencesFacade) {
        this.preferencesFacade = preferencesFacade;
        this.preferencesForm = new NotificationPreferencesForm(this.preferencesFacade, this);
        this.grid = new Grid<>(NotificationPreference.class);
        this.addNewPreference = new Button("Add new Preference");

        addNewPreference.addClickListener(e -> {
            grid.asSingleSelect().clear();
            preferencesForm.setPreference(new NotificationPreference());
        });

        preferencesForm.setPreference(null);

        filterField.setPlaceholder("Filder by ID or destination city name");
        filterField.setClearButtonVisible(true);
        filterField.setValueChangeMode(ValueChangeMode.EAGER);
        filterField.addValueChangeListener(e -> refresh(e.getValue()));

        grid.setColumns("id", "userId", "departureCity","destinationCity", "minTemperature", "maxPrice");
        HorizontalLayout toolbar = new HorizontalLayout(filterField, addNewPreference);
        HorizontalLayout mainContent = new HorizontalLayout(grid, preferencesForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        setSizeFull();
        refresh("");

        grid.asSingleSelect().addValueChangeListener(event -> preferencesForm.setPreference(grid.asSingleSelect().getValue()));

    }





}
