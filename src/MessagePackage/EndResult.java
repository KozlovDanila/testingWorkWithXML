package MessagePackage;

import javax.swing.*;

public class EndResult extends Messages {

    @Override
    public void message() {
        JOptionPane.showMessageDialog(new JFrame(), StoreMessages.getEndResult());
        System.exit(0);
    }
}
