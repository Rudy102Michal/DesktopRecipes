package pl.edu.pk.inf.java.DialogBoxes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Michał on 2016-07-01.
 */
public class DecisionBox extends PopUpBox {
    private boolean decision;

    public DecisionBox(String message, String title) {
        super(message, title);
    }

    public void displayMessage(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMaxWidth(300);
        window.setMinHeight(200);

        Label label1 = new Label(message);

        Button yesButt = new Button("Tak");
        yesButt.setOnAction(e -> {
            decision = true;
            window.close();
        });

        Button noButt = new Button("Nie");
        noButt.setOnAction(e -> {
            decision = false;
            window.close();
        });

        HBox hLayout = new HBox(10, yesButt, noButt);
        hLayout.setAlignment(Pos.CENTER);

        VBox vLayout = new VBox(10);
        vLayout.getChildren().addAll(label1, hLayout);
        vLayout.setAlignment(Pos.CENTER);

        window.setScene(new Scene(vLayout));
        window.showAndWait();
    }

    public boolean isDecision() {
        return decision;
    }
}
