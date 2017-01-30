package reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WorkerTag {

    private String tag;
    private NodeList list;

    public WorkerTag(Document doc, String tag) {
        this.tag = tag;
        list = doc.getElementsByTagName(tag);
    }

    private NodeList getTag() {
        return list;
    }

    public Node getNode(int index) {
        return list.item(index);
    }

    public NodeList getList(int index, String listName) {
        Node list = getTag().item(index);
        Element element = (Element) list;
        return element.getElementsByTagName(listName);
    }

    public String getQuestion(int index) {
        String res = "<html>";

        if (index >= getLength()) {
            return "-1";
        } else {

            Element question = getElement(list, index);
            res += getTextByElement(question, "text");
            NodeList answerList = question.getElementsByTagName("Answer");
            for (int answerIndex = 0; answerIndex < answerList.getLength(); answerIndex++) {
                Element answer = getElement(answerList, answerIndex);
                res += getTextByElement(answer, "text");
            }
        }

        return res + "</html>";
    }

    private String getTextByElement(Element element, String text) {
        if (element != null) {
            return element.getAttribute(text) + "<br>";
        }
        return "";
    }

    public Element getElement(NodeList nodeList, int index) {

        Node questionsNode = nodeList.item(index);
        if (questionsNode.getNodeType() == Node.ELEMENT_NODE) {
            Element questionElement = (Element) questionsNode;
            return questionElement;
        }
        return null;
    }

    public int getLength() {
        return list.getLength();
    }
}
