package hust.soict.globalict.test.screen.customer.store;



import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.screen.customer.controller.ViewStoreController;
import hust.soict.globalict.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.naming.LimitExceededException;

public class TestViewStoreScreen extends Application {
    private static Store store;
    private static Cart cart;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/hust/soict/globalict/aims/screen/customer/view/Store.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        ViewStoreController viewStoreController = new ViewStoreController(store, cart);
        fxmlLoader.setController(viewStoreController);
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) throws LimitExceededException {
        cart = new Cart();
        store = new Store();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Cartoon", 13.6f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Fantasy", "hihihaha", 24, 19.5f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Horror", "Conmeo", 12, 30.2f);
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        Book book1 = new Book("ConChicken", "Fantasy", 12.6f);
        book1.addAuthor("ConWolf");
        store.addMedia(book1);

        CompactDisc cd1 = new CompactDisc("Helo", "Fiction", 12.3f);
        store.addMedia(cd1);

        CompactDisc cd3 = new CompactDisc(1002, "As", "Fiction", 20.3f, 20, "Alahuakbra");
        store.addMedia(cd3);

        CompactDisc cd4 = new CompactDisc(1003, "BlackHuman", "Fantasy", 10.2f, 10, "White");
        store.addMedia(cd4);
        launch(args);
    }
}
