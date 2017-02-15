package GUI;

import MessagePackage.StoreMessages;
import Utils.Updater;
import controlers.ResponseHandler;
import models.Answer;
import models.Question;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import readerDOM.WorkerTag;
import writerDOM.DOMWriter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    private WorkerTag workerTag;
    private int indexQuestion = 0;
    private ResponseHandler handlerQuestion;
    private Document doc;

    public Console(Document doc) {
        this.doc = doc;
        handlerQuestion = new ResponseHandler();
        workerTag = new WorkerTag(doc, "Question");
    }

    public ResponseHandler startConsole() {
        Scanner scanner = new Scanner(System.in);
        DOMWriter writer = new DOMWriter();
        Answer answer;

        Question question = new Question((Element) workerTag.getNode(indexQuestion++), workerTag);
        String str = question.getFullQuestionWithAnswers();
        System.out.println(str);
        while (indexQuestion < workerTag.getCountQuestions()) {
            answer = new Answer(scanner.nextLine(), indexQuestion);
            try {
                Updater updater = new Updater();
                String nextQuestion = updater.updateQuestion(doc, answer, workerTag, handlerQuestion, indexQuestion);
                System.out.println(nextQuestion);
                indexQuestion++;
            } catch (InputMismatchException ex) {
                System.out.println(StoreMessages.getCorrectInputData() + "\n");
            }
        }
        answer = new Answer(scanner.nextLine(), indexQuestion);
        handlerQuestion.addAnswer(workerTag, answer);
        writer.createResultXML(handlerQuestion.getAnswerResultList(), workerTag, answer);
        return handlerQuestion;
    }
}
