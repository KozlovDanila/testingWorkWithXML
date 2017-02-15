package MessagePackage;

import javax.swing.*;

public class CorrectInputData extends Messages {
    @Override
    public void message() {
        JOptionPane.showMessageDialog(new JFrame(), StoreMessages.getCorrectInputData());
    }
}

