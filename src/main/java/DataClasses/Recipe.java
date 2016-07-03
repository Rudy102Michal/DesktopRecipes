package DataClasses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Michał on 2016-06-15.
 */
public class Recipe {
    private List<Ingredient> recipeIngredients;
    public String recipeName;
    private int[] prepTime;
    public List<DefaultTags> recipeTags;
    private String recipeComment;
    private DiffGrade recipeDifficulty;


    public Recipe() {
        this.recipeIngredients = new ArrayList<Ingredient>();
        this.prepTime = new int[2];
        this.recipeTags = new ArrayList<DefaultTags>();
    }

    public Recipe(List<Ingredient> recipeIngredients, String recipeName, int[] prepTime, List<DefaultTags> recipeTags, String ingredientComment, DiffGrade recipeDifficulty) {
        this.recipeIngredients = new ArrayList<Ingredient>(recipeIngredients);
        this.recipeName = recipeName;
        this.prepTime = new int[2];
        this.prepTime = prepTime;
        this.recipeTags = new ArrayList<DefaultTags>(recipeTags);
        this.recipeComment = ingredientComment;
        this.recipeDifficulty = recipeDifficulty;
    }

    public void addIngredient(Ingredient newIngredient){
        recipeIngredients.add(newIngredient);
    }

    public List<Ingredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public String getRecipeComment() {
        return recipeComment;
    }

    public int getPrepTime(int i) {
        return prepTime[i];
    }

    public void setRecipeComment(String recipeComment) {
        this.recipeComment = recipeComment;
    }

    public void setPrepTime(int prepTimeFrom, int prepTimeTo) {
        this.prepTime[0] = prepTimeFrom;
        this.prepTime[1] = prepTimeTo;
    }

    public void setRecipeDifficulty(DiffGrade recipeDifficulty) {
        this.recipeDifficulty = recipeDifficulty;
    }

    public DiffGrade getRecipeDifficulty() {
        return recipeDifficulty;
    }

    public void testPrint()
    {
        System.out.println(this.recipeName);
        System.out.println(this.prepTime[0]);
        System.out.println(this.prepTime[1]);
        Iterator<Ingredient> it = this.recipeIngredients.iterator();
        while(it.hasNext())
        {
            Ingredient tmp = it.next();
            System.out.print(tmp.ingredientName);

            System.out.print(tmp.getIngredientQuantity());
            System.out.println(tmp.getIngredientUnit());
        }
        System.out.println(this.recipeComment);

    }

    public boolean equals2(Recipe recipeToCompare){
        if (this.recipeName.equals(recipeToCompare.recipeName) &&
                (this.prepTime == recipeToCompare.prepTime) &&
                (this.recipeTags.equals(recipeToCompare.recipeTags)) &&
                (this.recipeComment.equals(recipeToCompare.recipeComment)) &&
                (this.recipeDifficulty == recipeToCompare.recipeDifficulty) &&
                (this.recipeIngredients.equals(recipeToCompare.recipeIngredients))) return true;
        else return false;
    }
}
