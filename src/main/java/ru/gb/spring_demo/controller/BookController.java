package ru.gb.spring_demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring_demo.model.Book;
import ru.gb.spring_demo.service.BookService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {



    private final BookService bookService;

//    @Autowired
//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @DeleteMapping("/id")
    public void deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

//
//    @PostMapping("/issue")
//    public void issueBook(@RequestBody IssueRequest request) {
//        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());
//    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        if (id != book.getId()) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + book.getId() + "\"");
        }
        return bookService.updateBook(book);
    }

}
