package pl.edu.pk.inf.java;


import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import pl.edu.pk.inf.java.DataClasses.*;
import pl.edu.pk.inf.java.GlobalData.GlobalVars;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by Berrion on 2016-07-05.
 */
public class ReadFileCommand implements Command {
    public void execute(){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("recipes.txt"));
            JSONObject jsonObj = (JSONObject) obj;
            JSONArray rec = (JSONArray) jsonObj.get("Recipe List");


            for(int i = 0 ; i < rec.size() ;i++) {
                JSONObject a = (JSONObject) rec.get(i);
                JSONArray ing = (JSONArray) a.get("Ingredients");
                Iterator<JSONObject> iterator = ing.iterator();

                List<Ingredient> r = new ArrayList<Ingredient>();
                while (iterator.hasNext()) {
                    JSONObject el = iterator.next();
                    Ingredient in = new Ingredient((String) el.get("Ingredient"), Double.parseDouble((String) el.get("Quantity")), Unit.valueOf((String) el.get("Unit")));
                    r.add(in);
                }
                JSONArray tag = (JSONArray) a.get("Tags");
                Iterator<DefaultTags> iterator2 = tag.iterator();
                List<DefaultTags> tt = new ArrayList<DefaultTags>();
                while (iterator2.hasNext()) {
                    tt.add(iterator2.next());
                }

                int pp[] = {Integer.parseInt((String) a.get("Prep time")), Integer.parseInt((String) a.get("Prep time2"))};
                Recipe tmp = new Recipe(r, (String) a.get("Name"), pp, tt,(String) a.get("Comment"),DiffGrade.valueOf((String) a.get("Difficulty")));
                GlobalVars.recipeContainer.addRecipe(tmp);


            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
