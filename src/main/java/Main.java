import Controllers.BrowseRecipesController;
import Controllers.NewIngredientController;
import Controllers.NewRecipeController;
import GlobalData.GlobalVars;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader rAddLoader, rBrowseLoader;
        rAddLoader = new FXMLLoader(getClass().getResource("newRecipe.fxml"));
        rBrowseLoader = new FXMLLoader(getClass().getResource("browseRecipes.fxml"));

        GlobalVars.newRecipeController = new NewRecipeController();
        GlobalVars.browseRecipesController = new BrowseRecipesController();

        rAddLoader.setController(GlobalVars.newRecipeController);
        rBrowseLoader.setController(GlobalVars.browseRecipesController);

        GlobalVars.rAddScene = new Scene(rAddLoader.load());
        GlobalVars.rBrowseScene = new Scene(rBrowseLoader.load());

        //GlobalVars.newRecipeController.buttRecipesBrowsing.selectedProperty().bind(GlobalVars.browseRecipesController.buttRecipesBrowsing.selectedProperty());
        //GlobalVars.newRecipeController.buttRecipeAdding.selectedProperty().bind(GlobalVars.browseRecipesController.buttRecipeAdding.selectedProperty());

        Bindings.bindBidirectional(GlobalVars.newRecipeController.buttRecipeAdding.selectedProperty(), GlobalVars.browseRecipesController.buttRecipeAdding.selectedProperty());
        Bindings.bindBidirectional(GlobalVars.newRecipeController.buttRecipesBrowsing.selectedProperty(), GlobalVars.browseRecipesController.buttRecipesBrowsing.selectedProperty());

        primaryStage.setTitle("Menager przepisów 42");
        primaryStage.setScene(GlobalVars.rAddScene);
        GlobalVars.mainStage = primaryStage;
        GlobalVars.mainStage.show();
    }


    public static void main(String[] args) {

        final GlobalVars appGlobalVars = new GlobalVars();
        launch(args);
    }
}
