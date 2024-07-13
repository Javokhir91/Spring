package ru.gb.model;


import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;


 @Data
 @Entity
 @Table(name = "issues")
public class Issue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long bookId;
  private Long readerId;


  private  LocalDateTime issuedAt;

  private LocalDateTime timeReturn = null;

  public Issue() {
  }

  public Issue(Long bookId, Long readerId) {
    this.bookId = bookId;
    this.readerId = readerId;
//    this.issuedAt = issuedAt;
  }

  public void setTimeReturn(LocalDateTime timeReturn) {
    this.timeReturn = timeReturn;
  }

  public Long getId() {
    return id;
  }

   public void setIssuedAt(LocalDateTime issuedAt) {
     this.issuedAt = issuedAt;
   }

  public Long getBookId() {
    return bookId;
  }

  public Long getReaderId() {
    return readerId;
  }

  public LocalDateTime getIssuedAt() {
    return issuedAt;
  }

  public LocalDateTime getTimeReturn() {
    return timeReturn;
  }

}
