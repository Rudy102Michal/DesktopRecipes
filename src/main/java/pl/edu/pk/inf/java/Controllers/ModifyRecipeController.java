package pl.edu.pk.inf.java.Controllers;

import javafx.collections.ObservableList;
import pl.edu.pk.inf.java.DataClasses.Recipe;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michał on 2016-07-05.
 */
public class ModifyRecipeController extends NewRecipeController {

    private BrowseRecipesController parentController;

    private ObservableList<ObservableIngredient> observableRecipeIngredients;
    private Recipe recipeToModify;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }

    public ModifyRecipeController() {
    }
}
