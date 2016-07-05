package pl.edu.pk.inf.java;

import java.util.ArrayList;
import java.util.List;
import pl.edu.pk.inf.java.DataClasses.*;
import pl.edu.pk.inf.java.DataClasses.DiffGrade;

/**
 * Created by Michał on 2016-06-08.
 */
public class Recipes_Desktop {
    public static void main(String[] args) {
        pl.edu.pk.inf.java.DataClasses.Ingredient in1 = new Ingredient("Jajko", 2, pl.edu.pk.inf.java.DataClasses.Unit.AMOUNT);
        pl.edu.pk.inf.java.DataClasses.Ingredient in2 = new Ingredient("Bekon", 20, pl.edu.pk.inf.java.DataClasses.Unit.DG);
        ArrayList<pl.edu.pk.inf.java.DataClasses.DefaultTags> tt = new ArrayList<>();
        tt.add(pl.edu.pk.inf.java.DataClasses.DefaultTags.ALCOHOL);
        tt.add(pl.edu.pk.inf.java.DataClasses.DefaultTags.ENGLISH);

        List<pl.edu.pk.inf.java.DataClasses.Ingredient>  rr = new ArrayList<>();
        rr.add(in1);
        rr.add(in2);
        rr.add(in2);

        int pp[] = {20, 25};
        pl.edu.pk.inf.java.DataClasses.Recipe tmp = new pl.edu.pk.inf.java.DataClasses.Recipe(rr, "Jajecznica", pp, tt, "whatever", DiffGrade.BASIC);
        RecipeContainer kont = RecipeContainer.GetInstance();
        kont.addRecipe(tmp);
        tmp = new pl.edu.pk.inf.java.DataClasses.Recipe(rr, "Jajecznica2", pp, tt, "whatever", DiffGrade.BASIC);
        kont.addRecipe(tmp);
        tmp = new pl.edu.pk.inf.java.DataClasses.Recipe(rr, "Jajecznica3", pp, tt, "whatever", DiffGrade.BASIC);
        kont.addRecipe(tmp);
        tmp = new pl.edu.pk.inf.java.DataClasses.Recipe(rr, "Jajecznica", pp, tt, "whatever", DiffGrade.BASIC);
        kont.addRecipe(tmp);
        tmp = new pl.edu.pk.inf.java.DataClasses.Recipe(rr, "Jajecznica3", pp, tt, "whatever", DiffGrade.BASIC);
        //tt.remove(1);

        Iterator iter = kont.getIterator();
        while(iter.hasNext()){
            iter.next().testPrint();
            System.out.println();
        }
        //tt.clear();
        Recipe tmp2 = new pl.edu.pk.inf.java.DataClasses.Recipe(rr, "Jajecznica", pp, tt, "whatever", DiffGrade.BASIC);
        ArrayList<Recipe> temp2 = new ArrayList();
        kont.change(tmp,tmp2);
        iter = kont.getIterator();
        while(iter.hasNext())iter.next().testPrint();
    }
}
