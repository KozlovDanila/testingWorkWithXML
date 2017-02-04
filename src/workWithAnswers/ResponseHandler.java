package workWithAnswers;

import reader.WorkerTag;

import java.util.ArrayList;
import java.util.List;

public class ResponseHandler {

    private List<BoxAnswerResult> answerResultList;
    private int countTrueAnswer = 0;

    public ResponseHandler() {
        answerResultList = new ArrayList<>();
        countTrueAnswer = 0;
    }

    public void addAnswer(WorkerTag tag, Answer answer) {
        boolean resultAnswer;
        String[] resultInput;
        resultInput = ParserAnswerUtils.getAnswers(answer.getElement());
        resultAnswer = ParserAnswerUtils.checkAnswers(tag.getList(answer.getIndex() - 1, "Answer"), resultInput, tag);
        if (resultAnswer == true) {
            countTrueAnswer++;
        }
        answerResultList.add(new BoxAnswerResult(resultInput, resultAnswer));
    }

    public List<BoxAnswerResult> getAnswerResultList() {
        return answerResultList;
    }

    public int getCountTrueAnswer() {
        return countTrueAnswer;
    }

}
