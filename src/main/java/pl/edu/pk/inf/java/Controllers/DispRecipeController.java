package pl.edu.pk.inf.java.Controllers;

import pl.edu.pk.inf.java.DataClasses.Ingredient;
import pl.edu.pk.inf.java.DataClasses.Recipe;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * Created by Michał on 2016-07-02.
 */
public class DispRecipeController implements Initializable {

    public Recipe recipeToShow;

    public Label recipeNameLabel;
    public Label recipePrepTimeLabel;
    public Label recipeDiffGradeLabel;
    public Label recipeIngredientsLabel;
    public Label recipeCommentLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(recipeNameLabel.getText());
        System.out.println(recipePrepTimeLabel.getText());
        System.out.println(recipeDiffGradeLabel.getText());
        System.out.println(recipeIngredientsLabel.getText());
        System.out.println(recipeCommentLabel.getText());


    }


    public void setRecipeToShow(Recipe recipeToShow) {
        this.recipeToShow = recipeToShow;


    }

    public void setLabels() {
        recipeToShow.testPrint();


        recipeNameLabel.setText(recipeToShow.recipeName);
        recipePrepTimeLabel.setText("Czas przygotowania: " + recipeToShow.getPrepTime(0) + "-" + recipeToShow.getPrepTime(1) + " min.");
        recipeDiffGradeLabel.setText("Poziom trudności: " + recipeToShow.getRecipeDifficulty().toString());


        Iterator<Ingredient> it = recipeToShow.getRecipeIngredients().iterator();
        String tmpStr = "Składniki:\n";
        while(it.hasNext())
        {
            Ingredient tmpIngr = it.next();
            tmpStr += "- " + tmpIngr.getIngredientName() + ", " + Double.toString(tmpIngr.getIngredientQuantity()) + " " + tmpIngr.getIngredientUnit().toString() + "\n";
        }

        recipeIngredientsLabel.setText(tmpStr);
        recipeCommentLabel.setText("Opis:\n" + recipeToShow.getRecipeComment());
    }
}
