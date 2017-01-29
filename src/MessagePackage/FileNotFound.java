package MessagePackage;

import MessagePackage.Messages;
import org.w3c.dom.Document;

import javax.swing.*;

public class FileNotFound implements Messages {

    @Override
    public void message() {
        JOptionPane.showMessageDialog(new JFrame(), "File not found!");
    }

    @Override
    public void getCount(Document doc) {

    }
}
