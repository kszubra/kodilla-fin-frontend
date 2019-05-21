package com.kodilla.kodillafinalfrontend.form;

import com.kodilla.kodillafinalfrontend.backend.api.reservation.ReservationFacade;
import com.kodilla.kodillafinalfrontend.domain.Reservation;
import com.kodilla.kodillafinalfrontend.view.ReservationsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class ReservationsForm extends FormLayout {
    private final ReservationFacade reservationFacade;
    private ReservationsView reservationsView;

    private TextField name = new TextField("name");
    private TextField surname = new TextField("surname");
    private TextField email = new TextField("email");
    private TextField price = new TextField("price");

    private TextField thereFlightDepartureCity = new TextField("thereFlightDepartureCity");
    private TextField thereFlightDepartureAirportCode = new TextField("thereFlightDepartureAirportCode");
    private TextField thereFlightDestinationCity = new TextField("thereFlightDestinationCity");
    private TextField thereFlightDestinationAirportCode = new TextField("thereFlightDestinationAirportCode");
    private TextField thereFlightDate = new TextField("thereFlightDate");

    private TextField returnFlightDepartureCity = new TextField("returnFlightDepartureCity");
    private TextField returnFlightDepartureAirportCode = new TextField("returnFlightDepartureAirportCode");
    private TextField returnFlightDestinationCity = new TextField("returnFlightDestinationCity");
    private TextField returnFlightDestinationAirportCode = new TextField("returnFlightDestinationAirportCode");
    private TextField returnFlightDate = new TextField("returnFlightDate");


    private Binder<Reservation> binder = new Binder<>(Reservation.class);

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button delete = new Button("Delete", VaadinIcon.CHECK.create());

    @Autowired
    public ReservationsForm(ReservationFacade facade, ReservationsView view) {
        this.reservationFacade = facade;
        this.reservationsView = view;
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(
                name, surname, email, price,
                thereFlightDepartureCity, thereFlightDepartureAirportCode, thereFlightDestinationCity, thereFlightDestinationAirportCode,
                thereFlightDate, returnFlightDepartureCity, returnFlightDepartureAirportCode, returnFlightDestinationCity,
                returnFlightDestinationAirportCode, returnFlightDate, buttons
        );
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    public void setReservation(Reservation reservation) {
        binder.setBean(reservation);

        if (reservation == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }

    void save() {
        Reservation reservation = binder.getBean();

        if(reservation.isSafeToSave() && reservation.getId().equals("")) {
            reservationFacade.addReservation(reservation);
        } else if(reservation.isSafeToUpdate()) {
            reservationFacade.updateReservation(reservation);
        } else {
            Notification.show("Fields are not filled properly!");
        }

        reservationsView.refresh("");
        setReservation(null);
    }

    void delete() {
        Reservation reservation = binder.getBean();

        if(reservation.getId().chars().allMatch(Character::isDigit)) {
            reservationFacade.deleteReservation( Long.parseLong( reservation.getId() ) );
        }
        reservationsView.refresh("");
        setReservation(null);
    }

}
