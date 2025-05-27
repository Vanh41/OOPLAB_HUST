package hust.soict.globalict.aims.screen.manager;

import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen{
    private JTextField tfLength;
    private JTextField tfDirector;
    private JCheckBox cbNegativeLength;

    public AddDigitalVideoDiscToStoreScreen(Store store, StoreManagerScreen storeScreen) {
        this.store = store;
        this.storeScreen = storeScreen;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(7, 2));  // Added one more row

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
        
        formPanel.add(new JLabel("Create problematic DVD (for testing): "));
        cbNegativeLength = new JCheckBox("Use negative length");
        formPanel.add(cbNegativeLength);

        JButton submitButton = new JButton("Add DVD");
        submitButton.addActionListener(new AddDigitalVideoDiscListener());
        formPanel.add(submitButton);

        cp.add(formPanel, BorderLayout.CENTER);

        setTitle("Add DVD");
        setSize(350, 350);  // Increased size
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public class AddDigitalVideoDiscListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            int length;
            
            if (cbNegativeLength.isSelected()) {
                length = -Math.abs(Integer.parseInt(tfLength.getText()));
            } else {
                length = Math.abs(Integer.parseInt(tfLength.getText()));
            }
            
            String director = tfDirector.getText();
            DigitalVideoDisc disc = new DigitalVideoDisc(title, category, director, length, cost);
            store.addMedia(disc);
            
            JOptionPane.showMessageDialog(null,
                "Added DVD: " + title + "\nLength: " + length + 
                (length < 0 ? " (negative, will trigger exception when played)" : ""),
                "DVD Added", 
                JOptionPane.INFORMATION_MESSAGE);
                
            storeScreen.updateMediaTable();
            storeScreen.setVisible(true);
            setVisible(false);
        }
    }
}