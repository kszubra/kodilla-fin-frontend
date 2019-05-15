package com.kodilla.kodillafinalfrontend;

import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentFacade;
import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentStatus;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@NoArgsConstructor
public class PaymentForm extends FormLayout {
    @Autowired
    private PaymentView paymentView;
    @Autowired
    private PaymentFacade paymentFacade;

    private TextField id = new TextField("id");
    private TextField value = new TextField("value");
    private ComboBox<PaymentStatus> status = new ComboBox<>("status");
    private TextField paymentDate = new TextField("paymentDate");
    private Binder<Payment> binder = new Binder<>(Payment.class); // Mówić ogólnie, mapuje on zmienne z formularza ze zmiennymi w obiekcie typu Book. Jeśli nazwy zmiennych nie są takie same w dwóch obiektach, możemy użyć adnotacji @PropertyId nad polem w formularzu – należy wskazać nazwę zmiennej z klasy, do której chcemy zmapować dane pole.

    private Button save = new Button("Save");

    public void setPayment(Payment payment) { //od ukrycia formularza, gdy nie jest on nam potrzebny
        binder.setBean(payment);

        if (payment == null) {
            setVisible(false);
        } else {
            setVisible(true);
            id.focus();
        }
    }

    private void save() {
        Payment payment = binder.getBean();
        paymentFacade.addPayment(payment);
        paymentView.refresh();
        setPayment(null);
    }


    public PaymentForm(PaymentView paymentView) {
        this.paymentView = paymentView;

        status.setItems(PaymentStatus.values());
        HorizontalLayout buttons = new HorizontalLayout(save);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(id, value, status, paymentDate, buttons);
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
    }


}
