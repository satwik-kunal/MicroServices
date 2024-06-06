package com.satwik.quizapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionResponse {
    int id;
    String response;
}
