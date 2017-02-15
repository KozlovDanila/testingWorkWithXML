package Utils;

import models.Answer;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import readerDOM.WorkerTag;

public class ReviewerInputData {

    private Answer answer;
    private Document doc;

    public ReviewerInputData(Answer answer, Document doc) {
        this.answer = answer;
        this.doc = doc;
    }

    public boolean checkCorrectInputData() {
        String str = answer.getStringWithAnswer();
        if (str.length() == 0) {
            return false;
        }
        if(!checkSymbol(str) || !checkInputBorder()){
            return false;
        }
        return true;
    }

    private boolean checkInputBorder() {
        WorkerTag questionTag = new WorkerTag(doc, "Question");
        NodeList answerList = questionTag.getList(answer.getIndex()-1, "Answer");
        String[] answers = answer.getAnswers();
        for (int i = 0;i < answers.length; i++) {
            if (!answers[i].equals("")) {
                if (Integer.parseInt(answers[i]) < 1 || Integer.parseInt(answers[i]) > answerList.getLength()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkSymbol(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch != ' ') {
                if (ch < '0' || ch > '9') {
                    return false;
                }
            }
        }
        return true;
    }
}
