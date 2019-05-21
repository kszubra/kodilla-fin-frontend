package com.kodilla.kodillafinalfrontend.form;

import com.kodilla.kodillafinalfrontend.backend.api.user.UserFacade;
import com.kodilla.kodillafinalfrontend.domain.User;
import com.kodilla.kodillafinalfrontend.view.UsersView;
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
public class UsersForm extends FormLayout {
    private final UserFacade userFacade;
    private UsersView usersView;

    private TextField name = new TextField("name");
    private TextField surname = new TextField("surname");
    private TextField email = new TextField("email");
    private TextField securePassword = new TextField("password");
    private TextField notificationsId = new TextField("notifications");

    private Binder<User> binder = new Binder<>(User.class);

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button delete = new Button("Delete", VaadinIcon.CHECK.create());

    @Autowired
    public UsersForm(UserFacade facade, UsersView view) {
        this.userFacade = facade;
        this.usersView = view;
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(name, surname, email, securePassword, notificationsId, buttons);
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    public void setUser(User user) {
        binder.setBean(user);

        if (user == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }

    void save() {
        User user = binder.getBean();

        if(user.isSafeToSave() && user.getId().equals("")) {
            userFacade.registerUser(user);
        } else if(user.isSafeToUpdate()) {
            userFacade.updateUser(user);
        } else {
            Notification.show("Fields are not filled properly!");
        }

        usersView.refresh("");
        setUser(null);
    }

    void delete() {
        User user = binder.getBean();

        if(user.getId().chars().allMatch(Character::isDigit)) {
            userFacade.deleteUser( Long.parseLong( user.getId() ) );
        }
        usersView.refresh("");
        setUser(null);
    }

}
