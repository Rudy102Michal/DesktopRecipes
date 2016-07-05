package pl.edu.pk.inf.java.GlobalData;

import pl.edu.pk.inf.java.Controllers.BrowseRecipesController;
import pl.edu.pk.inf.java.Controllers.NewRecipeController;
import pl.edu.pk.inf.java.DataClasses.Ingredient;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.pk.inf.java.RecipeContainer;

/**
 * Created by Michał on 2016-06-22.
 */
public class GlobalVars {
    public static RecipeContainer recipeContainer;
    public GlobalVars() {
        dataAvailable = false;
        recipeContainer = RecipeContainer.GetInstance();
    }

    public static Stage mainStage;
    public static NewRecipeController newRecipeController;
    public static BrowseRecipesController browseRecipesController;
    public static Scene rAddScene, rBrowseScene;

    public static boolean dataAvailable;
    public static Ingredient tempIngredient;
}
