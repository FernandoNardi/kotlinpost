package ui;

import business.PostBusiness;
import entity.PostEntity;

import javax.swing.*;

public class PostDetailForm extends JFrame {
    private JPanel rootPanel;
    private JLabel lblTitle;
    private JLabel lblBody;

    private PostBusiness postBusiness = new PostBusiness();

    public PostDetailForm(int id) {
        super();

        this.loadPost(id);

        this.setContentPane(this.rootPanel);
        this.setSize(500,250);
        this.setVisible(true);

    }

    private void loadPost(int id) {
        PostEntity postEntity = this.postBusiness.getSinglePost(id);
        this.lblTitle.setText(postEntity.getTitle());
        this.lblBody.setText("<html>" + postEntity.getBody() + "<html>");
    }
}
