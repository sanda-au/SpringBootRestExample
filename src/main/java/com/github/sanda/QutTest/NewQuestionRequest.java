package com.github.sanda.QutTest;

import java.util.List;

public class NewQuestionRequest {

    private List<String> choices;
    private  String       question;

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
