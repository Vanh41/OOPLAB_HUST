package hust.soict.globalict.screen.manager;

import javax.swing.*;
import java.awt.*;

public class AddBookToStoreScreen extends JFrame {
    private JTextField tfTitle;
    private JTextField tfCategory;
    private JTextField tfCost;

    public AddBookToStoreScreen() {
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(4, 2));

        cp.add(new JLabel("Title: "));
        tfTitle = new JTextField(10);
        cp.add(tfTitle);

        cp.add(new JLabel("Category: "));
        tfCategory = new JTextField(10);
        cp.add(tfCategory);

        cp.add(new JLabel("Cost: "));
        tfCost = new JTextField(10);
        cp.add(tfCost);

        JButton submitButton = new JButton("Add to store");
        submitButton.addActionListener(e -> {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            tfTitle.setText("");
            tfCategory.setText("");
            tfCost.setText("");
        });
        cp.add(submitButton);
    }
}
