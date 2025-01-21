package org.example.book.serviceImpl;

import org.example.book.entity.Book;
import org.example.book.kafka.BookKafkaProducer;
import org.example.book.repository.BookRepository;
import org.example.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookKafkaProducer bookKafkaProducer;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public Book saveBook(Book book) {
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteBookKafka(Long id) {
        bookKafkaProducer.sendBookDeleteEvent(id);
        bookRepository.deleteById(id);
    }
}
