package readerDOM;

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

    public String getTextByElement(Element element, String text) {
        if (element != null) {
            return element.getAttribute(text) + "\n";
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

    public int getCountQuestions() {
        return list.getLength();
    }
}
