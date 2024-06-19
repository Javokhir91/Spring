package ru.gb.spring_demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Issue {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    private LocalDateTime issueAt;
    private LocalDateTime returnAt;

    public Issue() {
    }

    public Issue(Book book, Reader reader, LocalDateTime issueAt, LocalDateTime returnAt) {
        this.book = book;
        this.reader = reader;
        this.issueAt = issueAt;
        this.returnAt = returnAt;
    }
}
