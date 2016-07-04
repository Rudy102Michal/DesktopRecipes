package Controllers;

import DataClasses.Recipe;
import GlobalData.GlobalVars;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michał on 2016-07-02.
 */
public abstract class MainController implements Initializable {

    public ToggleButton buttRecipeAdding;
    public ToggleButton buttRecipesBrowsing;
    public ToggleGroup menuToggleGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        buttRecipeAdding.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
            if(menuToggleGroup.getSelectedToggle().equals(buttRecipeAdding))
                e.consume();
        });

        buttRecipesBrowsing.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
            if(menuToggleGroup.getSelectedToggle().equals(buttRecipesBrowsing))
                e.consume();
        });

        buttRecipeAdding.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if(!menuToggleGroup.getSelectedToggle().equals(buttRecipeAdding))
                buttRecipeAddingClicked();
        });

        buttRecipesBrowsing.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if(!menuToggleGroup.getSelectedToggle().equals(buttRecipesBrowsing))
                buttRecipesBrowsingClicked();
        });
    }

    public void buttRecipeAddingClicked() {
        System.out.print("A");
        buttRecipeAdding.setSelected(true);
        GlobalVars.mainStage.setScene(GlobalVars.rAddScene);
    }

    public void buttRecipesBrowsingClicked() {
        System.out.print("B");
        buttRecipesBrowsing.setSelected(true);
        GlobalVars.mainStage.setScene(GlobalVars.rBrowseScene);
    }

    public void displayRecipeToUser(Recipe recipeToDisp) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../displayRecipe.fxml"));

            Parent window = loader.load();
            DispRecipeController controller = loader.getController();
            controller.setRecipeToShow(recipeToDisp);
            controller.setLabels();

            stage.setScene(new Scene(window));
            stage.showAndWait();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
