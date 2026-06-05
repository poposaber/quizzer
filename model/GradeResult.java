package model;

import java.util.Collections;
import java.util.List;

public class GradeResult {
    private final boolean correct;
    private final int pointsAwarded;
    private final int pointsPossible;
    private final List<String> matchedAnswers;
    private final List<String> missingAnswers;

    public GradeResult(boolean correct, int pointsAwarded, int pointsPossible,
                       List<String> matchedAnswers, List<String> missingAnswers) {
        this.correct = correct;
        this.pointsAwarded = pointsAwarded;
        this.pointsPossible = pointsPossible;
        this.matchedAnswers = matchedAnswers == null ? Collections.emptyList() : Collections.unmodifiableList(matchedAnswers);
        this.missingAnswers = missingAnswers == null ? Collections.emptyList() : Collections.unmodifiableList(missingAnswers);
    }

    public boolean isCorrect() {
        return correct;
    }

    public int getPointsAwarded() {
        return pointsAwarded;
    }

    public int getPointsPossible() {
        return pointsPossible;
    }

    public List<String> getMatchedAnswers() {
        return matchedAnswers;
    }

    public List<String> getMissingAnswers() {
        return missingAnswers;
    }

    @Override
    public String toString() {
        return "GradeResult{" +
                "correct=" + correct +
                ", pointsAwarded=" + pointsAwarded +
                ", pointsPossible=" + pointsPossible +
                ", matchedAnswers=" + matchedAnswers +
                ", missingAnswers=" + missingAnswers +
                '}';
    }
}
