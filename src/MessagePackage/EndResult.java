package MessagePackage;

import org.w3c.dom.Document;
import reader.WorkerTag;
import workWithAnswers.ResponseHandler;

import javax.swing.*;

public class EndResult extends Messages {

    private int countOfQuestin;
    private int countTrueAnswer;

    public void getCount(Document doc, ResponseHandler handler) {
        WorkerTag questionTag = new WorkerTag(doc, "Question");
        countOfQuestin = questionTag.getLength();
        countTrueAnswer = handler.getCountTrueAnswer();
    }

    @Override
    public void message() {
        JOptionPane.showMessageDialog(new JFrame(),
                "<html>Всего вопросов: " +
                        +countOfQuestin + "<br>" +
                        "Правильных ответов: " + countTrueAnswer + "</html>");
        System.exit(0);
    }
}
