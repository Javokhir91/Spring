package ru.gb.spring_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.gb.spring_demo.controller.IssueRequest;
import ru.gb.spring_demo.dao.BookRepository;
//import ru.gb.spring_demo.dao.BookRepositoryLocal;
//import ru.gb.spring_demo.dao.IssueRepositoryLocal;
import ru.gb.spring_demo.dao.IssueRepository;
//import ru.gb.spring_demo.dao.ReaderRepositoryLocal;
import ru.gb.spring_demo.dao.ReaderRepository;
import ru.gb.spring_demo.model.Book;
import ru.gb.spring_demo.model.Issue;
import ru.gb.spring_demo.model.IssueDTO;
import ru.gb.spring_demo.model.Reader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IssueService {

    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    @Autowired
    public IssueService(BookRepository bookRepository, IssueRepository issueRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.issueRepository = issueRepository;
        this.readerRepository = readerRepository;
    }

    public Issue saveIssue(Issue issue) {
        issueRepository.save(issue);
        return issue;
    }


    public Issue saveIssueReques(IssueRequest request) {
        if (bookRepository.findById(request.getBookId()).isEmpty()) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.findById(request.getReaderId()).isEmpty()) {
            throw new NoSuchElementException("Не найдена читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        // моно проверить, что у читателя нет книг на руках (или его лимит не привышает в Х книг)

        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.save(issue);
        return issue;
    }

    public Issue getIssueById(Long id) {
        return issueRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public ResponseEntity<?> issueBookToReader(Long readerId, Issue newIssue){
        if (readerRepository.findById(readerId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("409 - Conflict");
        }
        newIssue.setTimestamp(LocalDateTime.now());
        issueRepository.save(newIssue);
        return ResponseEntity.ok(newIssue);
    }

    public List<IssueDTO> getAllIssues(){
        List<Issue> issues = issueRepository.findAll();
        List<IssueDTO> issueDTOS = new ArrayList<>();
        for (Issue issue : issues) {
            Reader reader = readerRepository.findById(issue.getReaderId()).orElseThrow(NoSuchElementException::new);
            Book book = bookRepository.findById(issue.getBookId()).orElseThrow(NoSuchElementException::new);
            if (reader != null && book != null) {
                IssueDTO issueDTO = new IssueDTO(
                        issue.getId(),
                        book.getName(),
                        reader.getName(),
                        issue.getIssueAt(),
                        issue.getReturnAt()
                );
                issueDTOS.add(issueDTO);
            }
        }
        return issueDTOS;
    }
}
