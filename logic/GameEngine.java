package logic;

import model.GradeResult;
import model.Quiz;
import model.ScoringMode;
import java.util.ArrayList;

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

        String[] correctAnswers = currentQuiz.getCorrectAnswerArray();
        Integer[] scoreArray = currentQuiz.getScoreArray();
        boolean[] matchedSlots = new boolean[correctAnswers.length];

        ArrayList<String> matched = new ArrayList<String>();
        ArrayList<String> incorrectProvided = new ArrayList<String>();
        int pointsAwarded = 0;

        for (String answer : answerList) {
            if (answer == null) {
                incorrectProvided.add(null);
                continue;
            }

            String matchedCorrectAnswer = null;
            for (int i = 0; i < correctAnswers.length; i++) {
                if (matchedSlots[i]) {
                    continue;
                }

                String correctAnswer = correctAnswers[i];
                if (correctAnswer != null && correctAnswer.equalsIgnoreCase(answer)) {
                    matchedCorrectAnswer = correctAnswer;
                    matchedSlots[i] = true;
                    if (currentQuiz.getScoringMode() == ScoringMode.WEIGHTED_PER_ANSWER && scoreArray != null) {
                        pointsAwarded += scoreArray[i];
                    }
                    break;
                }
            }

            if (matchedCorrectAnswer != null) {
                matched.add(matchedCorrectAnswer);
            }
            else {
                incorrectProvided.add(answer);
            }
        }

        ArrayList<String> remainingCorrect = new ArrayList<String>();
        for (int i = 0; i < correctAnswers.length; i++) {
            if (!matchedSlots[i]) {
                remainingCorrect.add(correctAnswers[i]);
            }
        }

        boolean allMatched = incorrectProvided.isEmpty() && remainingCorrect.isEmpty() && matched.size() == currentQuiz.getAnswerCount();

        int pointsPossible = currentQuiz.getScore();
        if (currentQuiz.getScoringMode() == ScoringMode.ALL_OR_NOTHING) {
            pointsAwarded = allMatched ? pointsPossible : 0;
        }

        currentScore += pointsAwarded;

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
