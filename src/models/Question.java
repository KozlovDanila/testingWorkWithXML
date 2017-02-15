package models;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import readerDOM.WorkerTag;

public class Question {

    private Element question;
    private WorkerTag workerTag;

    public Question(Element question, WorkerTag workerTag) {
        this.question = question;
        this.workerTag = workerTag;
    }

    public String getFullQuestionWithAnswers() {
        return getQuestion() + "\n" + getAnswersChoice() + "\n";
    }

    public String getAnswersChoice() {
        String res = "";
        NodeList answerList = question.getElementsByTagName("Answer");
        for (int answerIndex = 0; answerIndex < answerList.getLength(); answerIndex++) {
            Element answer = workerTag.getElement(answerList, answerIndex);
            res += answerIndex + 1 + ": ";
            res += workerTag.getTextByElement(answer, "text");
        }
        return res;
    }

    public String getQuestion(){
        return workerTag.getTextByElement(question, "text");
    }


}
