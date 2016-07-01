package DialogBoxes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Michał on 2016-07-01.
 */
public class AlertBox extends PopUpBox {

    public void displayMessage(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMaxWidth(300);
        window.setMinHeight(200);

        Label label1 = new Label(message);
        Button okButt = new Button("Ok");
        okButt.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, okButt);
        layout.setAlignment(Pos.CENTER);

        window.setScene(new Scene(layout));
        window.showAndWait();
    }

    public AlertBox(String message, String title) {
        super(message, title);
    }
}
