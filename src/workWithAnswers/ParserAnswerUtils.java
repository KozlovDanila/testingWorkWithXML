package workWithAnswers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import reader.WorkerTag;

public class ParserAnswerUtils {

    public ParserAnswerUtils() {
    }

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

    public static boolean checkCorrectInputData(Document doc, Answer answer) {
        WorkerTag questionTag = new WorkerTag(doc, "Question");
        NodeList answerList = questionTag.getList(answer.getIndex()-1, "Answer");
        String str = answer.getElement();

        if (str.length() == 0) {
            return false;
        }

        if(!checkSymbol(str) || !checkInputBorder(str, answerList)){
            return false;
        }
        return true;
    }

    private static boolean checkInputBorder(String str, NodeList list) {
        String[] answers = getAnswers(str);
        for (int i = 0;i < answers.length; i++) {
            if (!answers[i].equals("")) {
                if (Integer.parseInt(answers[i]) < 1 || Integer.parseInt(answers[i]) > list.getLength()) {
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
