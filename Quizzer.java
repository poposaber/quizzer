import java.util.Scanner;
import logic.GameEngine;
import model.GradeResult;
import model.Quiz;
import output.CliOutput;

public class Quizzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameEngine ge = new GameEngine();
        CliOutput co = new CliOutput();

        boolean wantsToContinue = true;
        while (wantsToContinue) {
            Quiz quiz = ge.generateQuiz();

            co.showQuestion(quiz);
            String[] answerList = new String[quiz.getAnswerCount()];

            for (int i = 0; i < quiz.getAnswerCount(); i++) {
                answerList[i] = scanner.nextLine();
            }

            GradeResult gr = ge.submitAnswers(answerList);

            co.showGradeResult(gr);

            co.showScore(ge.getCurrentScore());

            System.out.println("Continue? (Y/N)");
            String s = scanner.nextLine();
            if (!(s.equals("y") || s.equals("Y"))) {
                wantsToContinue = false;
            }
        }
        

        scanner.close();
    }
}