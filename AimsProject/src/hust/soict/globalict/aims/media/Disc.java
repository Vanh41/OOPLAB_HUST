package hust.soict.globalict.aims.media;

public class Disc extends Media {
    private int length;
    private String director;

    public Disc() {

    }

    public Disc(String title) {
        super(title);
    }

    public Disc(int id, String title) {
        super(id, title);
    }

    public Disc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost);
        this.length = length;
        this.director = director;
    }

    public Disc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public Disc(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public int getLength() {
        return length;
    }

    public String getDirector() {
        return director;
    }
}
