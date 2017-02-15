package app;

import Work.Worker;
import Work.WorkerWithGui;

public class App {

    public static void main(String[] args) throws Exception {
       Worker worker = new WorkerWithGui();
       // Worker worker = new WorkerWithConsole();
        worker.working("Questions.xml");
    }

}


