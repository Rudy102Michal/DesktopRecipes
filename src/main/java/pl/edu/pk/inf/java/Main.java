package pl.edu.pk.inf.java;

import pl.edu.pk.inf.java.Controllers.BrowseRecipesController;
import pl.edu.pk.inf.java.Controllers.NewRecipeController;
import pl.edu.pk.inf.java.DialogBoxes.AlertBox;
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

        readRecipesFromFile();          //Tu wczytujemy wszystkie przepisy z pliku.
                                        //Tuż przed startem głównego okna
        primaryStage.setTitle("Menager przepisów 42");
        primaryStage.setOnCloseRequest( e -> {
            AlertBox mess = new AlertBox("Przepisy zostały zapisane.\nZostaną wczytane po ponownym\nuruchomieniu programu.", "Koniec programu");
            mess.displayMessage();
            saveRecipesToFile();
        });
        primaryStage.setResizable(false);
        primaryStage.setScene(GlobalVars.rAddScene);
        GlobalVars.mainStage = primaryStage;
        GlobalVars.mainStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }

    private void readRecipesFromFile() {

        //Tu ma być odczyt przepisów z pliku.
        ReadFileCommand read = new ReadFileCommand();
        FileCommand file = new FileCommand();//wykonywacz komend
        file.setMode(read); // ustawiamy wykonywacz komend w tryb zapisu
        file.execute(); // wykonywacz komend wykonuje zapis
    }

    private void saveRecipesToFile() {

        //Tu ma być zapis przepisów do pliku.

        SaveFileCommand save = new SaveFileCommand();
        FileCommand file = new FileCommand();//wykonywacz komend
        file.setMode(save);
        file.execute();

    }
}
