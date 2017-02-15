package MessagePackage;

import javax.swing.*;

public class FileNotFound extends Messages {

    @Override
    public void message() {
        JOptionPane.showMessageDialog(new JFrame(), StoreMessages.getFileNotFound());
    }
}
