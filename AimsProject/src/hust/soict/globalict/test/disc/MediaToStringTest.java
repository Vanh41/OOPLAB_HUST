package hust.soict.globalict.test.disc;

import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Media;
import java.util.ArrayList;
import java.util.List;

public class MediaToStringTest {
    public static void main(String[] args) {
        List<Media> mediae = new ArrayList<>();

        DigitalVideoDisc dvd = new DigitalVideoDisc("Inception");
        Book book = new Book("The Hobbit", "Fantasy", 10.99f);
        CompactDisc cd = new CompactDisc("Greatest Hits", "Pop",15.50f);
        mediae.add(cd);
        mediae.add(dvd);
        mediae.add(book);
        for (Media m : mediae) {
            System.out.println(m.toString());
        }
    }
}
