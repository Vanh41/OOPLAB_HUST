package hust.soict.globalict.aims.media;

public class DigitalVideoDisc extends Disc implements Playable{
    private String director;
    private int length;

    private static int nbDigitalVideoDiscs = 0;


    public DigitalVideoDisc(String title) {
        super();
        this.setTitle(title);
        nbDigitalVideoDiscs++;
        this.setId(nbDigitalVideoDiscs);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super();
        this.setTitle(title);
        this.setCategory(category);
        this.setCost(cost);
        nbDigitalVideoDiscs++;
        this.setId(nbDigitalVideoDiscs);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super();
        this.setTitle(title);
        this.setCategory(category);
        this.director = director;
        this.setCost(cost);
        nbDigitalVideoDiscs++;
        this.setId(nbDigitalVideoDiscs);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super();
        this.setTitle(title);
        this.setCategory(category);
        this.director = director;
        this.length = length;
        this.setCost(cost);
        nbDigitalVideoDiscs++;
        this.setId(nbDigitalVideoDiscs);
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
                this.getId(),
                getTitle(),     // title
                getCategory(),  // category
                getDirector(),  // director
                getLength(),    // length
                getCost());     // cost
    }

    public boolean isMatch(int id) {
        return this.getId() == id;
    }

    public boolean isMatch(String title) {
        return this.getTitle().equals(title);
    }

    @Override
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }

}