package Work;

import GUI.Console;
import MessagePackage.StoreMessages;
import controlers.ResponseHandler;
import org.w3c.dom.Document;
import readerDOM.DOMReader;

public class WorkerWithConsole implements Worker {

    public WorkerWithConsole() {
    }

    @Override
    public void working(String filename) {
        System.out.println("Здравствуйте. Пройдите тестирование!\n");
        Document doc = readeDoc(filename);
        Console console = new Console(doc);
        ResponseHandler handler = console.startConsole();
        StoreMessages.getCount(doc, handler);
        System.out.println(StoreMessages.getEndResult());
    }

    private Document readeDoc(String filename){
        DOMReader reader = new DOMReader();
        try {
            reader.createDoc(filename);
        } catch (Exception e) {
            StoreMessages.getFileNotFound();
            System.exit(0);
        }
        return reader.getDocument();
    }
}

