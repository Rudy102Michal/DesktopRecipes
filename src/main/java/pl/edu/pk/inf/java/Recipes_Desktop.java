package pl.edu.pk.inf.java;

import java.util.ArrayList;
import java.util.List;
import DataClasses.*;
import DataClasses.DefaultTags;
import DataClasses.DiffGrade;

/**
 * Created by Michał on 2016-06-08.
 */
public class Recipes_Desktop {
    public static void main(String[] args) {
        DataClasses.Ingredient in1 = new Ingredient("Jajko", 2, DataClasses.Unit.AMOUNT);
        DataClasses.Ingredient in2 = new Ingredient("Bekon", 20, DataClasses.Unit.DG);
        ArrayList<DataClasses.DefaultTags> tt = new ArrayList<>();
        tt.add(DataClasses.DefaultTags.ALCOHOL);
        tt.add(DataClasses.DefaultTags.ENGLISH);

        List<DataClasses.Ingredient>  rr = new ArrayList<>();
        rr.add(in1);
        rr.add(in2);
        rr.add(in2);

        int pp[] = {20, 25};
        DataClasses.Recipe tmp = new DataClasses.Recipe(rr, "Jajecznica", pp, tt, "whatever", DiffGrade.BASIC);
        RecipeContainer kont = RecipeContainer.GetInstance();
        kont.addRecipe(tmp);
        tmp = new DataClasses.Recipe(rr, "Jajecznica2", pp, tt, "whatever", DiffGrade.BASIC);
        kont.addRecipe(tmp);
        tmp = new DataClasses.Recipe(rr, "Jajecznica3", pp, tt, "whatever", DiffGrade.BASIC);
        kont.addRecipe(tmp);
        tmp = new DataClasses.Recipe(rr, "Jajecznica", pp, tt, "whatever", DiffGrade.BASIC);
        kont.addRecipe(tmp);
        //tt.remove(1);

        Iterator iter = kont.getIterator();
        while(iter.hasNext()){
            iter.next().testPrint();
            System.out.println();
        }
        //tt.clear();
        /*ArrayList<Recipe> temp2 = new ArrayList();
        temp2 = kont.findRecipe(tt,"");
        System.out.println(temp2.size());*/
        tmp = new DataClasses.Recipe(rr, "Jajecznica", pp, tt, "whatever", DiffGrade.BASIC);
        kont.eraseRecipe(tmp);
        iter = kont.getIterator();
        while(iter.hasNext())iter.next().testPrint();
    }
}
