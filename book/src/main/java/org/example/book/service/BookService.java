package org.example.book.service;

import org.example.book.entity.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooks();

    public Book getBookById(Long id);

    public Book saveBook(Book Book);

    public void deleteBook(Long id);
}
