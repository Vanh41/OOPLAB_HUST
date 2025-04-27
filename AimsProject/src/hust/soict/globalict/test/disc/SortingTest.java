package hust.soict.globalict.test.disc;

import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingTest {

    public static void main(String[] args) {
        // Tạo danh sách các đối tượng Media (collection)
        List<Media> collection = new ArrayList<>();
        collection.add(new DigitalVideoDisc("The Aatrix"));
        collection.add(new Book("The Hobbit", "Fantasy", 10.99f));
        collection.add(new CompactDisc("Greatest Hits", "Pop",15.50f));

        printCollection(collection);
        Collections.sort(collection, Media.COMPARE_BY_TITLE_COST);
        System.out.println("\n---After sorting---");
        printCollection(collection);

        Collections.sort(collection, Media.COMPARE_BY_COST_TITLE);
        System.out.println("\n---After sorting---");
        printCollection(collection);
    }

    // Hàm tiện ích để in nội dung danh sách
    public static void printCollection(List<Media> collection) {
        for (Media media : collection) {
            System.out.println(media);
        }
    }
}