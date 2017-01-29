package workWithAnswers;

import java.util.Arrays;

public class BoxAnswerResult {
    private String[] answers;
    private boolean answerResult;

    public BoxAnswerResult(String[] answers, boolean answerResult) {
        this.answers = answers;
        this.answerResult = answerResult;
    }

    public boolean getAnswerResult() {
        return answerResult;
    }

    public String[] getAnswers() {
        return answers;
    }


    @Override
    public String toString() {
        return "workWithAnswers.BoxAnswerResult{" +
                "answers=" + Arrays.toString(answers) +
                ", answerResult=" + answerResult +
                '}';
    }
}
