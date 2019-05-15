package com.kodilla.kodillafinalfrontend;

import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentFacade;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) //so it doesn't throw assertion error when refreshing site
@Route()
public class MainView extends VerticalLayout { // address depends on class name, with  "PaymentView" address was http://localhost:8081/payment
    @Autowired
    private PaymentFacade paymentFacade;
    private Grid<Payment> grid = new Grid<>(Payment.class);

    public void refresh() {
        grid.setItems( paymentFacade.getPayments() );
    }

    public MainView() {
        add( new Button("Show payments", e-> grid.setItems( paymentFacade.getPayments() ) ) );
        grid.setColumns("id", "value", "status", "paymentDate");
        add(grid);

        setSizeFull();

        //refresh();
    }





}

