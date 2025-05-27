package hust.soict.globalict.aims.screen.customer.controller;
import hust.soict.globalict.aims.cart.Cart;
import hust.soict.globalict.aims.exception.PlayerException;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemController {
    private Media media;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    @FXML
    private Label lblCost;

    @FXML
    private Label lblTitle;
    private Cart cart;

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        cart.addMedia(media);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Added to cart");
        alert.setHeaderText("Added " + media.getTitle() + " to cart");
        alert.showAndWait();
    }

    @FXML
    void btnPlayClicked(ActionEvent event) throws PlayerException {      try {
        if (media instanceof Playable) {
            ((Playable) media).play();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Playing media");
            alert.setHeaderText("Now playing " + media.getTitle());
            alert.showAndWait();
        }
    } catch (PlayerException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Illegal DVD length");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    }

    public void setData(Media media) {
        this.media = media;
        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost() + "$");

        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        }
        else {
            btnPlay.setVisible(false);
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60));
        }
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
