package GUI;

import MessagePackage.CorrectInputData;
import MessagePackage.EndResult;
import MessagePackage.Messages;
import reader.WorkerTag;
import workWithAnswers.BoxAnswerResult;
import workWithAnswers.ParserAnswerUtils;
import org.w3c.dom.Document;
import writer.DOMWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MyGUI extends JFrame {

    private static final int POSITION_X_WINDOW = 0;
    private static final int POSITION_Y_WINDOW = 0;

    private static JTextField textAnswer;
    private static JLabel textQuestion;
    private static JButton button;

    private static int index = 1;
    private static Messages messages;

    private static List<BoxAnswerResult> answerResultList;
    private static int countTrueAnswer = 0;
    private static WorkerTag tag;

    public MyGUI(Document doc) {

        super("testing");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(POSITION_X_WINDOW, POSITION_Y_WINDOW, (int) dim.getWidth(), (int) dim.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        answerResultList = new ArrayList<>();
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
        String str = tag.getQuestion(index);
        if (!ParserAnswerUtils.checkCorrectInputData(doc, index)) {
            messages = new CorrectInputData();
            messages.message();
        } else {
            if (str.equals("-1")) {
                createListWithAnswer();
                DOMWriter.createResultXML(answerResultList, tag);
                messages = new EndResult();
                messages.getCount(doc);
                messages.message();
            } else {
                createListWithAnswer();
                textQuestion.setText(str);
                index++;
            }
        }
    }

    private static void createListWithAnswer() {
        boolean resultAnswer;
        String[] resultInput;
        resultInput = ParserAnswerUtils.getAnswers(MyGUI.getAnswer());
        resultAnswer = ParserAnswerUtils.checkAnswers(tag.getList(MyGUI.getIndexQuestion()-1, "Answer"), resultInput, tag);
        if (resultAnswer == true) {
            countTrueAnswer++;
        }
        answerResultList.add(new BoxAnswerResult(resultInput, resultAnswer));
    }

    public static String getAnswer() {
        return textAnswer.getText();
    }

    public static int getIndexQuestion() {
        return index;
    }

    public void updateAnswer() {
        textAnswer.setText("");
    }

    public static int getCountTrueAnswer() {
        return countTrueAnswer;
    }
}
