package writer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import reader.WorkerTag;
import workWithAnswers.Answer;
import workWithAnswers.BoxAnswerResult;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class DOMWriter {

    public static void createResultXML(List<BoxAnswerResult> listWithResult, WorkerTag tag, Answer lastAnswer) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document newDoc = builder.newDocument();
            Element rootElement = newDoc.createElementNS("", "Questions");
            newDoc.appendChild(rootElement);

            for (int i = 0; i < listWithResult.size(); i++) {
                Element questions = CreateAnswerForDOMWriter.createQuestionElement(listWithResult.get(i), newDoc, i, tag, lastAnswer);
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
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        return transformer;
    }

    private static void addFile(Document newDoc) throws TransformerException {
        Transformer transformer = createTransformer();
        DOMSource source = new DOMSource(newDoc);
        StreamResult file = new StreamResult(new File("Answer.xml"));
        transformer.transform(source, file);
    }

}
