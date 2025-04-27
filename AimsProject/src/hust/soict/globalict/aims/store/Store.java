package hust.soict.globalict.aims.store;

import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Media;

import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemInStore = new ArrayList<>();

    public void addMedia(Media media) {
        if (!itemInStore.contains(media)) {
            itemInStore.add(media);
            System.out.println("Media added to store");
        }
    }

    public Media searchMedia(String title) {
        for (Media media : itemInStore) {
            if (media.getTitle().equals(title)) {
                return media;
            }
        }
        return null;
    }


    public void removeMedia(Media media) {
        if (itemInStore.remove(media)) {
            System.out.println("Media removed from store");
        } else {
            System.out.println("Media not found in store");
        }
    }

    public ArrayList<Media> getItemsInStore(){
        return itemInStore;
    }

}
