package com.kodilla.kodillafinalfrontend;

import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentFacade;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@Component
@Route //@Route określa, pod jakim linkiem będzie widoczny nasz widok. Brak wartości oznacza stronę główną,
public class PaymentView extends VerticalLayout {
    @Autowired
    private PaymentFacade paymentFacade;
    @Autowired
    private PaymentForm form;

    //private PaymentForm form = new PaymentForm(this); //TO??
    private Grid<Payment> grid = new Grid<>(Payment.class);
    private TextField filter = new TextField();
    private Button addNewPayment = new Button("Add new payment");


    public void refresh() {
        grid.setItems(paymentFacade.getPayments());
    }

    private void update() {
        grid.setItems(paymentFacade.getPaymentsByDate(filter.getValue()));
    }

    public PaymentView() {
        //add( new Button("Click me", e-> Notification.show("Hello World")));
        filter.setPlaceholder("Filter by date...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("id", "value", "status", "paymentDate");

        addNewPayment.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setPayment(new Payment());
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewPayment);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setPayment(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setPayment(grid.asSingleSelect().getValue()));
    }

}

