package hust.soict.globalict.aims.cart;

import hust.soict.globalict.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;

public class Cart {
    private static final int MAX_NUMBER_ORDERED = 20;
    private final ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public void addMedia(Media media) {
        if (media == null) {
            throw new IllegalArgumentException("Cannot add null media to the cart.");
        }
        if (itemsOrdered.size() >= MAX_NUMBER_ORDERED) {
            throw new IllegalStateException("Cannot add media. Cart is full.");
        }
        itemsOrdered.add(media);
    }

    public void addMedia(Media[] media) {
        for (int i = 0; i < media.length; i++) {
            addMedia(media[i]);
        }
    }

    public void addMedia(Media media1, Media media2) {
        addMedia(media1);
        addMedia(media2);
    }

    public Media searchMedia(String title){
        for(Media media:itemsOrdered){
            if(media.getTitle().equals(title)){
                System.out.println(media);
                return media;
            }
        }
        System.out.println("No match found");
        return null;
    }

    public void removeMedia(Media disc) {
        boolean found = false;
        for (int i = 0; i < itemsOrdered.size(); i++) {
            if (itemsOrdered.get(i) == disc) {
                found = true;
                for (int j = i; j < itemsOrdered.size() - 1; j++) {
                    itemsOrdered.set(j, itemsOrdered.get(j + 1));
                }
                itemsOrdered.set(itemsOrdered.size() - 1, null);
                System.out.println("The media has been removed");
                return;
            }
        }
        System.out.println("No digital video media found");
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            if (media != null) {
                total += media.getCost();
            }
        }
        return total;
    }

    public void display() {
        for (int i = 0; i < itemsOrdered.size(); i++) {
            if (itemsOrdered.get(i) != null) {
                System.out.println(i + 1 + "  " + itemsOrdered.get(i).getTitle() + "  " + itemsOrdered.get(i).getCost());
            }
        }
        System.out.println("Total cost " + totalCost());
    }

    public void print() {
        System.out.println("\n***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            if (itemsOrdered.get(i) != null) {
                System.out.println(itemsOrdered.get(i).toString());
            }
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("***************************************************");
    }

    public void search(String title) {
        for (Media media : itemsOrdered) {
            if (media != null && media.getTitle().equals(title)) {
                System.out.println(media);
                return;
            }
        }
        System.out.println("No match found");
    }

    public void search(int id) {
        for (Media media : itemsOrdered) {
            if (media != null && media.getId() == id) {
                System.out.println(media);
                return;
            }
        }
        System.out.println("No match found");
    }

    public ObservableList<Media> getItemsOrdered(){
        return itemsOrdered;
    }

    public void sortByCost(){
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }

    public void sortByTitle(){
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    public void clear(){
        itemsOrdered.clear();
    }
}