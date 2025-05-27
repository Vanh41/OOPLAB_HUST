package hust.soict.globalict.aims;

import javax.swing.JOptionPane;
import java.util.Scanner;
import hust.soict.globalict.aims.exception.PlayerException;
import hust.soict.globalict.aims.media.*;
import hust.soict.globalict.aims.store.Store;
import hust.soict.globalict.aims.cart.Cart;

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
            System.out.println("1. See a media's details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4");
            switch (scanner.nextInt()) {
                case 1:
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
                        System.out.println("Enter director:");
                        scanner.nextLine();
                        String director = scanner.nextLine();
                        System.out.println("Enter length:");
                        int length = scanner.nextInt();
                        media = new DigitalVideoDisc(title, category, director, length, cost);
                        try {
                            ((DigitalVideoDisc) media).play();
                        } catch (PlayerException e) {
                            JOptionPane.showMessageDialog(null,
                                    e.getMessage(),
                                    "Illegal DVD Length",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (type == 3) {
                        System.out.println("Enter artist:");
                        scanner.nextLine();
                        String artist = scanner.nextLine();
                        CompactDisc cd = new CompactDisc(title, category, cost);
                        cd.setArtist(artist);

                        System.out.println("Do you want to add tracks? (y/n)");
                        String choice = scanner.next();
                        if (choice.equalsIgnoreCase("y")) {
                            addTracksToCD(cd);
                        }

                        media = cd;
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

    private static void addTracksToCD(CompactDisc cd) {
        while (true) {
            System.out.println("Enter track title (or 'exit' to finish):");
            scanner.nextLine(); // Clear buffer
            String trackTitle = scanner.nextLine();

            if (trackTitle.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Enter track length :");
            int trackLength = scanner.nextInt();

            Track track = new Track(trackTitle, trackLength);
            cd.addTrack(track);
            System.out.println("Track added!");
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
        Media media = store.searchMedia(title);
        if (media != null) {
            cart.addMedia(media);
            System.out.println("Added " + media.getTitle() + " to cart.");
        } else {
            System.out.println("Media not found!");
        }
    }

    public static void playMedia() {
        System.out.println("Enter media title:");
        String title = scanner.next();
        Media media = store.searchMedia(title);

        if (media == null) {
            System.out.println("No media found with the given title.");
            return;
        }

        try {
            if (media instanceof Playable) {
                Playable playable = (Playable) media;
                if (media instanceof DigitalVideoDisc) {
                    System.out.println("Playing DVD: " + media.getTitle());
                } else if (media instanceof CompactDisc) {
                    System.out.println("Playing CD: " + media.getTitle());
                }
                playable.play();
            } else {
                System.out.println("This media type is not playable.");
            }
        } catch (PlayerException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Exception toString: " + e.toString());
            System.err.println("Stack trace:");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error: " + e.getMessage(),
                "PlayerException",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void mediaDetailsMenu() {
        System.out.println("Enter media title:");
        String title = scanner.next();
        Media media = store.searchMedia(title);

        if (media == null) {
            System.out.println("No media found with that title!");
            return;
        }

        System.out.println(media);

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
                    cart.addMedia(media);
                    System.out.println("Added " + media.getTitle() + " to cart.");
                    break;
                case 2:
                    if (media instanceof Playable) {
                        try {
                            ((Playable) media).play();
                        } catch (PlayerException e) {
                            System.err.println("Error: " + e.getMessage());
                            System.err.println("Exception toString: " + e.toString());
                            System.err.println("Stack trace:");
                            e.printStackTrace();

                            JOptionPane.showMessageDialog(null,
                                "Error: " + e.getMessage(),
                                "PlayerException",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        System.out.println("This media is not playable!");
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
                    playMediaFromCart();
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
        Media media = cart.searchMedia(title);
        if (media != null) {
            cart.removeMedia(media);
            System.out.println("Removed " + media.getTitle() + " from cart.");
        } else {
            System.out.println("Media not found in cart!");
        }
    }

    private static void playMediaFromCart() {
        System.out.println("Enter media title to play:");
        String title = scanner.next();
        Media media = cart.searchMedia(title);

        if (media == null) {
            System.out.println("No media found in cart with the given title.");
            return;
        }

        try {
            if (media instanceof Playable) {
                Playable playable = (Playable) media;
                playable.play();
            } else {
                System.out.println("This media type is not playable.");
            }
        } catch (PlayerException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Exception toString: " + e.toString());
            System.err.println("Stack trace:");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error: " + e.getMessage(),
                "PlayerException",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void placeOrder() {
        System.out.println("Order placed. Cart is now empty.");
        cart = new Cart();
    }

    public static void main(String[] args) {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", -120, 24.95f); // Negative length to trigger exception

        CompactDisc cd1 = new CompactDisc("Greatest Hits", "Music", 15.95f);
        cd1.setArtist("Various Artists");
        cd1.addTrack(new Track("Song 1", 180));
        cd1.addTrack(new Track("Song 2", -30));

        Book book1 = new Book("Harry Potter", "Fantasy", 29.95f);

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(cd1);
        store.addMedia(book1);


        try {
            showMenu();
        } catch (Exception e) {
            System.err.println("Application terminated unexpectedly: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Application error: " + e.getMessage(),
                "Unexpected Error",
                JOptionPane.ERROR_MESSAGE);
        } finally {
            scanner.close();
        }
    }
}