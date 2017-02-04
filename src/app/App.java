package app;

import MessagePackage.FileNotFound;
import MessagePackage.Messages;
import reader.DOMReader;
import GUI.MyGUI;
import org.w3c.dom.*;

public class App {

    public static void main(String[] args) {

        MyGUI gui = null;
        Document doc = null;

        try {
            DOMReader.createDoc("Questions.xml");
            doc = DOMReader.getDocument();
        } catch (Exception ex) {
            Messages messages = new FileNotFound();
            messages.message();
            System.exit(0);
        }

        gui = new MyGUI(doc);
        gui.addElements();
        gui.makeListener(doc);
        gui.setVisible(true);
    }
}
