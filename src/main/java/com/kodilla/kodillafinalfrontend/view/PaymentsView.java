package com.kodilla.kodillafinalfrontend.view;

import com.kodilla.kodillafinalfrontend.form.PaymentsForm;
import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentFacade;
import com.kodilla.kodillafinalfrontend.domain.Payment;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;


@Component
@UIScope
@Route()
public class PaymentsView extends VerticalLayout {
    private final PaymentFacade paymentFacade;
    private PaymentsForm paymentsForm;
    private Grid<Payment> grid;
    private Button addNewPayment;

    TextField filterField = new TextField();

    public void refresh(String filterText) {
        Pattern datePattern = Pattern.compile("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");

        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(paymentFacade.getPayments());
        }
        else if ( datePattern.matcher(filterText).matches() ) {
            grid.setItems(paymentFacade.getPaymentsByDate( filterText ));
        } else if ( filterText.chars().allMatch(Character::isDigit) ) {
            grid.setItems(paymentFacade.getPayment( Long.parseLong( filterText ) ));
        } else {
            Notification.show("Filter text can be only a date in \"YYYY-MM-DD\" format or ID number");
        }
    }

    public PaymentsView(PaymentFacade paymentFacade) {
        this.paymentFacade = paymentFacade;
        this.paymentsForm = new PaymentsForm(this.paymentFacade, this);
        this.grid = new Grid<>(Payment.class);
        this.addNewPayment = new Button("Add new Payment");

        addNewPayment.addClickListener(e -> {
            grid.asSingleSelect().clear(); //"czyścimy" zaznaczenie
            paymentsForm.setPayment(new Payment()); //dodajemy nowy obiekt do formularza
        });

        paymentsForm.setPayment(null);

        filterField.setPlaceholder("Filter by \"YYYY-MM-DD\" date or ID");
        filterField.setClearButtonVisible(true);
        filterField.setValueChangeMode(ValueChangeMode.EAGER);
        filterField.addValueChangeListener(e -> refresh(e.getValue()));

        grid.setColumns("id", "value", "status", "paymentDate");
        HorizontalLayout toolbar = new HorizontalLayout(filterField, addNewPayment);
        HorizontalLayout mainContent = new HorizontalLayout(grid, paymentsForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        setSizeFull();
        refresh("");

        grid.asSingleSelect().addValueChangeListener(event -> paymentsForm.setPayment(grid.asSingleSelect().getValue()));

    }





}

