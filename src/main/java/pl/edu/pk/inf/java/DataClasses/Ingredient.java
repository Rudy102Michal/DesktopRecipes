package pl.edu.pk.inf.java.DataClasses;

/**
 * Created by Michał on 2016-06-15.
 */
public class Ingredient {
    public String ingredientName;
    public Unit ingredientUnit;
    public double ingredientQuantity;

    public Ingredient(String ingredientName, double ingredientQuantity, Unit ingredientUnit) {
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientUnit = ingredientUnit;
    }

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
        this.ingredientQuantity = -1;
        this.ingredientUnit = Unit.UNKN;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Unit getIngredientUnit() {
        return ingredientUnit;
    }

    public void setIngredientUnit(Unit ingredientUnit) {
        this.ingredientUnit = ingredientUnit;
    }

    public double getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(double ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }
}
