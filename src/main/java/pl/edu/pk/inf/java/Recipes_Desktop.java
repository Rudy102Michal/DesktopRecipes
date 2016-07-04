package pl.edu.pk.inf.java;

import GlobalData.GlobalVars;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michał on 2016-06-08.
 */
public class Recipes_Desktop {
    public static void main(String[] args) {
        GlobalVars.Przepisy = RecipeContainer.GetInstance();
        ingredient in1 = new ingredient("Jajko", 2, Unit.AMOUNT);
        ingredient in2 = new ingredient("Bekon", 20, Unit.DG);
        List<DefaultTags> tt = new ArrayList<DefaultTags>();
        tt.add(DefaultTags.ALCOHOL);

        List<ingredient>  rr = new ArrayList<ingredient>();
        rr.add(in1);
        rr.add(in2);
        rr.add(in2);

        int pp[] = {20, 25};
        recipe tmp = new recipe(rr, "Jajecznica", pp, tt);

        tmp.testPrint();

        ReadFileCommand read = new ReadFileCommand();
        FileCommand file = new FileCommand();
        file.setMode(read);
        file.execute();
        SaveFileCommand save = new SaveFileCommand();
        file.setMode(save);
        file.execute();
    }
}
