package app;

import MessagePackage.FileNotFound;
import MessagePackage.Messages;
import reader.DOMReader;
import GUI.MyGUI;
import org.w3c.dom.*;

public class App {

    private static MyGUI gui;

    public static void main(String[] args) {

        try {
            DOMReader.createDoc("Questions.xml");
            Document doc = DOMReader.getDocument();

            gui = new MyGUI(doc);
            gui.addElements();
            gui.makeListener(doc);
        } catch (Exception ex) {
            Messages messages = new FileNotFound();
            messages.message();
            System.exit(0);
        }
        gui.setVisible(true);
    }

    public static int getCountOfTrueAnswer() {
        return gui.getCountTrueAnswer();
    }
}
