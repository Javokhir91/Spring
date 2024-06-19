package ru.gb.spring_demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssueDTO {

    private Long id;
    private Long bookId;
    private Long readerId;
    private LocalDateTime issueAt;
    private LocalDateTime returnAt = null;

    public IssueDTO(Long id, Long bookId, Long readerId, LocalDateTime returnAt) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.issueAt = LocalDateTime.now();
        this.returnAt = returnAt;
    }
}
