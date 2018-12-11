package com.github.sanda.QutTest;

import javax.persistence.Embeddable;

@Embeddable
public class PollChoice {
  public PollChoice() {
  }

  private String choice;
  private long votes;

  public String getChoice() {
    return choice;
  }

  public void setChoice(String choice) {
    this.choice = choice;
  }

  public long getVotes() {
    return votes;
  }

  public void setVotes(Long votes) {
    this.votes = votes;
  }

  public PollChoice(String choice) {
    this.choice = choice;
  }

}