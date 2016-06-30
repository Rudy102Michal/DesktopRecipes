package Controllers;

import DataClasses.Unit;
import DataClasses.Ingredient;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import GlobalData.GlobalVars;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Michał on 2016-06-18.
 */
public class NewIngredientController implements Initializable {

    public TextField ingredientName;
    public TextField ingredientQuantity;
    public ChoiceBox<String> ingredientUnit;
    public Button buttAddIngredient;

    public void initialize(URL location, ResourceBundle resources) {
        ingredientUnit.getItems().addAll(
                Unit.GRAM.toString(),
                Unit.DG.toString(),
                Unit.KG.toString(),
                Unit.LITER.toString(),
                Unit.ML.toString(),
                Unit.SPOON.toString(),
                Unit.GLASS.toString(),
                Unit.PACK.toString(),
                Unit.AMOUNT.toString());

    }

    public void sendNewIngredient()
    {
        Unit tempUnit = Unit.UNKN;
        if(ingredientUnit.getValue() == Unit.GRAM.toString())
            tempUnit = Unit.GRAM;
        if(ingredientUnit.getValue() == Unit.DG.toString())
            tempUnit = Unit.DG;
        if(ingredientUnit.getValue() == Unit.KG.toString())
            tempUnit = Unit.KG;
        if(ingredientUnit.getValue() == Unit.LITER.toString())
            tempUnit = Unit.LITER;
        if(ingredientUnit.getValue() == Unit.ML.toString())
            tempUnit = Unit.ML;
        if(ingredientUnit.getValue() == Unit.SPOON.toString())
            tempUnit = Unit.SPOON;
        if(ingredientUnit.getValue() == Unit.GLASS.toString())
            tempUnit = Unit.GLASS;
        if(ingredientUnit.getValue() == Unit.PACK.toString())
            tempUnit = Unit.PACK;
        if(ingredientUnit.getValue() == Unit.AMOUNT.toString())
            tempUnit = Unit.AMOUNT;

        GlobalVars.tempIngredient = new Ingredient(ingredientName.getText(), Double.parseDouble(ingredientQuantity.getText()), tempUnit);
        GlobalVars.dataAvailable = true;

        Stage stage = (Stage) buttAddIngredient.getScene().getWindow();
        stage.close();
    }

    public void meth()
    {
        System.out.print("fuck u");
    }
}
