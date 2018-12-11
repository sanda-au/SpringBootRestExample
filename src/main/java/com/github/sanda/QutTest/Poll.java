package com.github.sanda.QutTest;

import java.util.Date;
import java.util.List;
import javax.persistence.*;


@Entity
public class Poll {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long         id;
  @ElementCollection
  private  List<PollChoice> choices;
  private  String       question;

  @Temporal(TemporalType.TIMESTAMP)
  private Date published_at;


  public Poll() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<PollChoice> getChoices() {
    return choices;
  }

  public void setChoices(List<PollChoice> choices) {
    this.choices = choices;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public Date getPublished_at() {
    return published_at;
  }

  public void setPublished_at(Date published_at) {
    this.published_at = published_at;
  }
}
