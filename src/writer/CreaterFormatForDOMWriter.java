package writer;

import GUI.MyGUI;
import org.w3c.dom.*;
import reader.WorkerTag;
import workWithAnswers.BoxAnswerResult;

public class CreaterFormatForDOMWriter {

    public CreaterFormatForDOMWriter() {
    }

    public static Element createQuestionElement(BoxAnswerResult boxAnswerResult, Document newDoc, int index, WorkerTag tag) {
        Element question = newDoc.createElement("Question");
        createAttrQuestion(boxAnswerResult, newDoc, index, question, tag);
        addAnswer(boxAnswerResult, newDoc, index, question, tag);
        return question;
    }

    private static void addAnswer(BoxAnswerResult boxAnswerResult, Document newDoc, int index, Element question, WorkerTag tag) {
        String[] answersStr = boxAnswerResult.getAnswers();
        NodeList answerList = tag.getList(MyGUI.getIndexQuestion() - 1, "Answer");

        for (int i = 0; i < answersStr.length; i++) {
            Element answer = newDoc.createElement("Answer");
            String answerText = "";
            Node answerNode = answerList.item(Integer.parseInt(answersStr[i]) - 1);
            if (answerNode.getNodeType() == answerNode.ELEMENT_NODE) {
                Element answerN = (Element) answerNode;
                answerText = answerN.getAttribute("text");
            }
            answer.setAttributeNode(setAttr(newDoc, "number", answersStr[i]));
            answer.setAttributeNode(setAttr(newDoc, "text", answerText));
            question.appendChild(answer);
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
