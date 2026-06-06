package logic;

import model.Quiz;
import java.util.Random;

public class QuizDatabase {
    private Quiz[] quizList = {
        new Quiz("What is the tallest building in Taiwan?", "Taipei 101", 2), 
        new Quiz("What is the name of our planet?", "Earth", 3), 
        new Quiz("What is the answer of 3 * 12?", "36", 5), 
        new Quiz("What are the three conditions for burning?", new String[]{"fuel", "oxygen", "heat"}, 3, 6), 
        new Quiz("What are the countries in PIIGS?", new String[]{"Portugal", "Italy", "Ireland", "Greece", "Spain"}, 5, new Integer[]{2, 3, 4, 5, 6})
    };
    public QuizDatabase() {

    }
    public Quiz GetRandomQuiz() {
        Random rand = new Random();
        if (quizList.length == 0) {
            throw new IllegalStateException("No quizzes available");
        }
        return quizList[rand.nextInt(quizList.length)];
    }
}
