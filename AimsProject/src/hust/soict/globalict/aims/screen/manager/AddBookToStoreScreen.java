package hust.soict.globalict.aims.screen.manager;

import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookToStoreScreen extends AddItemToStoreScreen{
    private JTextField tfAuthors;

    public AddBookToStoreScreen(Store store, StoreManagerScreen storeScreen) {
        this.store = store;
        this.storeScreen = storeScreen;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2));

        formPanel.add(new JLabel("Title: "));
        tfTitle = new JTextField(10);
        formPanel.add(tfTitle);

        formPanel.add(new JLabel("Category: "));
        tfCategory = new JTextField(10);
        formPanel.add(tfCategory);

        formPanel.add(new JLabel("Cost: "));
        tfCost = new JTextField(10);
        formPanel.add(tfCost);

        formPanel.add(new JLabel("Authors: "));
        tfAuthors = new JTextField(10);
        formPanel.add(tfAuthors);

        JButton submitButton = new JButton("Add Book");
        submitButton.addActionListener(new AddBookListener());
        formPanel.add(submitButton);

        cp.add(formPanel, BorderLayout.CENTER);

        setTitle("Add Book");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public class AddBookListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String authors = tfAuthors.getText();
            String[] authorArr = authors.split(",");
            Book book = new Book(title, category, cost);
            for (int i=1;i<authorArr.length;i++){
                book.addAuthor(authorArr[i].trim());
            }
            store.addMedia(book);
            storeScreen.updateMediaTable();
            storeScreen.setVisible(true);
            dispose();
        }
    }
}