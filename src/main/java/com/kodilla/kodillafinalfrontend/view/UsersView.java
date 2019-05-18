package com.kodilla.kodillafinalfrontend.view;

import com.kodilla.kodillafinalfrontend.form.UsersForm;
import com.kodilla.kodillafinalfrontend.backend.api.user.UserFacade;
import com.kodilla.kodillafinalfrontend.domain.User;
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

@Component
@UIScope
@Route()
public class UsersView extends VerticalLayout {
    private final UserFacade userFacade;
    private UsersForm usersForm;
    private Grid<User> grid;
    private Button addNewUser;

    TextField filterField = new TextField();

    public void refresh(String filterText) {
        if(StringUtils.isEmpty(filterText)) {
            grid.setItems(userFacade.getAllUsers());
        } else if ( filterText.chars().allMatch(Character::isDigit) ) {
            grid.setItems(userFacade.getUserById( Long.parseLong( filterText ) ));
        } else {
            Notification.show("Filter text can be only a date byID number");
        }
    }

    public UsersView(UserFacade userFacade) {
        this.userFacade = userFacade;
        this.usersForm = new UsersForm(this.userFacade, this);
        this.grid = new Grid<>(User.class);
        this.addNewUser = new Button("Add new User");

        addNewUser.addClickListener(e -> {
            grid.asSingleSelect().clear();
            usersForm.setUser(new User());
        });

        usersForm.setUser(null);

        filterField.setPlaceholder("Filter by ID");
        filterField.setClearButtonVisible(true);
        filterField.setValueChangeMode(ValueChangeMode.EAGER);
        filterField.addValueChangeListener(e -> refresh(e.getValue()));

        grid.setColumns("id", "name", "surname", "email", "securePassword", "registered", "notificationIds");
        HorizontalLayout toolbar = new HorizontalLayout(filterField, addNewUser);
        HorizontalLayout mainContent = new HorizontalLayout(grid, usersForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        setSizeFull();
        refresh("");

        grid.asSingleSelect().addValueChangeListener(event -> usersForm.setUser(grid.asSingleSelect().getValue()));

    }
}
