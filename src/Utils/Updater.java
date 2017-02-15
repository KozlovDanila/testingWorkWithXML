package Utils;

import controlers.ResponseHandler;
import models.Answer;
import models.Question;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import readerDOM.WorkerTag;

import java.util.InputMismatchException;

public class Updater {

    public Updater() {
    }

    public String updateQuestion(Document doc, Answer answer, WorkerTag workerTag, ResponseHandler handler, int index) {
        Question question = new Question((Element) workerTag.getNode(index), workerTag);
        String str = question.getFullQuestionWithAnswers();
        ReviewerInputData reviewer = new ReviewerInputData(answer, doc);
        if (!reviewer.checkCorrectInputData()) {
            throw new InputMismatchException();
        } else {
            handler.addAnswer(workerTag, answer);
        }
        return str;
    }
}
