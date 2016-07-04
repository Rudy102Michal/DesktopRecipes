package pl.edu.pk.inf.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Michał on 2016-06-08.
 *
 * Recipe class.
 * Indicative preparation time for pl.edu.pk.inf.java.Recipe, prepTime, should always be 2 element array. Ex.: 15-20min
 */
public class recipe {
    public List<ingredient> recipeIngredients;
    public String recipeName;
    private int[] prepTime;
    public List<DefaultTags> recipeTags;

    public recipe(List<ingredient> recipeIngredients, String recipeName, int[] prepTime, List<DefaultTags> recipeTags) {
        this.recipeIngredients = new ArrayList<ingredient>(recipeIngredients);
        this.recipeName = recipeName;
        this.prepTime = new int[2];
        this.prepTime = prepTime;
        this.recipeTags = new ArrayList<DefaultTags>(recipeTags);
    }

    public void addIngredient(ingredient newIngredient){
        recipeIngredients.add(newIngredient);
    }

    public int[] getPrep()
    {
        return prepTime;
    }


    public void testPrint()
    {
        System.out.println(this.recipeName);
        System.out.println(this.prepTime[0]);
        System.out.println(this.prepTime[1]);
        Iterator<ingredient> it = recipeIngredients.iterator();
        while(it.hasNext())
        {
            ingredient tmp = it.next();
            System.out.println(tmp.ingredientName);
            System.out.println(tmp.getIngredientQuantity());
            System.out.println(tmp.getIngredientUnit());
        }
    }
}
