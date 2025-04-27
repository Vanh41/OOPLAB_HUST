package hust.soict.globalict.screen.manager;

import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen{
    private JTextField tfLength;
    private JTextField tfDirector;

    public AddDigitalVideoDiscToStoreScreen(Store store, StoreManagerScreen storeScreen) {
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

        formPanel.add(new JLabel("Length: "));
        tfLength = new JTextField(10);
        formPanel.add(tfLength);

        formPanel.add(new JLabel("Director: "));
        tfDirector = new JTextField(10);
        formPanel.add(tfDirector);

        JButton submitButton = new JButton("Add DVD");
        submitButton.addActionListener(new AddDigitalVideoDiscListener());
        formPanel.add(submitButton);

        cp.add(formPanel, BorderLayout.CENTER);

        setTitle("Add DVD");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public class AddDigitalVideoDiscListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            int length = Integer.parseInt(tfLength.getText());
            String director = tfDirector.getText();
            DigitalVideoDisc disc = new DigitalVideoDisc(title, category, director, length, cost);
            store.addMedia(disc);
            storeScreen.updateMediaTable();
            storeScreen.setVisible(true);
            setVisible(false);
        }
    }
}
