package hust.soict.globalict.aims.screen.manager;

import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen{
    private JTextField tfArtist;

    public AddCompactDiscToStoreScreen(Store store, StoreManagerScreen storeScreen) {
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

        formPanel.add(new JLabel("Artist: "));
        tfArtist = new JTextField(10);
        formPanel.add(tfArtist);

        JButton submitButton = new JButton("Add CD");
        submitButton.addActionListener(new AddCompactDiscListener());
        formPanel.add(submitButton);

        cp.add(formPanel, BorderLayout.CENTER);

        setTitle("Add CD");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public class AddCompactDiscListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String artist = tfArtist.getText();
            CompactDisc disc = new CompactDisc(title, category, cost);
            disc.setArtist(artist);
            store.addMedia(disc);
            storeScreen.updateMediaTable();
            storeScreen.setVisible(true);
            dispose();
        }
    }
}
