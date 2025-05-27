package hust.soict.globalict.aims.media;
import java.util.Comparator;

public abstract class Media implements Comparable<Media>{
    private int id;
    private String title;
    private String category;
    private float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST =
            new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE =
            new MediaComparatorByCostTitle();


    public Media() {

    }

    public Media(String title) {
        this.title = title;
    }

    public Media(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Media(String title, String category, float cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost must be non-negative.");
        }
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Media)) return false;
        Media other = (Media) obj;

        return (this.title != null && this.title.equals(other.getTitle()) && this.cost ==
                other.getCost());
    }

    @Override
    public int compareTo(Media other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare to null Media");
        }

        int titleCompare = this.title.compareToIgnoreCase(other.getTitle());
        if (titleCompare != 0) {
            return titleCompare;
        }

        return Float.compare(this.cost, other.getCost());
    }
}