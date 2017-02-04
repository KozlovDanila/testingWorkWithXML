package writer;

import org.w3c.dom.*;
import reader.WorkerTag;
import workWithAnswers.Answer;
import workWithAnswers.BoxAnswerResult;

public class CreateAnswerForDOMWriter {

    public CreateAnswerForDOMWriter() {
    }

    public static Element createQuestionElement(BoxAnswerResult boxAnswerResult, Document newDoc, int index, WorkerTag tag, Answer answer) {
        Element question = newDoc.createElement("Question");
        createAttrQuestion(boxAnswerResult, newDoc, index, question, tag);
        addAnswer(boxAnswerResult, newDoc, question, tag, answer);
        return question;
    }

    private static void addAnswer(BoxAnswerResult boxAnswerResult, Document newDoc, Element question, WorkerTag tag, Answer answer) {
        String[] answersStr = boxAnswerResult.getAnswers();
        NodeList answerList = tag.getList(answer.getIndex() - 1, "Answer");

        for (int i = 0; i < answersStr.length; i++) {
            Element answerElement = newDoc.createElement("Answer");
            String answerText = "";
            Node answerNode = answerList.item(Integer.parseInt(answersStr[i]) - 1);
            if (answerNode.getNodeType() == answerNode.ELEMENT_NODE) {
                Element answerN = (Element) answerNode;
                answerText = answerN.getAttribute("text");
            }
            answerElement.setAttributeNode(setAttr(newDoc, "number", answersStr[i]));
            answerElement.setAttributeNode(setAttr(newDoc, "text", answerText));
            question.appendChild(answerElement);
        }
    }

    private static void createAttrQuestion(BoxAnswerResult boxAnswerResult, Document newDoc, int index, Element question, WorkerTag tag) {
        Node questionsNode = tag.getNode(index);
        if (questionsNode.getNodeType() == Node.ELEMENT_NODE) {
            Element questionNode = (Element) questionsNode;
            question.setAttributeNode(setAttr(newDoc, "text", questionNode.getAttribute("text")));
        }
        String str = getStateAnswer(boxAnswerResult);
        question.setAttributeNode(setAttr(newDoc, "succes", str));
        question.setAttributeNode(setAttr(newDoc, "number", index + 1 + ""));
    }

    private static String getStateAnswer(BoxAnswerResult boxAnswerResult) {
        if (boxAnswerResult.getAnswerResult() == true) {
            return "true";
        } else {
            return "false";
        }
    }

    private static Attr setAttr(Document doc, String name, String value) {
        Attr attr = doc.createAttribute(name);
        attr.setValue(value);
        return attr;
    }
}
