package hust.soict.globalict.aims.disc;

public class DigitalVideoDisc {
    private String director;
    private int length;

    private static int nbDigitalVideoDiscs = 0;


    public DigitalVideoDisc(String title) {
        super();
        this.title = title;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super();
        this.title = title;
        this.category = category;
        this.cost = cost;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super();
        this.title = title;
        this.category = category;
        this.director = director;
        this.cost = cost;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super();
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public int getLength() {
        return length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String toString() {
        return String.format("%d. DVD - %s - %s - %s - %d: %.2f $",
                this.id,
                getTitle(),     // title
                getCategory(),  // category
                getDirector(),  // director
                getLength(),    // length
                getCost());     // cost
    }

    public boolean isMatch(int id) {
        return this.id == id;
    }

    public boolean isMatch(String title) {
        return this.title.equals(title);
    }
}