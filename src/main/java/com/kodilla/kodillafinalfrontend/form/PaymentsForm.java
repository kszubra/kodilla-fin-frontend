package com.kodilla.kodillafinalfrontend.form;


import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentFacade;
import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentStatus;
import com.kodilla.kodillafinalfrontend.domain.Payment;
import com.kodilla.kodillafinalfrontend.view.PaymentsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
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
public class PaymentsForm extends FormLayout {
    private final PaymentFacade paymentFacade;
    private PaymentsView paymentsView;

    private TextField value = new TextField("value");
    private TextField paymentDate = new TextField("Payment date");
    private ComboBox<PaymentStatus> status = new ComboBox<>("Payment status");
    private Binder<Payment> binder = new Binder<>(Payment.class);

    Button save = new Button("Save", VaadinIcon.CHECK.create());

    @Autowired
    public PaymentsForm(PaymentFacade facade, PaymentsView view) {
        this.paymentFacade = facade;
        this.paymentsView = view;
        status.setItems(PaymentStatus.values());
        HorizontalLayout buttons = new HorizontalLayout(save);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(value, status, paymentDate, buttons);
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
    }

    public void setPayment(Payment payment) {
        binder.setBean(payment);

        if (payment == null) {
            setVisible(false);
        } else {
            setVisible(true);
            value.focus();
        }
    }

    void save() {
        Payment payment = binder.getBean();

        if(payment.isSafeToSave() && payment.getId().equals("")) {
            paymentFacade.addPayment(payment);
        } else if(payment.isSafeToUpdate()) {
            paymentFacade.updatePayment(payment);
        } else {
            Notification.show("Fields are not filled properly!");
        }

        paymentsView.refresh("");
        setPayment(null);
    }

}

