package hust.soict.globalict.javafx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController{

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton eraserBtn;

    @FXML
    private RadioButton penBtn;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();

    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        Color drawColor = Color.BLACK;
        if (eraserBtn.isSelected()) {
            drawColor = Color.WHITE;
        }
        else if (penBtn.isSelected()) {
            drawColor = Color.BLACK;
        }
        Circle newCircle = new Circle(event.getX(),
                event.getY(), 4, drawColor);

        drawingAreaPane.getChildren().add(newCircle);
    }

}


