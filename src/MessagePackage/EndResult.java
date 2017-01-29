package MessagePackage;

import app.App;
import org.w3c.dom.Document;
import reader.DOMReader;
import reader.WorkerTag;

import javax.swing.*;

public class EndResult implements Messages {

    private static int countOfQuestin;

    public void getCount(Document doc) {
        WorkerTag questionTag = new WorkerTag(doc, "Question");
        countOfQuestin = questionTag.getLength();
    }

    @Override
    public void message() {
        JOptionPane.showMessageDialog(new JFrame(),
                "<html>Всего вопросов: " +
                        +countOfQuestin + "<br>" +
                        "Правильных ответов: " + App.getCountOfTrueAnswer() + "</html>");
        System.exit(0);
    }
}
