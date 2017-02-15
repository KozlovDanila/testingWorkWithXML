package MessagePackage;

import org.w3c.dom.Document;
import readerDOM.WorkerTag;
import controlers.ResponseHandler;

public class StoreMessages {

    private static int countOfQuestin;
    private static int countTrueAnswer;

    public static void getCount(Document doc, ResponseHandler handler) {
        WorkerTag questionTag = new WorkerTag(doc, "Question");
        countOfQuestin = questionTag.getCountQuestions();
        countTrueAnswer = handler.getCountTrueAnswer();
    }

    public static String getCorrectInputData() {
        return "1. Пустая строка считается неверным ответом.\n" +
                "2. Можно вводить только целые числа.\n" +
                "3. Числа должны быть не более(менее) максимального количества ответов.";
    }

    public static String getEndResult() {
        return "Всего вопросов: " +
                + countOfQuestin + "\n" +
                "Правильных ответов: " + countTrueAnswer;
    }

    public static String getFileNotFound() {
        return "File not found";
    }


}
