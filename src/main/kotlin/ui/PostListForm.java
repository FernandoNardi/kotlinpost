package ui;

import javax.swing.*;

public class PostListForm extends JFrame {
    private JTable tablePost;
    private JPanel rootPanel;

    public PostListForm() {
        super();

        this.setContentPane(this.rootPanel);
        this.setSize(500,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // finish the program
    }
}
