package DataClasses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Michał on 2016-06-15.
 */
public class recipe {
    private List<ingredient> recipeIngredients;
    public String recipeName;
    private int[] prepTime;
    public List<DefaultTags> recipeTags;
    private String recipeComment;


    public recipe() {
        this.recipeIngredients = new ArrayList<ingredient>();
        this.prepTime = new int[2];
        this.recipeTags = new ArrayList<DefaultTags>();
    }

    public recipe(List<ingredient> recipeIngredients, String recipeName, int[] prepTime, List<DefaultTags> recipeTags, String ingredientComment) {
        this.recipeIngredients = new ArrayList<ingredient>(recipeIngredients);
        this.recipeName = recipeName;
        this.prepTime = new int[2];
        this.prepTime = prepTime;
        this.recipeTags = new ArrayList<DefaultTags>(recipeTags);
        this.recipeComment = ingredientComment;
    }

    public void addIngredient(ingredient newIngredient){
        recipeIngredients.add(newIngredient);
    }

    public int[] getPrepTime() {
        return prepTime;
    }

    public void setRecipeComment(String recipeComment) {
        this.recipeComment = recipeComment;
    }

    public void setPrepTime(int prepTimeFrom, int prepTimeTo) {
        this.prepTime[0] = prepTimeFrom;
        this.prepTime[1] = prepTimeTo;
    }

    public void testPrint()
    {
        System.out.println(this.recipeName);
        System.out.println(this.prepTime[0]);
        System.out.println(this.prepTime[1]);
        Iterator<ingredient> it = this.recipeIngredients.iterator();
        while(it.hasNext())
        {
            ingredient tmp = it.next();
            System.out.print(tmp.ingredientName);

            System.out.print(tmp.getIngredientQuantity());
            System.out.println(tmp.getIngredientUnit());
        }
        System.out.println(this.recipeComment);

    }
}
