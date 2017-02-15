package GUI;

import MessagePackage.CorrectInputData;
import MessagePackage.EndResult;
import MessagePackage.Messages;
import MessagePackage.StoreMessages;
import Utils.Updater;
import controlers.ResponseHandler;
import models.Answer;
import models.Question;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import readerDOM.WorkerTag;
import writerDOM.DOMWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class MyGUI extends JFrame {

    private JTextField textAnswerField;
    private JTextArea textQuestionField;
    private JButton button;

    private int indexQuestion = 1;

    private ResponseHandler handlerQuestion;
    private WorkerTag workerTag;

    public MyGUI(Document doc) {

        super("testing");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int positionXOnWindow = 0;
        int positionYOnWindow = 0;
        setBounds(positionXOnWindow, positionYOnWindow, (int) dim.getWidth(), (int) dim.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        handlerQuestion = new ResponseHandler();
        workerTag = new WorkerTag(doc, "Question");
    }

    public void addElements() {
        textAnswerField = new JTextField();
        add(textAnswerField, BorderLayout.NORTH);

        Question question = new Question((Element) workerTag.getNode(0), workerTag);
        textQuestionField = new JTextArea("Здравствуйте. Пройдите тестирование!\n" + question.getFullQuestionWithAnswers());
        add(textQuestionField, BorderLayout.CENTER);

        button = new JButton("Ответить!");
        add(button, BorderLayout.EAST);
    }

    public void makeListener(Document doc) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Answer answer = new Answer(textAnswerField.getText(), indexQuestion);
                if (indexQuestion >= workerTag.getCountQuestions()) {
                    handlerQuestion.addAnswer(workerTag, answer);
                    DOMWriter writer = new DOMWriter();
                    writer.createResultXML(handlerQuestion.getAnswerResultList(), workerTag, answer);
                    Messages messages = new EndResult();
                    StoreMessages.getCount(doc, handlerQuestion);
                    messages.message();
                }

                try {
                    Updater updater = new Updater();
                    String nextQuestion = updater.updateQuestion(doc, answer, workerTag, handlerQuestion, indexQuestion);
                    textQuestionField.setText(nextQuestion);
                    indexQuestion++;
                } catch (InputMismatchException ex) {
                    Messages messages = new CorrectInputData();
                    messages.message();
                }

                updateAnswer();
            }
        });
    }

    public void updateAnswer() {
        textAnswerField.setText("");
    }
}
