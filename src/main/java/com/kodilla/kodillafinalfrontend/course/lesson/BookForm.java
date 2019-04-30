package com.kodilla.kodillafinalfrontend.course.lesson;

import com.kodilla.kodillafinalfrontend.course.lesson.books.MainView;
import com.kodilla.kodillafinalfrontend.course.lesson.domain.Book;
import com.kodilla.kodillafinalfrontend.course.lesson.service.BookService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class BookForm extends FormLayout {
    private MainView mainView;
    private BookService service = BookService.getInstance();

    private TextField title = new TextField("Title");
    private TextField author = new TextField("Author");
    private TextField publicationYear = new TextField("Publication year");
    private ComboBox<BookType> type = new ComboBox<>("Book type");
    private Binder<Book> binder = new Binder<>(Book.class); // Mówić ogólnie, mapuje on zmienne z formularza ze zmiennymi w obiekcie typu Book. Jeśli nazwy zmiennych nie są takie same w dwóch obiektach, możemy użyć adnotacji @PropertyId nad polem w formularzu – należy wskazać nazwę zmiennej z klasy, do której chcemy zmapować dane pole.

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    public void setBook(Book book) { //od ukrycia formularza, gdy nie jest on nam potrzebny
        binder.setBean(book);

        if (book == null) {
            setVisible(false);
        } else {
            setVisible(true);
            title.focus();
        }
    }

    private void save() {
        Book book = binder.getBean();
        service.save(book);
        mainView.refresh();
        setBook(null);
    }

    private void delete() {
        Book book = binder.getBean();
        service.delete(book);
        mainView.refresh();
        setBook(null);
    }

    public BookForm(MainView mainView) {
        this.mainView = mainView;

        type.setItems(BookType.values());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(title, author, publicationYear, type, buttons);
        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }


}
