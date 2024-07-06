package org.exaple.api;

import lombok.Data;

import java.time.LocalDate;
import org.exaple.api.Book;
import java.util.UUID;


@Data
public class Issue {
    private UUID id;
    private LocalDate issueAt;
    private Book book;
    private Reader reader;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setIssueAt(LocalDate issueAt) {
        this.issueAt = issueAt;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public UUID getId() {
        return id;
    }


    public LocalDate getIssueAt() {
        return issueAt;
    }


}
