package ru.gb.service;

import java.util.List;
import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.model.Book;
import ru.gb.repository.BookRepository;


@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

    public Book getBookById(Long id) {
        Optional<Book> optionalBook =bookRepository.findById(id);
        if(optionalBook.isPresent()){
            return optionalBook.get();
        } else {
            throw new NullPointerException("Book not found.");
        }
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBooks(Long id, Book book){
        Book updateBook = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
        updateBook.setName(book.getName());
        return bookRepository.save(updateBook);
    }
}
