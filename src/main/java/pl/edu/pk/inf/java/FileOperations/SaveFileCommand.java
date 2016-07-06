package pl.edu.pk.inf.java.FileOperations;
import java.io.FileWriter;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pl.edu.pk.inf.java.DataClasses.DefaultTags;
import pl.edu.pk.inf.java.DataClasses.Ingredient;
import pl.edu.pk.inf.java.DataClasses.Recipe;
import pl.edu.pk.inf.java.FileOperations.Command;
import pl.edu.pk.inf.java.GlobalData.GlobalVars;

/**
 * Created by Natalie/Karol on 2016-07-02.
 */
public class SaveFileCommand implements Command {
    public void execute() {
        try
        {
            FileWriter file = new FileWriter("recipes.txt");
            JSONObject Main = new JSONObject();
            JSONArray arr = new JSONArray();
            while(GlobalVars.recipeContainer.getIterator().hasNext())
            {

                Recipe el =  GlobalVars.recipeContainer.getIterator().next();

                JSONObject tmp = new JSONObject();
                tmp.put("Name",el.recipeName);
                tmp.put("Prep time",( String.valueOf(el.getPrepTime(0))));
                tmp.put("Prep time2",String.valueOf(el.getPrepTime(1)));

                JSONArray temp_array_ingredient = new JSONArray();
                java.util.Iterator<Ingredient> it  = el.getRecipeIngredients().iterator();
                while(it.hasNext())
                {
                    Ingredient temp_ing = it.next();
                    JSONObject temp_ingredients  = new JSONObject();
                    temp_ingredients.put("Ingredient",temp_ing.ingredientName);
                    temp_ingredients.put("Quantity",String.valueOf(temp_ing.getIngredientQuantity()));
                    temp_ingredients.put("Unit",String.valueOf(temp_ing.getIngredientUnit().name()));
                    temp_array_ingredient.add(temp_ingredients);
                }
                tmp.put("Ingredients",temp_array_ingredient); //dodawanie skladnikow

                JSONArray temp_array_tags = new JSONArray();
                Iterator<DefaultTags> it2 = el.recipeTags.iterator();
                while(it2.hasNext())
                {

                    temp_array_tags.add(String.valueOf(it2.next()));
                }
                tmp.put("Tags",temp_array_tags);
                tmp.put("Comment",el.getRecipeComment());
                tmp.put("Difficulty",el.getRecipeDifficulty().name());

                arr.add(tmp);



            }
            Main.put("Recipe List",arr);
            file.write(Main.toJSONString());
            file.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
