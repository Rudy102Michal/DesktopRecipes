package Controllers;

import DataClasses.DefaultTags;
import DataClasses.Unit;
import DataClasses.ingredient;
import DataClasses.recipe;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

/**
 * Created by Michał on 2016-06-15.
 */
public class Controller implements Initializable {

    public Button buttAddIngredientToRecipe;
    public Button buttAddNewRecipe;

    public TextField recipeName;
    public TextField prepTimeFrom;
    public TextField prepTimeTo;

    private List<CheckBox> checkBoxes;
    public CheckBox checkVegan;
    public CheckBox checkVegetarian;
    public CheckBox checkMediterrean;
    public CheckBox checkOriental;
    public CheckBox checkEuropean;
    public CheckBox checkItalian;
    public CheckBox checkFrench;
    public CheckBox checkEnglish;
    public CheckBox checkPolish;
    public CheckBox checkAlcohol;

    public TextArea recipeComment;

    public TableView<String> ingredientsTable;

    private recipe tmpRecipe;

    public Controller() {
        tmpRecipe = new recipe();
        this.checkBoxes = new ArrayList<CheckBox>(Arrays.asList(checkVegan, checkVegan, checkVegetarian, checkMediterrean, checkOriental, checkEuropean, checkItalian, checkFrench, checkEnglish, checkPolish, checkAlcohol));
    }

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("My nigga");
    }

    public void addNewRecipe(){
        tmpRecipe.recipeName = recipeName.getText();
        tmpRecipe.setRecipeComment(recipeComment.getText());
        tmpRecipe.setPrepTime(Integer.parseInt(prepTimeFrom.getText()), Integer.parseInt(prepTimeTo.getText()));

        if(checkVegan.isSelected())
            tmpRecipe.recipeTags.add(DefaultTags.VEGAN);
        if(checkVegetarian.isSelected())
            tmpRecipe.recipeTags.add(DefaultTags.VEGETARIAN);
        if(checkMediterrean.isSelected())
            tmpRecipe.recipeTags.add(DefaultTags.MEDITERREAN);
        if(checkOriental.isSelected())
            tmpRecipe.recipeTags.add(DefaultTags.ORIENTAL);
        if(checkEuropean.isSelected())
            tmpRecipe.recipeTags.add(DefaultTags.EUROPEAN);
        if(checkItalian.isSelected())
            tmpRecipe.recipeTags.add(DefaultTags.ITALIAN);
        if(checkFrench.isSelected())
            tmpRecipe.recipeTags.add(DefaultTags.FRENCH);
        if(checkEnglish.isSelected())
            tmpRecipe.recipeTags.add(DefaultTags.ENGLISH);
        if(checkPolish.isSelected())
            tmpRecipe.recipeTags.add(DefaultTags.POLISH);
        if(checkAlcohol.isSelected())
            tmpRecipe.recipeTags.add(DefaultTags.ALCOHOL);

        ingredient in1 = new ingredient("bekon", 2.5, Unit.KG);
        ingredient in2 = new ingredient("piwo", 3, Unit.AMOUNT);
        ingredient in3 = new ingredient("czosnek", 1, Unit.SPOON);

        tmpRecipe.addIngredient(in1);
        tmpRecipe.addIngredient(in2);
        tmpRecipe.addIngredient(in3);

        tmpRecipe.testPrint();
    }

    public void getIngredientFromUser(){

    }
}
