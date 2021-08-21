package com.example.quizapk.data;

import com.example.quizapk.models.Question;

import java.util.ArrayList;

public interface AnswerListAsync {
    void processFinished(ArrayList<Question> questionArrayList) ;
}
