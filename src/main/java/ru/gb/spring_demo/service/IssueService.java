package ru.gb.spring_demo.service;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.gb.spring_demo.controller.IssueRequest;
import ru.gb.spring_demo.dao.BookRepository;
import ru.gb.spring_demo.dao.IssueRepository;
import ru.gb.spring_demo.dao.ReaderRepository;
import ru.gb.spring_demo.model.Book;
import ru.gb.spring_demo.model.Issue;
import ru.gb.spring_demo.model.Reader;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
@Data
@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public IssueService(IssueRepository issueRepository, BookRepository bookRepository, ReaderRepository readerRepository) {
        this.issueRepository = issueRepository;
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public Issue createIssue(IssueRequest issueRequest) {
        Book book = bookRepository.findById(issueRequest.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + issueRequest.getBookId()));
        Reader reader = readerRepository.findById(issueRequest.getReaderId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid reader ID: " + issueRequest.getReaderId()));

        Issue issue = new Issue();
        issue.setBook(book);
        issue.setReader(reader);
        issue.setIssueAt(LocalDateTime.now());
        issue.setReturnAt(issueRequest.getReturnAt() != null ? issueRequest.getReturnAt() : null);

        return issueRepository.save(issue);
    }

    public Issue getIssueById(Long id) {
        return issueRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public ResponseEntity<?> issueBookToReader(Long readerId, Issue newIssue) {
        if (readerRepository.findById(readerId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("409 - Conflict");
        }
        newIssue.setIssueAt(LocalDateTime.now());
        issueRepository.save(newIssue);
        return ResponseEntity.ok(newIssue);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);
    }

    public Issue updateIssue(Issue issue) {
        return issueRepository.save(issue);
    }

}
