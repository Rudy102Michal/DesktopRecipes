package pl.edu.pk.inf.java;
import java.io.FileWriter;
import java.util.Iterator;

import GlobalData.GlobalVars;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
            while(GlobalVars.Przepisy.getIterator().hasNext())
            {

                recipe el =  GlobalVars.Przepisy.getIterator().next();

                JSONObject tmp = new JSONObject();
                tmp.put("Name",el.recipeName);
                tmp.put("Prep time",( String.valueOf(el.getPrep()[0])));
                tmp.put("Prep time2",String.valueOf(el.getPrep()[1]));

                JSONArray temp_array_ingredient = new JSONArray();
                java.util.Iterator<ingredient> it  = el.recipeIngredients.iterator();
                while(it.hasNext())
                {
                    ingredient temp_ing = it.next();
                    JSONObject temp_ingredients  = new JSONObject();
                    temp_ingredients.put("Ingredient",temp_ing.ingredientName);
                    temp_ingredients.put("Quantity",String.valueOf(temp_ing.getIngredientQuantity()));
                    temp_ingredients.put("Unit",String.valueOf(temp_ing.getIngredientUnit()));
                    temp_array_ingredient.add(temp_ingredients);
                }
                tmp.put("Ingredients",temp_array_ingredient); //dodawanie skladnikow

                JSONArray temp_array_tags = new JSONArray();
                Iterator<DefaultTags> it2 = el.recipeTags.iterator();
                while(it2.hasNext())
                {

                    temp_array_tags.add(it2.next());
                }
                tmp.put("Tags",temp_array_tags);

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
