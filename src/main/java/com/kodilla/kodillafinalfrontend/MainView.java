package com.kodilla.kodillafinalfrontend;

import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentFacade;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;


@Component
@UIScope
@Route()
public class MainView extends VerticalLayout { // address depends on class name, with  "PaymentView" address was http://localhost:8081/payment
    private final PaymentFacade paymentFacade;
    private Grid<Payment> grid = new Grid<>(Payment.class);

    public void refresh() {
        grid.setItems( paymentFacade.getPayments() );
    }

    public MainView(PaymentFacade paymentFacade) {
        this.paymentFacade = paymentFacade;

        //add( new Button("Show payments", e-> grid.setItems( paymentFacade.getPayments() ) ) );
        grid.setColumns("id", "value", "status", "paymentDate");
        add(grid);

        setSizeFull();

        refresh();
    }





}

