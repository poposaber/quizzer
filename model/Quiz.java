package model;

public class Quiz {
    private String question;
    private String[] correctAnswerList;
    private int answerCount;
    private int score;
    public Quiz(String question, String correctAnswer, int score) {
        this.question = question;
        this.correctAnswerList = new String[]{correctAnswer};
        this.answerCount = 1;
        this.score = score;
    }
    public Quiz(String question, String[] correctAnswerList, int answerCount, int score) {
        this.question = question;
        this.correctAnswerList = correctAnswerList;
        this.answerCount = answerCount;
        this.score = score;
    }
    public String getQuestion() {
        return question;
    }
    public String[] getCorrectAnswerList() {
        return correctAnswerList;
    }
    public int getAnswerCount() {
        return answerCount;
    }
    public int getScore() {
        return score;
    }
}
