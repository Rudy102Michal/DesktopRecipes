package pl.edu.pk.inf.java.Controllers;

import pl.edu.pk.inf.java.DataClasses.*;
import pl.edu.pk.inf.java.DialogBoxes.AlertBox;
import pl.edu.pk.inf.java.GlobalData.GlobalVars;

import pl.edu.pk.inf.java.LocalExceptions.ExceptionNoIngredient;
import pl.edu.pk.inf.java.LocalExceptions.ExceptionNoIntegerInput;
import pl.edu.pk.inf.java.LocalExceptions.ExceptionNoTextInput;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

/**
 * Created by Michał on 2016-06-15.
 */
public class NewRecipeController extends MainController {

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

    public ToggleGroup diffToggleGroup;
    public RadioButton radioButtMedium;

    private ObservableList<ObservableIngredient> observableRecipeIngredients;

    public TextArea recipeComment;

    public TableView<ObservableIngredient> ingredientsTable;
    public TableColumn<ObservableIngredient, String> tableColumnName;
    public TableColumn<ObservableIngredient, Double> tableColumnQuantity;
    public TableColumn<ObservableIngredient, String> tableColumnUnit;

    private Ingredient receivedIngredient;

    public void initialize(URL location, ResourceBundle resources) {

        super.initialize(location, resources);

        tableColumnName.setCellValueFactory(new PropertyValueFactory<ObservableIngredient, String>("ingredientName"));
        tableColumnQuantity.setCellValueFactory(new PropertyValueFactory<ObservableIngredient, Double>("ingredientQuantity"));
        tableColumnUnit.setCellValueFactory(new PropertyValueFactory<ObservableIngredient, String>("ingredientUnit"));
        observableRecipeIngredients = FXCollections.observableArrayList();
        ingredientsTable.setItems(observableRecipeIngredients);
        System.out.println("My nigga");

    }

    public void addNewRecipe(){

        Recipe tmpRecipe = new Recipe();

        try {
            tmpRecipe.recipeName = recipeName.getText();
            if(tmpRecipe.recipeName.equals(""))
                throw new ExceptionNoTextInput();
        }
        catch(ExceptionNoTextInput e)
        {
            AlertBox mess = new AlertBox("Nie podano nazwy przepisu", "Brak nazwy");
            mess.displayMessage();
            return;
        }

        try {
            tmpRecipe.setRecipeComment(recipeComment.getText());
            if(recipeComment.getText().equals(""))
                throw new ExceptionNoTextInput();
        }
        catch(ExceptionNoTextInput e)
        {
            AlertBox mess = new AlertBox("Nie podano opisu do przepisu", "Brak opisu");
            mess.displayMessage();
            return;
        }

        try {
            if(prepTimeFrom.getText().isEmpty() || prepTimeTo.getText().isEmpty())
                throw new ExceptionNoIntegerInput();
            else
            {
                try {
                    tmpRecipe.setPrepTime(Integer.parseInt(prepTimeFrom.getText()), Integer.parseInt(prepTimeTo.getText()));
                }
                catch(NumberFormatException e)
                {
                    AlertBox mess = new AlertBox("Podane wartości dla czasu przygotowania nie są wartościami całkowitymi", "Błędny czas przygotowania");
                    mess.displayMessage();
                    return;
                }

                if(tmpRecipe.getPrepTime(0) <= 0 || tmpRecipe.getPrepTime(1) <= 0 || tmpRecipe.getPrepTime(0) >= tmpRecipe.getPrepTime(1))
                {
                    AlertBox mess = new AlertBox("Czas przygotowania musi być większy od zera\noraz czas 'do' musi być większy niż 'od'", "Błędny czas przygotowania" );
                    mess.displayMessage();
                    return;
                }
            }
        }
        catch(ExceptionNoIntegerInput e)
        {
            AlertBox mess = new AlertBox("Nie podano czasu przygotowania", "Brak czasu przygotowania");
            mess.displayMessage();
            return;
        }

        RadioButton tmpRadioButt = (RadioButton) diffToggleGroup.getSelectedToggle();
        for(DiffGrade dg : DiffGrade.values())
        {
            if(tmpRadioButt.getText().equals(dg.toString()))
            {
                tmpRecipe.setRecipeDifficulty(dg);
                break;
            }
        }

        try {
            if(observableRecipeIngredients.isEmpty())
                throw new ExceptionNoIngredient();
        }
        catch(ExceptionNoIngredient e)
        {
            AlertBox mess = new AlertBox("Do przepisu nie dodano żadnego składniku", "Brak składników");
            mess.displayMessage();
            return;
        }

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

        for(ObservableIngredient ing : observableRecipeIngredients)
        {
            tmpRecipe.addIngredient(new Ingredient(ing.getIngredientName(), ing.getIngredientQuantity(), convertUnit(ing.getIngredientUnit())));
        }

        clearObjects();
        tmpRecipe.testPrint();          //container function that takes recipe as argument here
        GlobalVars.recipeContainer.addRecipe(tmpRecipe);
        //displayRecipeToUser(tmpRecipe);          //container function that takes recipe as argument here
    }

    private Unit convertUnit(String strUnit)
    {
        for(Unit u : Unit.values())
        {
            if(strUnit.equals(u.toString()))
                return u;
        }

        return Unit.UNKN;
    }

    private void clearObjects()
    {
        recipeName.clear();
        recipeComment.clear();
        prepTimeFrom.clear();
        prepTimeTo.clear();
        observableRecipeIngredients.clear();

        diffToggleGroup.selectToggle(radioButtMedium);

        checkEuropean.setSelected(false);
        checkEnglish.setSelected(false);
        checkFrench.setSelected(false);
        checkAlcohol.setSelected(false);
        checkItalian.setSelected(false);
        checkMediterrean.setSelected(false);
        checkOriental.setSelected(false);
        checkPolish.setSelected(false);
        checkVegan.setSelected(false);
        checkVegetarian.setSelected(false);
    }

    public void deleteIngredient()
    {

        ObservableList<ObservableIngredient> ingredientSelected, allIngredients;
        allIngredients = ingredientsTable.getItems();
        ingredientSelected = ingredientsTable.getSelectionModel().getSelectedItems();

        ingredientSelected.forEach(allIngredients::remove);
    }

    public void getIngredientFromUser(){
        Stage stage = new Stage();
        NewIngredientController tmpController;
        try {
            FXMLLoader n1 = new FXMLLoader(getClass().getResource("/newIngredient.fxml"));
            Parent newWindow = n1.load();
            tmpController = n1.getController();
            tmpController.setParentController(this);
            stage.setTitle("Dodaj nowy składnik");

            stage.setOnCloseRequest(e -> {
                e.consume();
                tmpController.roughExit();
            });
            stage.setScene(new Scene(newWindow));
            stage.showAndWait();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        if(receivedIngredient != null) {
            ingredientsTable.getItems().add(new ObservableIngredient(receivedIngredient));
            receivedIngredient = null;
        }
    }

    public void setReceivedIngredient(Ingredient receivedIngredient) {
        this.receivedIngredient = receivedIngredient;
    }
}








