package output;

import model.GradeResult;
import model.Quiz;

import java.text.MessageFormat;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class CliOutput {
    private final ResourceBundle bundle;

    public CliOutput() {
        ResourceBundle b = null;
        try {
            b = ResourceBundle.getBundle("resources.messages");
        } catch (MissingResourceException e) {
            // fallback to null and use keys as patterns
            System.err.println("missing resource bundle");
        }
        this.bundle = b;
    }

    private String fmt(String key, Object... args) {
        String pattern;
        if (bundle != null) {
            try {
                pattern = bundle.getString(key);
            } catch (MissingResourceException e) {
                pattern = key;
            }
        } else {
            pattern = key;
        }
        return MessageFormat.format(pattern, args);
    }

    public void showQuestion(Quiz q) {
        System.out.println(fmt(CliContent.QUESTION_PROMPT, q.getQuestion()));
        if (q.getAnswerCount() > 1) {
            System.out.println(fmt(CliContent.MULTI_ANSWER_INSTRUCTIONS, q.getAnswerCount()));
        }
        System.out.println(fmt(CliContent.QUESTION_POINTS, q.getScore()));
    }

    public void showGradeResult(GradeResult r) {
        if (r.isCorrect()) {
            System.out.println(fmt(CliContent.RESULT_CORRECT, r.getPointsAwarded(), r.getPointsPossible()));
        } else {
            String missing = join(r.getMissingAnswers());
            System.out.println(fmt(CliContent.RESULT_INCORRECT, r.getPointsAwarded(), r.getPointsPossible(), missing));
        }
    }

    public void showScore(int total) {
        System.out.println(fmt(CliContent.SCORE_TOTAL, total));
    }

    private String join(List<String> list) {
        if (list == null || list.isEmpty()) return "";
        StringJoiner sj = new StringJoiner(", ");
        for (String s : list) sj.add(s);
        return sj.toString();
    }
}
