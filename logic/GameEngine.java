package logic;

import model.GradeResult;
import model.Quiz;
import java.util.ArrayList;
import java.util.Arrays;

public class GameEngine {
    private QuizDatabase qdb = new QuizDatabase();
    private Quiz currentQuiz;
    private int currentScore = 0;

    public GameEngine() {
        
    }

    public Quiz generateQuiz() {
        currentQuiz = qdb.GetRandomQuiz();
        return currentQuiz;
    }

    /**
     * Submit answers and receive a GradeResult describing the outcome.
     */
    public GradeResult submitAnswers(String[] answerList) {
        if (currentQuiz == null) {
            throw new IllegalStateException("Quiz not generated yet.");
        }

        ArrayList<String> remainingCorrect = new ArrayList<String>(Arrays.asList(currentQuiz.getCorrectAnswerList()));
        ArrayList<String> matched = new ArrayList<String>();
        ArrayList<String> incorrectProvided = new ArrayList<String>();

        for (String answer : answerList) {
            if (remainingCorrect.contains(answer)) {
                matched.add(answer);
                remainingCorrect.remove(answer);
            } else {
                incorrectProvided.add(answer);
            }
        }

        boolean allMatched = incorrectProvided.isEmpty() && remainingCorrect.isEmpty() && matched.size() == currentQuiz.getAnswerCount();

        int pointsPossible = currentQuiz.getScore();
        int pointsAwarded = allMatched ? pointsPossible : 0; // simple full/zero scoring for now
        if (allMatched) {
            currentScore += pointsAwarded;
        }

        return new GradeResult(allMatched, pointsAwarded, pointsPossible, matched, remainingCorrect);
    }

    /**
     * Backwards-compatible verify method that delegates to submitAnswers.
     */
    // public boolean verifyAnswer(String[] answerList) {
    //     GradeResult r = submitAnswers(Arrays.asList(answerList));
    //     return r.isCorrect();
    // }

    public int getCurrentScore() {
        return currentScore;
    }
}
