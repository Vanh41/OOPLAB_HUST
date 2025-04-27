package hust.soict.globalict.aims;
import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.store.Store;

import java.util.Scanner;

public class Aims {
    static Scanner scanner = new Scanner(System.in);
    static Store store = new Store();
    static Cart cart = new Cart();


    public static void showMenu() {
        while (true) {
            System.out.println("AIMS: ");
            System.out.println("-------------------------------");
            System.out.println("1. View store");
            System.out.println("2. Update store");
            System.out.println("3. See current cart");
            System.out.println("0. Exit");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3");
            switch (scanner.nextInt()) {
                case 1:
                    storeMenu();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    cartMenu();
                    break;
                case 0:
                    System.out.println("Thank you for using AIMS!");
                    return;
                default:
                    System.out.println("Please choose a number: 0-1-2-3");
            }
        }
    }


    public static void storeMenu() {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. See a media’s details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4");
            switch (scanner.nextInt()) {
                case 1:
//                    seeMediaDetails();
                    mediaDetailsMenu();
                    break;
                case 2:
                    addMedia();
                    break;
                case 3:
                    playMedia();
                    break;
                case 4:
                    cart.print();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Please choose a number: 0-1-2-3-4");
            }
        }
    }

    public static void updateStore() {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add a media");
            System.out.println("2. Remove a media");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2");
            scanner.nextLine();
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Enter media type (1. Book, 2. DVD, 3. CD):");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter media title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter media category (e.g. Fantasy, Science Fiction, etc.):");
                    String category = scanner.nextLine();
                    System.out.println("Enter media cost:");
                    float cost = scanner.nextFloat();
                    Media media = null;

                    if (type == 1) {
                        media = new Book(title, category, cost);
                    } else if (type == 2) {
                        media = new DigitalVideoDisc(title, "Category", "Director", 120, cost);
                    } else if (type == 3) {
                        media = new CompactDisc(title, category, cost);
                    }

                    if (media != null) {
                        store.addMedia(media);
                        System.out.println("Media added successfully!");
                    }
                    break;
                case 2:
                    System.out.println("Enter media title:");
                    scanner.nextLine(); // Clear buffer
                    title = scanner.nextLine();
                    media = store.searchMedia(title);
                    if (media != null) {
                        store.removeMedia(media);
                        System.out.println("Media removed successfully!");
                    } else {
                        System.out.println("Media not found!");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Please choose a number: 0-1-2");
                    break;
            }
        }
    }


    public static void seeMediaDetails() {
        System.out.println("Enter media title:");
        String title = scanner.next();
        Media media = store.searchMedia(title);
        if (media != null) {
            System.out.println(media);
        } else {
            System.out.println("No media found");
        }
    }

    public static void addMedia() {
        System.out.println("Enter media title:");
        String title = scanner.next();
        cart.addMedia(store.searchMedia(title));
    }

    public static void playMedia() {
        System.out.println("Enter media title:");
        String title = scanner.next();
        Media media = store.searchMedia(title);
        if (media == null) {
            System.out.println("No media found");
            return;
        }
        if (media.getClass() == DigitalVideoDisc.class) {
            DigitalVideoDisc dvd = (DigitalVideoDisc) media;
            System.out.println("Playing " + dvd.getTitle());
            dvd.play();
        }
        if (media.getClass() == CompactDisc.class) {
            CompactDisc cd = (CompactDisc) media;
            System.out.println("Playing " + cd.getTitle());
            cd.play();
        }
        if (media.getClass() == Book.class) {
            System.out.println("Not playable");
        }
    }

    public static void mediaDetailsMenu() {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            System.out.println("2. Play");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2");
            switch (scanner.nextInt()) {
                case 1:
                    addMedia();
                    break;
                case 2:
                    playMedia();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Please choose a number: 0-1-2");
                    break;
            }
        }
    }

    public static void cartMenu() {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter media in cart");
            System.out.println("2. Sort media in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4-5");

            switch (scanner.nextInt()) {
                case 1:
                    filterMedia();
                    break;
                case 2:
                    sortMedia();
                    break;
                case 3:
                    removeMedia();
                    break;
                case 4:
                    playMedia();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Please choose a number: 0-1-2-3-4-5");
            }
        }
    }

    private static void filterMedia() {
        System.out.println("Filter by:");
        System.out.println("1. Id");
        System.out.println("2. Title");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Enter id:");
            int id = scanner.nextInt();
            cart.search(id);
        } else if (choice == 2) {
            System.out.println("Enter title:");
            String title = scanner.next();
            cart.search(title);
        }
    }

    private static void sortMedia() {
        System.out.println("Sort by:");
        System.out.println("1. Title");
        System.out.println("2. Cost");
        int choice = scanner.nextInt();
        if (choice == 1) {
            cart.sortByTitle();
        } else if (choice == 2) {
            cart.sortByCost();
        }
    }

    private static void removeMedia() {
        System.out.println("Enter media title to remove:");
        String title = scanner.next();
        cart.removeMedia(store.searchMedia(title));
    }

    public static void placeOrder() {
        System.out.println("Order placed. Cart is now empty.");
        cart = new Cart();
    }

    public static void main(String[] args) {
        while (true) {
            showMenu();
            return;
        }
    }
}