package workWithAnswers;

import GUI.MyGUI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import reader.WorkerTag;

public class ParserAnswerUtils {

    public static boolean checkAnswers(NodeList answerListXML, String[] answersUser, WorkerTag tag) {
        if (getCountTrueAnswer(answerListXML, tag) != answersUser.length) {
            return false;
        }
        for (int answerIndex = 0; answerIndex < answersUser.length; answerIndex++) {
            Element answer = tag.getElement(answerListXML, Integer.parseInt(answersUser[answerIndex]) - 1);
            if (answer.getAttribute("right").equals("false")) {
                return false;
            }
        }
        return true;
    }

    private static int getCountTrueAnswer(NodeList answerListXML, WorkerTag tag) {
        int count = 0;
        for (int answerIndex = 0; answerIndex < answerListXML.getLength(); answerIndex++) {
            Element answer = tag.getElement(answerListXML, answerIndex);
            if (answer.getAttribute("right").equals("true")) {
                count++;
            }
        }
        return count;
    }

    public static boolean checkCorrectInputData(Document doc) {
        String str = MyGUI.getAnswer();
        boolean validInput = false;
        if (str.length() == 0) {
            return validInput;
        }

        validInput = checkSymbol(str);
        validInput = checkInputBorder(doc, str);

        return validInput;
    }

    private static boolean checkInputBorder(Document doc, String str) {
        String[] answers = getAnswers(str);
        WorkerTag questionTag = new WorkerTag(doc, "Question");
        for (String element : answers) {
            if (!element.equals("")) {
                if (Integer.parseInt(element) < 1 || Integer.parseInt(element) > questionTag.getLength()) {
                    return false;
                }
            }
        }
        return true;
    }


    private static boolean checkSymbol(String str) {
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

    public static String[] getAnswers(String answers) {
        return answers.split(" ");
    }
}
