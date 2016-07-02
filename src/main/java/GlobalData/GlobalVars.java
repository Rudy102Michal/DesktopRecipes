package GlobalData;

import DataClasses.Ingredient;
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

    public static boolean dataAvailable;
    public static Ingredient tempIngredient;
}
