package com.kodilla.kodillafinalfrontend;

import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentFacade;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@Component
@Route //@Route określa, pod jakim linkiem będzie widoczny nasz widok. Brak wartości oznacza stronę główną,
public class PaymentView extends VerticalLayout {
    @Autowired
    private PaymentFacade paymentFacade;

    public PaymentView() {
        add( new Button("click", e-> Notification.show( String.valueOf( paymentFacade.getPayments() ) )) );
    }





}

