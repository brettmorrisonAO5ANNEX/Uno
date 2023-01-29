package UI;

import javax.swing.*;

public class UnoAppGUI extends JFrame {

    public UnoAppGUI() {
        super("UNO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new GamePanel());
        pack();
        setVisible(true);
        setBounds(400, 300, 600, 400);
    }
}
