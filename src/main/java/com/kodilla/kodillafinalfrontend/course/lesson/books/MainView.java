package com.kodilla.kodillafinalfrontend.course.lesson.books;

import com.kodilla.kodillafinalfrontend.course.lesson.domain.Book;
import com.kodilla.kodillafinalfrontend.course.lesson.service.BookService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route //@Route określa, pod jakim linkiem będzie widoczny nasz widok. Brak wartości oznacza stronę główną,
public class MainView extends VerticalLayout {
    private BookService bookService = BookService.getInstance();
    private Grid<Book> grid = new Grid<>(Book.class);
    private TextField filter = new TextField();

    public void refresh() {
        grid.setItems(bookService.getBooks());
    }

    private void update() {
        grid.setItems(bookService.findByTitle(filter.getValue()));
    }

    public MainView() {
        //add( new Button("Click me", e-> Notification.show("Hello World")));
        grid.setColumns("title", "author", "publicationYear", "type");
        add(grid);
        setSizeFull();
        refresh();
        filter.setPlaceholder("Filter by title");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        add(filter, grid);
    }

}
