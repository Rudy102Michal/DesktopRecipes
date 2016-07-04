package pl.edu.pk.inf.java;
import GlobalData.GlobalVars;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Natalie on 2016-07-02.
 */
class ReadFileCommand implements Command {
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

                List<ingredient> r = new ArrayList<ingredient>();
                while (iterator.hasNext()) {
                    JSONObject el = iterator.next();
                    ingredient in = new ingredient((String) el.get("Ingredient"), Double.parseDouble((String) el.get("Quantity")), Unit.valueOf((String) el.get("Unit")));
                    r.add(in);
                }
                JSONArray tag = (JSONArray) a.get("Tags");
                Iterator<DefaultTags> iterator2 = tag.iterator();
                List<DefaultTags> tt = new ArrayList<DefaultTags>();
                while (iterator2.hasNext()) {
                    tt.add(iterator2.next());
                }
                int pp[] = {Integer.parseInt((String) a.get("Prep time")), Integer.parseInt((String) a.get("Prep time2"))};
                recipe tmp = new recipe(r, (String) a.get("Name"), pp, tt);
                GlobalVars.Przepisy.addRecipe(tmp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
