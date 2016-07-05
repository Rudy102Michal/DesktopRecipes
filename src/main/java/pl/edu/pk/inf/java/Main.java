package pl.edu.pk.inf.java;

import pl.edu.pk.inf.java.Controllers.BrowseRecipesController;
import pl.edu.pk.inf.java.Controllers.NewRecipeController;
import pl.edu.pk.inf.java.GlobalData.GlobalVars;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    final GlobalVars appGlobalVars= new GlobalVars();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader rAddLoader, rBrowseLoader;
        rAddLoader = new FXMLLoader(getClass().getResource("/newRecipe.fxml"));
        rBrowseLoader = new FXMLLoader(getClass().getResource("/browseRecipes.fxml"));

        GlobalVars.newRecipeController = new NewRecipeController();
        GlobalVars.browseRecipesController = new BrowseRecipesController();

        rAddLoader.setController(GlobalVars.newRecipeController);
        rBrowseLoader.setController(GlobalVars.browseRecipesController);

        GlobalVars.rAddScene = new Scene(rAddLoader.load());
        GlobalVars.rBrowseScene = new Scene(rBrowseLoader.load());

        Bindings.bindBidirectional(GlobalVars.newRecipeController.buttRecipeAdding.selectedProperty(), GlobalVars.browseRecipesController.buttRecipeAdding.selectedProperty());
        Bindings.bindBidirectional(GlobalVars.newRecipeController.buttRecipesBrowsing.selectedProperty(), GlobalVars.browseRecipesController.buttRecipesBrowsing.selectedProperty());

        primaryStage.setTitle("Menager przepisów 42");
        primaryStage.setResizable(false);
        primaryStage.setScene(GlobalVars.rAddScene);
        GlobalVars.mainStage = primaryStage;
        GlobalVars.mainStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
