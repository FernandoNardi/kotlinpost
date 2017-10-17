package ui;

import javax.swing.*;

public class PostDetailForm extends JFrame {
    private JPanel rootPanel;
    private JLabel lblTitle;
    private JLabel lblBody;

    public PostDetailForm(int id) {
        super();

        this.setContentPane(this.rootPanel);
        this.setSize(500,250);
        this.setVisible(true);

    }
}
