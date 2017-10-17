package ui;

import business.PostBusiness;
import entity.PostEntity;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PostListForm extends JFrame implements ListSelectionListener {
    private JTable tablePost;
    private JPanel rootPanel;

    private PostBusiness postBusiness = new PostBusiness();

    public PostListForm() {
        super();

        this.getAllPosts();

        this.setContentPane(this.rootPanel);
        this.setSize(500,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // finish the program

        this.setEvents();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int postId;
        if(e.getValueIsAdjusting()){
            postId = Integer.parseInt(this.tablePost.getValueAt(this.tablePost.getSelectedRow(), 0).toString());
//            JOptionPane.showMessageDialog(this, postId);
            new PostDetailForm(postId);
        }
    }

    private void setEvents() {
        this.tablePost.getSelectionModel().addListSelectionListener(this);
    }

    private void getAllPosts() {
        String[] columnsNames = {"Id", "Title"};
        List<PostEntity> posts = this.postBusiness.getAllPosts();

        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnsNames);

        int i = 0;
        for (PostEntity post: posts) {
            Object[] tableLine = new Object[2];
            tableLine[0] = post.getId();
            tableLine[1] = post.getTitle();
            model.addRow(tableLine);

            i++;
            if (i >= 10) {
                break;
            }
        }

        this.tablePost.setModel(model);
    }
}
