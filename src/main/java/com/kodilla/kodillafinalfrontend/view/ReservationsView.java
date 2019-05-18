package com.kodilla.kodillafinalfrontend.view;

import com.kodilla.kodillafinalfrontend.form.ReservationsForm;
import com.kodilla.kodillafinalfrontend.backend.api.reservation.ReservationFacade;
import com.kodilla.kodillafinalfrontend.domain.Reservation;
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
public class ReservationsView extends VerticalLayout {
    private final ReservationFacade reservationFacade;
    private ReservationsForm reservationsForm;
    private Grid<Reservation> grid;
    private Button addNewReservation;

    TextField filterField = new TextField();

    public void refresh(String filterText) {
        if(StringUtils.isEmpty(filterText)) {
            grid.setItems(reservationFacade.getAllReservations());
        } else if ( filterText.chars().allMatch(Character::isDigit) ) {
            grid.setItems(reservationFacade.getReservationById( Long.parseLong( filterText ) ));
        } else {
            grid.setItems(reservationFacade.getReservationBySurname( filterText ));
        }
    }

    public ReservationsView(ReservationFacade reservationFacade) {
        this.reservationFacade = reservationFacade;
        this.reservationsForm = new ReservationsForm(this.reservationFacade, this);
        this.grid = new Grid<>(Reservation.class);
        this.addNewReservation = new Button("Add new Reservation");

        addNewReservation.addClickListener(e -> {
            grid.asSingleSelect().clear();
            reservationsForm.setReservation(new Reservation());
        });

        reservationsForm.setReservation(null);

        filterField.setPlaceholder("Filter by ID or surname");
        filterField.setClearButtonVisible(true);
        filterField.setValueChangeMode(ValueChangeMode.EAGER);
        filterField.addValueChangeListener(e -> refresh(e.getValue()));

        grid.setColumns("id", "thereFlightDepartureCity", "thereFlightDepartureAirportCode", "thereFlightDestinationCity",
                "thereFlightDestinationAirportCode", "thereFlightDate", "returnFlightDepartureCity", "returnFlightDepartureAirportCode",
                "returnFlightDestinationCity", "returnFlightDestinationAirportCode", "returnFlightDate", "name", "surname",
                "email", "price", "paymentId");
        HorizontalLayout toolbar = new HorizontalLayout(filterField, addNewReservation);
        HorizontalLayout mainContent = new HorizontalLayout(grid, reservationsForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        setSizeFull();
        refresh("");

        grid.asSingleSelect().addValueChangeListener(event -> reservationsForm.setReservation(grid.asSingleSelect().getValue()));

    }
}
