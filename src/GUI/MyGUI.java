package GUI;

import MessagePackage.CorrectInputData;
import MessagePackage.EndResult;
import MessagePackage.Messages;
import reader.WorkerTag;
import workWithAnswers.Answer;
import workWithAnswers.ParserAnswerUtils;
import org.w3c.dom.Document;
import workWithAnswers.ResponseHandler;
import writer.DOMWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGUI extends JFrame {

    private JTextField textAnswer;
    private JLabel textQuestion;
    private JButton button;

    private int indexOfQuestion = 1;

    private ResponseHandler handlerQuestion;
    private WorkerTag tag;

    public MyGUI(Document doc) {

        super("testing");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int positionXOnWindow = 0;
        int positionYOnWindow = 0;
        setBounds(positionXOnWindow, positionYOnWindow, (int) dim.getWidth(), (int) dim.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        handlerQuestion = new ResponseHandler();
        tag = new WorkerTag(doc, "Question");
    }

    public void addElements() {

        textAnswer = new JTextField();
        textQuestion = new JLabel(tag.getQuestion(0));
        button = new JButton("Ответить!");

        add(textAnswer, BorderLayout.NORTH);
        add(textQuestion, BorderLayout.CENTER);
        add(button, BorderLayout.EAST);
    }

    public void makeListener(Document doc) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateQuestion(doc);
                updateAnswer();
            }
        });
    }

    public void updateQuestion(Document doc) {
        Answer answer = new Answer(textAnswer.getText(), indexOfQuestion);
        String str = tag.getQuestion(indexOfQuestion);

        if (!ParserAnswerUtils.checkCorrectInputData(doc, answer)) {
            Messages messages = new CorrectInputData();
            messages.message();
        } else {
            handlerQuestion.addAnswer(tag, answer);
            if (str.equals("-1")) {
                DOMWriter.createResultXML(handlerQuestion.getAnswerResultList(), tag, answer);
                Messages messages = new EndResult();
                messages.getCount(doc, handlerQuestion);
                messages.message();
            } else {
                textQuestion.setText(str);
                indexOfQuestion++;
            }
        }
    }

    public void updateAnswer() {
        textAnswer.setText("");
    }
}
