package hust.soict.globalict.aims.cart;

import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.MediaComparatorByCostTitle;
import java.util.Collections;
import java.util.ArrayList;

public class Cart {
    private static final int MAX_NUMBER_ORDERED = 20;
    private final ArrayList<Media> itemsOrdered = new ArrayList<>();

    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("No media found");
            return;
        }
        if (itemsOrdered.size() < MAX_NUMBER_ORDERED) {
            itemsOrdered.add(media);
            System.out.println("The disc has been added");
        } else {
            System.out.println("The cart is almost full");
        }
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

    public void sortByCost(){
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }

    public void sortByTitle(){
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }
}