package output;

/**
 * Message key constants for CLI texts.
 *
 * Implemented as a final class with `public static final` fields –
 * preferred to the constant-interface pattern.
 */
public final class CliContent {
    public static final String QUESTION_PROMPT = "question.prompt";
    public static final String ANSWER_PROMPT = "answer.prompt";
    public static final String MULTI_ANSWER_INSTRUCTIONS = "multiAnswer.instructions";
    public static final String RESULT_CORRECT = "result.correct";
    public static final String RESULT_INCORRECT = "result.incorrect";
    public static final String SCORE_TOTAL = "score.total";
    public static final String QUESTION_POINTS = "question.points";
    public static final String NO_QUIZZES = "error.noQuizzes";

    private CliContent() { }
}

