package service;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final List<Book> books;

    public BookService() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
        }
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Book> getBooksByLanguage(String language) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getLanguage().equalsIgnoreCase(language)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }
}
