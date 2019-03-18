package com.example.project1;

public class QuizGame {
    int score;
    String date;

    //Constructor to pass values from DatabaseHelper_QuizGame
    public QuizGame(int score, String date) {
        this.score = score;
        this.date = date;
    }

    //Our Score Getter
    public int getScore() {
        return score;
    }

    //Our Date Getter
    public String getDate() {
        return date;
    }
}
