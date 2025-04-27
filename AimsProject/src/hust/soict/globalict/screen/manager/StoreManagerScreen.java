package hust.soict.globalict.screen.manager;

import hust.soict.globalict.aims.media.*;
import hust.soict.globalict.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StoreManagerScreen extends JFrame {
    private Store store;

    JPanel createNorth(){
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar(){
        JMenu menu = new JMenu("Options");

        JMenu viewStore = new JMenu("View store");
        viewStore.addActionListener(e -> {
            updateMediaTable();
        });
        menu.add(viewStore);

        JMenu smUpdateStore = new JMenu("Update store");
        JMenuItem addBook = new JMenuItem("Add book");
        addBook.addActionListener(e -> {
            dispose();
            new AddBookToStoreScreen(store, this);
        });
        smUpdateStore.add(addBook);

        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(e -> {
            dispose();
            new AddCompactDiscToStoreScreen(store, this);
        });
        smUpdateStore.add(addCD);

        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(e -> {
            dispose();
            new AddDigitalVideoDiscToStoreScreen(store, this);
        });
        smUpdateStore.add(addDVD);
        menu.add(smUpdateStore);


        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader(){
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter(){
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i=0;i<mediaInStore.size();i++){
            MediaStore cell = new MediaStore(mediaInStore.get(i));
            center.add(cell);
        }

        return center;
    }

    public class MediaStore extends JPanel{
        private Media media;
        public MediaStore(Media media){
            this.media = media;
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JLabel title = new JLabel(media.getTitle());
            title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel cost = new JLabel(""+media.getCost()+" $");
            cost.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel container = new JPanel();
            container.setLayout(new FlowLayout(FlowLayout.CENTER));

            if (media instanceof Playable){
                JButton playButton = new JButton("Play");
                playButton.addActionListener(e -> {
                    JOptionPane.showMessageDialog(this, "Playing " + media.getTitle());
                });
                container.add(playButton);
            }

            this.add(Box.createVerticalGlue());
            this.add(title);
            this.add(cost);
            this.add(Box.createVerticalGlue());
            this.add(container);

            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }

    public StoreManagerScreen(Store store){
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateMediaTable() {
        Container cp = getContentPane();
        Component oldCenter = ((BorderLayout)cp.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        if (oldCenter != null) {
            cp.remove(oldCenter);
        }
        cp.add(createCenter(), BorderLayout.CENTER);
        cp.revalidate();
        cp.repaint();
    }

    public static void main(String[] args) {
        Store store = new Store();
        store.addMedia(new Book("The Hobbit", "Fantasy", 10.99f));
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation",
                "Roger Allers", 87, 19.95f));
        store.addMedia(new CompactDisc("Aladin", "Animation", 18.99f));
        store.addMedia(new Book("The Nigg", "Fantasy", 10.99f));
        store.addMedia(new CompactDisc("Wolf", "Action", 20.99f));
        new StoreManagerScreen(store);
    }
}