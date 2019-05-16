package com.kodilla.kodillafinalfrontend;


import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentFacade;
import com.kodilla.kodillafinalfrontend.backend.api.payment.PaymentStatus;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@UIScope
public class PaymentsForm extends VerticalLayout implements KeyNotifier {
    private final PaymentFacade paymentFacade;

    /**
     * The currently edited payment
     */
    private Payment payment;

    private TextField id = new TextField("id");
    private TextField value = new TextField("value");
    private TextField paymentDate = new TextField("Payment date");
    private ComboBox<PaymentStatus> status = new ComboBox<>("Payment status");
    private Binder<Payment> binder = new Binder<>(Payment.class);

    /* Action buttons */
    // TODO why more code?
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    HorizontalLayout actions = new HorizontalLayout(save, cancel);

    private ChangeHandler changeHandler;

    @Autowired
    public PaymentsForm(PaymentFacade facade) {
        this.paymentFacade = facade;

        add(id, value, status, paymentDate, actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");

        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        cancel.addClickListener(e -> editPayment(payment));
        setVisible(false);
    }

    void save() {
        paymentFacade.addPayment(payment);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editPayment(Payment payment) {
        if (payment == null) {
            setVisible(false);
            return;
        }

        paymentFacade.updatePayment(payment);

        binder.setBean(payment);

        setVisible(true);

        // Focus first name initially
        id.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

}

