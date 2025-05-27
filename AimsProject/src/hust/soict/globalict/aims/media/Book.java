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
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author name cannot be null or empty.");
        }
        if (!authors.contains(author)) {
            authors.add(author);
            System.out.println("Author " + author + " added to book.");
        } else {
            throw new IllegalStateException("Author " + author + " already exists in book.");
        }
    }

    public void removeAuthor(String author) {
        if (!authors.contains(author)) {
            throw new IllegalStateException("Author " + author + " is not in the book.");
        }
        authors.remove(author);
        System.out.println("Author " + author + " removed from book.");
    }

    @Override
    public String toString() {
        return "Book - Title: " + getTitle() + ", Cost: " + getCost();
    }
}