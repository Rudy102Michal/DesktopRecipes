import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.edu.pk.inf.java.DataClasses.*;
import pl.edu.pk.inf.java.RecipeContainer;

public class RecipeContainerTest
{
    private RecipeContainer recipeContainer = RecipeContainer.GetInstance();
    private Recipe sampleRecipe;
    private Recipe sampleRecipe2;
    private Field count = null;
    private Field recipeList = null;

    @Before
    public void setUp() throws IllegalAccessException
    {
        Class<?> c = this.recipeContainer.getClass();

        try {
            this.count = c.getDeclaredField("count");
            this.recipeList = c.getDeclaredField("RecipeList");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        this.count.setAccessible(true);
        this.recipeList.setAccessible(true);

        List<Ingredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(new Ingredient("pasta", 1.0, Unit.PACK));
        List<DefaultTags> recipeTags = new ArrayList<>();
        recipeTags.add(DefaultTags.EUROPEAN);
        recipeTags.add(DefaultTags.ITALIAN);
        this.sampleRecipe = new Recipe(recipeIngredients, "Spaghetti", new int[]{20,30}, recipeTags, "Comment", DiffGrade.BASIC);

        List<Ingredient> recipeIngredients2 = new ArrayList<>();
        recipeIngredients2.add(new Ingredient("flour", 1.0, Unit.KG));
        List<DefaultTags> recipeTags2 = new ArrayList<>();
        recipeTags2.add(DefaultTags.EUROPEAN);
        recipeTags2.add(DefaultTags.FRENCH);

        this.sampleRecipe2 = new Recipe(recipeIngredients2, "Macaroon", new int[]{120,240}, recipeTags2, "comment2", DiffGrade.CHIEF_LIKE);

        this.count.set(this.recipeContainer, 0);
        Arrays.fill((Recipe[])this.recipeList.get(this.recipeContainer), null);
    }

    @Test
    public void getInstance()
    {
        Assert.assertEquals(this.recipeContainer, RecipeContainer.GetInstance());
    }

    @Test
    public void addRecipe() throws IllegalAccessException
    {
        Assert.assertEquals(0, (int)this.count.get(this.recipeContainer));

        this.recipeContainer.addRecipe(this.sampleRecipe);
        this.recipeContainer.addRecipe(this.sampleRecipe2);

        Assert.assertEquals(2, (int)this.count.get(this.recipeContainer));
        Assert.assertEquals(this.sampleRecipe, ((Recipe[])this.recipeList.get(this.recipeContainer))[0]);
        Assert.assertEquals(this.sampleRecipe2, ((Recipe[])this.recipeList.get(this.recipeContainer))[1]);
    }

    @Test
    public void eraseRecipe() throws IllegalAccessException
    {
        this.recipeContainer.addRecipe(this.sampleRecipe);
        Assert.assertEquals(1, (int)this.count.get(this.recipeContainer));

        this.recipeContainer.eraseRecipe(this.sampleRecipe);
        Assert.assertEquals(0, (int)this.count.get(this.recipeContainer));
    }

    @Test
    public void findRecipeByEmptyName() throws IllegalAccessException
    {
        Assert.assertEquals(0, (int)this.count.get(this.recipeContainer));
        this.recipeContainer.addRecipe(this.sampleRecipe);
        this.recipeContainer.addRecipe(this.sampleRecipe2);

        ArrayList<Recipe> results = this.recipeContainer.findRecipe("");

        Assert.assertTrue(results.contains(this.sampleRecipe));
        Assert.assertTrue(results.contains(this.sampleRecipe2));
    }

    @Test
    public void findRecipeByCorrectName() throws IllegalAccessException
    {
        Assert.assertEquals(0, (int)this.count.get(this.recipeContainer));
        this.recipeContainer.addRecipe(this.sampleRecipe);
        this.recipeContainer.addRecipe(this.sampleRecipe2);

        ArrayList<Recipe> results = this.recipeContainer.findRecipe("Macaroon");

        Assert.assertTrue(results.contains(this.sampleRecipe2));
    }

    @Test
    public void findRecipeByTags() throws IllegalAccessException
    {
        Assert.assertEquals(0, (int)this.count.get(this.recipeContainer));
        this.recipeContainer.addRecipe(this.sampleRecipe);
        this.recipeContainer.addRecipe(this.sampleRecipe2);
        ArrayList<DefaultTags> tags = new ArrayList<>();
        tags.add(DefaultTags.EUROPEAN);
        tags.add(DefaultTags.ITALIAN);

        ArrayList<Recipe> results = this.recipeContainer.findRecipe(tags);

        Assert.assertTrue(results.contains(this.sampleRecipe));
    }
}
