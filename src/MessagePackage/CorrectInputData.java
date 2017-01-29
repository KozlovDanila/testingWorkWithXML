package MessagePackage;

import MessagePackage.Messages;
import org.w3c.dom.Document;

import javax.swing.*;

public class CorrectInputData implements Messages {
    @Override
    public void message() {
        JOptionPane.showMessageDialog(new JFrame(),
                "<html>1. Пустая строка считается неверным ответом.<br>" +
                        "2. Можно вводить только целые числа.<br>" +
                        "3. Числа должны быть не более(менее) максимального количества ответов.</html>");
    }

    @Override
    public void getCount(Document doc) {

    }
}

