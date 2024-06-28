package ru.gb.springdemo.controller;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Запрос на выдачу
 */
@Data
public class IssueRequest {
    /**
     * Идентификатор читателя
     */
    private Long readerId;
    /**
     * Идентификатор книги
     */
    private Long bookId;

    private LocalDateTime issueAt;

    private LocalDateTime returnAt;
}
