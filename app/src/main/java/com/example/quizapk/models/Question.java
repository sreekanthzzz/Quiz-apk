package com.example.quizapk.models;

import androidx.annotation.NonNull;

public class Question {
    private String question;
    private boolean answer;

    public Question()
    {

    }
    public Question(String question,boolean answer)
    {
        this.answer=answer;
        this.question=question;

    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean isAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    @NonNull
    @Override
    public String toString() {
        return "Question{"+
                "question="+question+'\''+
                ",answer="+answer+
                '}';
    }
}
