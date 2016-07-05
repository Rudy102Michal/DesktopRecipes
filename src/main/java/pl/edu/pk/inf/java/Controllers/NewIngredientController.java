package pl.edu.pk.inf.java.Controllers;

import pl.edu.pk.inf.java.DataClasses.Unit;
import pl.edu.pk.inf.java.DataClasses.Ingredient;
import pl.edu.pk.inf.java.DialogBoxes.AlertBox;
import pl.edu.pk.inf.java.DialogBoxes.DecisionBox;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import pl.edu.pk.inf.java.GlobalData.GlobalVars;
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
        Unit tempUnit = Unit.UNKN;                                  //change to switch/case
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

        if(!ingredientName.getText().isEmpty() && !ingredientQuantity.getText().isEmpty()) {
            try {
                GlobalVars.tempIngredient = new Ingredient(ingredientName.getText(), Double.parseDouble(ingredientQuantity.getText()), tempUnit);
            }
            catch(NumberFormatException e)
            {
                AlertBox mess = new AlertBox("Pole \"Ilość\" musi być wartością całkowitą \nlub zmiennoprzecinkową", "Niepoprawny input");
                mess.displayMessage();
                ingredientQuantity.clear();
                return;
            }
            GlobalVars.dataAvailable = true;
            Stage stage = (Stage) buttAddIngredient.getScene().getWindow();
            stage.close();
        }
        else
        {
            AlertBox mess = new AlertBox("Musisz podać nazwe składnika i jego ilość", "Niepoprawny input");
            mess.displayMessage();
        }
    }

    public void roughExit()
    {
        Stage stage = (Stage) buttAddIngredient.getScene().getWindow();
        if(!ingredientName.getText().isEmpty() || !ingredientQuantity.getText().isEmpty())
        {
            DecisionBox mess = new DecisionBox("Nie skończono wprowadzać nowego składnika.\nCzy na pewno chcesz wyjść?", "");
            mess.displayMessage();
            if(mess.isDecision())
                stage.close();

        }
        else
        {
            stage.close();
        }
    }

    public void meth()
    {
        System.out.print("fuck u");
    }
}
