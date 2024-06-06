package com.satwik.quizapp.service;

import com.satwik.quizapp.dto.QuestionResponse;
import com.satwik.quizapp.dto.QuestionWrapper;
import com.satwik.quizapp.model.Question;
import com.satwik.quizapp.model.Quiz;
import com.satwik.quizapp.repository.QuestionRepo;
import com.satwik.quizapp.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepo.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        for(Question q : questionFromDB){
            questionWrappers.add(new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4()));
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);

    }
    public ResponseEntity<String> getQuizResult(int id,List<QuestionResponse> questionResponse) {
        int result=0;
        int totalQuestion = questionResponse.size();
        Quiz quiz = quizRepo.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int ind = 0;
        for(QuestionResponse q: questionResponse){
            if(q.getResponse().equals(questions.get(ind).getRightAnswer())) {
                result += 1;
            }
            ind++;
        }
        return new ResponseEntity<>("You have scored "+result+ "/"+totalQuestion,HttpStatus.OK);
    }
}
