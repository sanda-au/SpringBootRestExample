package com.github.sanda.QutTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;



@RestController
public class PollResources {

    @Autowired
    private PollRepository pollRepository;

    @GetMapping("/questions")
    public List<Poll> retrieveAll() {
        return pollRepository.findAll();
    }

    @PostMapping("/questions")
    public ResponseEntity<Object> createQuestion(@RequestBody NewQuestionRequest newQuestionRequest) {

        Poll newPoll = new Poll();

        newPoll.setQuestion(newQuestionRequest.getQuestion());
        newPoll.setChoices(newQuestionRequest.getChoices().stream().map(s -> new PollChoice(s)).collect(Collectors.toList()));
        newPoll.setPublished_at(new Date());
        Poll poll = pollRepository.save(newPoll);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(poll.getId()).toUri();

        return ResponseEntity.created(location).body(poll);

    }

}