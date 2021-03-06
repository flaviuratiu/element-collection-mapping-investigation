package com.grapedata.hibernateelementcollectioninvestigation.web;

import com.grapedata.hibernateelementcollectioninvestigation.domain.QuestionResponse;
import com.grapedata.hibernateelementcollectioninvestigation.domain.QuestionResponseId;
import com.grapedata.hibernateelementcollectioninvestigation.domain.SelectedRating;
import com.grapedata.hibernateelementcollectioninvestigation.repository.QuestionResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class QuestionResponseController {

    @Autowired
    private QuestionResponseRepository questionResponseRepository;

    @GetMapping("/question-responses")
    public ResponseEntity<List<QuestionResponse>> getQuestionResponses() {
        return ResponseEntity.ok(questionResponseRepository.findAll());
    }

    @GetMapping("/question-responses/create-without-element-collection")
    public ResponseEntity<QuestionResponse> createQuestionResponseWithoutElementCollection() {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setId(new QuestionResponseId("a", "b"));
        return ResponseEntity.ok(questionResponseRepository.save(questionResponse));
    }

    @GetMapping("/question-responses/create-with-element-collection")
    public ResponseEntity<QuestionResponse> createQuestionResponseWithElementCollection() {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setId(new QuestionResponseId("c", "d"));
        questionResponse.setSelectedRatings(Collections.singletonList(new SelectedRating("o", "r")));
        return ResponseEntity.ok(questionResponseRepository.save(questionResponse));
    }
}
