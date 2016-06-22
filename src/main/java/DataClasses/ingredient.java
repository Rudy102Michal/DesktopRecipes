package DataClasses;

/**
 * Created by Michał on 2016-06-15.
 */
public class ingredient {
    public String ingredientName;
    private Unit ingredientUnit;
    private double ingredientQuantity;

    public ingredient(String ingredientName, double ingredientQuantity, Unit ingredientUnit) {
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientUnit = ingredientUnit;
    }

    public ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
        this.ingredientQuantity = -1;
        this.ingredientUnit = Unit.UNKN;
    }

    public Unit getIngredientUnit() {
        return ingredientUnit;
    }

    public double getIngredientQuantity() {
        return ingredientQuantity;
    }
}
