package controlers;

import models.Answer;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import readerDOM.WorkerTag;

import java.util.ArrayList;
import java.util.List;

public class ResponseHandler {

    private List<Answer> answerResultList;
    private int countTrueAnswer = 0;

    public ResponseHandler() {
        answerResultList = new ArrayList<>();
        countTrueAnswer = 0;
    }

    public void addAnswer(WorkerTag tag, Answer answer) {
        boolean resultAnswer;
        String[] resultInput;
        resultInput = answer.getAnswers();
        resultAnswer = checkAnswers(tag.getList(answer.getIndex() - 1, "Answer"), resultInput, tag);
        if (resultAnswer == true) {
            countTrueAnswer++;
            answer.setTrueAnswer();
        }
        answerResultList.add(answer);
    }

    public boolean checkAnswers(NodeList answerListXML, String[] answersUser, WorkerTag tag) {
        if (getCountTrueAnswer(answerListXML, tag) != answersUser.length) {
            return false;
        }
        for (int answerIndex = 0; answerIndex < answersUser.length; answerIndex++) {
            Element answer = tag.getElement(answerListXML, Integer.parseInt(answersUser[answerIndex]) - 1);
            if (answer.getAttribute("right").equals("false")) {
                return false;
            }
        }
        return true;
    }

    private static int getCountTrueAnswer(NodeList answerListXML, WorkerTag tag) {
        int count = 0;
        for (int answerIndex = 0; answerIndex < answerListXML.getLength(); answerIndex++) {
            Element answer = tag.getElement(answerListXML, answerIndex);
            if (answer.getAttribute("right").equals("true")) {
                count++;
            }
        }
        return count;
    }

    public List<Answer> getAnswerResultList() {
        return answerResultList;
    }

    public int getCountTrueAnswer() {
        return countTrueAnswer;
    }

}
