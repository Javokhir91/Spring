package ru.gb.spring_demo.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring_demo.model.Issue;
import ru.gb.spring_demo.service.IssueService;
import java.time.LocalDateTime;

@Data
@Slf4j
@Controller
@RequestMapping("/issue")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public String getIssues(Model model) {
        model.addAttribute("issues", issueService.getAllIssues());
        return "issues";
    }

    @GetMapping("/{id}")
    public String getIssueById(@PathVariable("id") Long id, Model model) {
        Issue issue = issueService.getIssueById(id);
        if (issue == null) {
            // выяснить на счет new
            new ResponseEntity<>(HttpStatus.NOT_FOUND); //ResponseEntity.notFound().build();
        } else {
            model.addAttribute("issue", issueService.getIssueById(id));
            new ResponseEntity<>(issue, HttpStatus.OK);  //ResponseEntity.ok(issue);
        }
        return "issues";
    }

    @PostMapping
    public ResponseEntity<?> createIssue(@RequestBody IssueRequest issueRequest) {
        Issue issue = issueService.createIssue(issueRequest);
        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issue> updateIssue(@PathVariable Long id) {
        Issue issue = issueService.getIssueById(id);
        if (issue == null) {
            // выяснить на счет new
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //ResponseEntity.notFound().build();
        } else {
            issue.setReturnAt(LocalDateTime.now());
            issueService.updateIssue(issue);
            return new ResponseEntity<>(issue, HttpStatus.OK);  //ResponseEntity.ok(issue);
        }
    }

}