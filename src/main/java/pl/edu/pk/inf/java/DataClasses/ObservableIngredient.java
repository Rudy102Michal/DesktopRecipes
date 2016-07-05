package pl.edu.pk.inf.java.DataClasses;

/**
 * Created by Michał on 2016-07-05.
 */
public class ObservableIngredient {
    private String ingredientName;
    private double ingredientQuantity;
    private String ingredientUnit;

    public ObservableIngredient(String ingredientName, double ingredientQuantity, String ingredientUnit) {
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientUnit = ingredientUnit;
    }

    public ObservableIngredient() {
        this.ingredientName = "";
        this.ingredientQuantity = 0;
        this.ingredientUnit = "";
    }

    public ObservableIngredient(Ingredient ingredient) {
        this.ingredientName = ingredient.getIngredientName();
        this.ingredientQuantity = ingredient.getIngredientQuantity();
        this.ingredientUnit = ingredient.getIngredientUnit().toString();
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(double ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public String getIngredientUnit() {
        return ingredientUnit;
    }

    public void setIngredientUnit(String ingredientUnit) {
        this.ingredientUnit = ingredientUnit;
    }
}
