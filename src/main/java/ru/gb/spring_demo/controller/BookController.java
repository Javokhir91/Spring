package ru.gb.spring_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring_demo.model.Book;
import ru.gb.spring_demo.service.BookService;

import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequestMapping("/books")
//@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping("/id")
    public void deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
    }


    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        if (id != book.getId()) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + book.getId() + "\"");
        }
        return bookService.updateBook(book);
    }

}
