package model;

public class Quiz {
    private String question;
    private String[] correctAnswerArray;
    private int answerCount;
    private int totalScore;
    private ScoringMode scoringMode;
    private int[] scoreArray;
    public Quiz(String question, String correctAnswer, int score) {
        this.question = question;
        this.correctAnswerArray = new String[]{correctAnswer};
        this.answerCount = 1;
        this.totalScore = score;
        scoringMode = ScoringMode.ALL_OR_NOTHING;
    }
    public Quiz(String question, String[] correctAnswerArray, int answerCount, int score) {
        this.question = question;
        this.correctAnswerArray = correctAnswerArray;
        this.answerCount = answerCount;
        this.totalScore = score;
        scoringMode = ScoringMode.ALL_OR_NOTHING;
    }
    public Quiz(String question, String[] correctAnswerArray, int answerCount, int[] scoreArray) {
        if (correctAnswerArray.length != scoreArray.length) {
            throw new IllegalArgumentException("length of correctAnswerArray not equal to scoreArray.");
        }
        this.question = question;
        this.correctAnswerArray = correctAnswerArray;
        this.answerCount = answerCount;
        this.totalScore = 0;
        for (int score : scoreArray) {
            this.totalScore += score;
        }
        this.scoreArray = scoreArray;

        scoringMode = ScoringMode.WEIGHTED_PER_ANSWER;
    }
    public String getQuestion() {
        return question;
    }
    public String[] getCorrectAnswerArray() {
        return correctAnswerArray;
    }
    public int getAnswerCount() {
        return answerCount;
    }
    public int getScore() {
        return totalScore;
    }
}
