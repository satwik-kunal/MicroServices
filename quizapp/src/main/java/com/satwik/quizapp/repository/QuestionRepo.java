package com.satwik.quizapp.repository;

import com.satwik.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {
    List<Question> findAllByCategory(String category);
}
