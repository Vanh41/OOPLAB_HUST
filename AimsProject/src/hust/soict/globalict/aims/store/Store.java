package hust.soict.globalict.aims.store;

import hust.soict.globalict.aims.media.DigitalVideoDisc;

public class Store {
    private DigitalVideoDisc[]itemInStore = new DigitalVideoDisc[10000];
    private int qtyInStore = 0;

    public void addDVD(DigitalVideoDisc dvd){
        for (int i = 0; i < qtyInStore; i++) {
            if (itemInStore[i].getTitle().equals(dvd.getTitle())) {
                return;
            }
        }
        itemInStore[qtyInStore] = dvd;
        System.out.println("DVD added to store");
        qtyInStore++;
    }

    public void removeDVD(DigitalVideoDisc dvd){
        boolean found = false;
        for (int i = 0; i < qtyInStore; i++) {
            if (itemInStore[i].getTitle().equals(dvd.getTitle())) {
                found = true;
                for (int j = i; j < qtyInStore - 1; j++) {
                    itemInStore[j] = itemInStore[j + 1];
                }
                itemInStore[qtyInStore - 1] = null;
                qtyInStore--;
                System.out.println("DVD removed from store");
                return;
            }
        }
        System.out.println("DVD not found in store");
    }
}
