package com.satwik.quizapp.controller;

import com.satwik.quizapp.model.Question;
import com.satwik.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{cat}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String cat){
        return questionService.getAllByCategory(cat);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
}
