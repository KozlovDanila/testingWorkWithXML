package Work;

import GUI.MyGUI;
import MessagePackage.FileNotFound;
import MessagePackage.Messages;
import org.w3c.dom.Document;
import readerDOM.DOMReader;


public class WorkerWithGui implements Worker {

    public WorkerWithGui() {
    }

    @Override
    public void working(String filename) {
        DOMReader reader = new DOMReader();
        try {
            reader.createDoc(filename);
        } catch (Exception e) {
            Messages messages = new FileNotFound();
            messages.message();
            System.exit(0);
        }
        Document doc =  reader.getDocument();
        MyGUI gui = new MyGUI(doc);
        gui.addElements();
        gui.makeListener(doc);
        gui.setVisible(true);
    }
}
