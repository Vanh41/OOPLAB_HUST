package hust.soict.globalict.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media{
    private List<String> authors = new ArrayList<String>();

    public Book(String title, String category, float cost) {
        super(title, category, cost);
        this.authors = authors;
    }
    

    public void addAuthor(String author) {
        if (!authors.contains(author)) {
            authors.add(author);
            System.out.println("Author " + author + " added to book ");
        }
        else {
            System.out.println("Author " + author + " already exists in book ");
        }
    }

    public void removeAuthor(String author) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).equals(author)) {
                authors.remove(i);
                System.out.println("Author " + author + " removed from book ");
                return;
            }
        }
        System.out.println("Author " + author + " does not exist in book ");
    }

    @Override
    public String toString() {
        return "Book - Title: " + getTitle() + ", Cost: " + getCost();
    }
}
