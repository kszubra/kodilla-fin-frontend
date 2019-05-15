package com.kodilla.kodillafinalfrontend.course.lesson.books;

import com.kodilla.kodillafinalfrontend.course.lesson.BookForm;
import com.kodilla.kodillafinalfrontend.course.lesson.domain.Book;
import com.kodilla.kodillafinalfrontend.course.lesson.service.BookService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

 //@Route określa, pod jakim linkiem będzie widoczny nasz widok. Brak wartości oznacza stronę główną,
public class MainView extends VerticalLayout {
    private BookService bookService = BookService.getInstance();
    private BookForm form = new BookForm(this);
    private Grid<Book> grid = new Grid<>(Book.class);
    private TextField filter = new TextField();
    private Button addNewBook = new Button("Add new book");


    public void refresh() {
        grid.setItems(bookService.getBooks());
    }

    private void update() {
        grid.setItems(bookService.findByTitle(filter.getValue()));
    }


     /**
      *
     public MainView() {
        //add( new Button("Click me", e-> Notification.show("Hello World")));
        filter.setPlaceholder("Filter by title...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("title", "author", "publicationYear", "type");

        addNewBook.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setBook(new Book());
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBook);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setBook(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setBook(grid.asSingleSelect().getValue()));
    } */

}
