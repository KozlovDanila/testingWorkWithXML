package writer;

import GUI.MyGUI;
import org.w3c.dom.*;
import reader.WorkerTag;
import workWithAnswers.BoxAnswerResult;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class DOMWriter {

    public static void createResultXML(List<BoxAnswerResult> listWithResult, WorkerTag tag) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document newDoc = builder.newDocument();
            Element rootElement = newDoc.createElementNS("", "Questions");
            newDoc.appendChild(rootElement);

            for (int i = 0; i < listWithResult.size(); i++) {
                Element questions = createQuestionElement(listWithResult.get(i), newDoc, i, tag);
                rootElement.appendChild(questions);
            }

            addFile(newDoc);

        } catch (Exception e) {
            System.exit(0);
        }
    }

    private static Transformer createTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        return transformer;
    }

    private static void addFile(Document newDoc) throws TransformerException {
        Transformer transformer = createTransformer();
        DOMSource source = new DOMSource(newDoc);
        StreamResult file = new StreamResult(new File("Answer.xml"));
        transformer.transform(source, file);
    }

    private static Element createQuestionElement(BoxAnswerResult boxAnswerResult, Document newDoc, int index, WorkerTag tag) {
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
