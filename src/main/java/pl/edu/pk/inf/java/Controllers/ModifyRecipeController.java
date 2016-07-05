package pl.edu.pk.inf.java.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.edu.pk.inf.java.DataClasses.*;
import pl.edu.pk.inf.java.DialogBoxes.AlertBox;
import pl.edu.pk.inf.java.GlobalData.GlobalVars;
import pl.edu.pk.inf.java.LocalExceptions.ExceptionNoIngredient;
import pl.edu.pk.inf.java.LocalExceptions.ExceptionNoIntegerInput;
import pl.edu.pk.inf.java.LocalExceptions.ExceptionNoTextInput;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michał on 2016-07-05.
 */
public class ModifyRecipeController implements Initializable {

    private BrowseRecipesController parentController;

    public Button buttAddIngredientToRecipe;
    public Button deleteButt;
    public Button buttModifyRecipe;

    public void setRecipeToModify(Recipe recipeToModify) {
        this.recipeToModify = recipeToModify;
    }

    private ObservableList<ObservableIngredient> observableRecipeIngredients;
    private Recipe recipeToModify;

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

    public TextField recipeName;
    public TextField prepTimeFrom;
    public TextField prepTimeTo;
    public TextArea recipeComment;

    public ToggleGroup diffToggleGroup;
    public RadioButton radioButtMedium;

    public TableView<ObservableIngredient> ingredientsTable;
    public TableColumn<ObservableIngredient, String> tableColumnName;
    public TableColumn<ObservableIngredient, Double> tableColumnQuantity;
    public TableColumn<ObservableIngredient, String> tableColumnUnit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableColumnName.setCellValueFactory(new PropertyValueFactory<ObservableIngredient, String>("ingredientName"));
        tableColumnQuantity.setCellValueFactory(new PropertyValueFactory<ObservableIngredient, Double>("ingredientQuantity"));
        tableColumnUnit.setCellValueFactory(new PropertyValueFactory<ObservableIngredient, String>("ingredientUnit"));
        observableRecipeIngredients = FXCollections.observableArrayList();
        ingredientsTable.setItems(observableRecipeIngredients);

    }

    public Recipe getRecipeToModify() {
        return recipeToModify;
    }

    public void setParentController(BrowseRecipesController parentController) {
        this.parentController = parentController;
    }

    public void setObjectsValues() {
        recipeName.setText(recipeToModify.recipeName);
        prepTimeFrom.setText(Integer.toString(recipeToModify.getPrepTime(0)));
        prepTimeTo.setText(Integer.toString(recipeToModify.getPrepTime(1)));

        recipeComment.setText(recipeToModify.getRecipeComment());

        for(Toggle tgg : diffToggleGroup.getToggles())
        {
            if(tgg.toString().equals(recipeToModify.getRecipeDifficulty().toString()))
            {
                diffToggleGroup.selectToggle(tgg);
                break;
            }
        }

        setChosenTags();

        ingredientsTable.getItems().clear();
        for(Ingredient ing : recipeToModify.getRecipeIngredients())
        {
            ingredientsTable.getItems().add(new ObservableIngredient(ing));
        }
    }

    private void setChosenTags() {

        if(recipeToModify.recipeTags.contains(DefaultTags.VEGAN))
            checkVegan.setSelected(true);
        if(recipeToModify.recipeTags.contains(DefaultTags.VEGETARIAN))
            checkVegetarian.setSelected(true);
        if(recipeToModify.recipeTags.contains(DefaultTags.MEDITERREAN))
            checkMediterrean.setSelected(true);
        if(recipeToModify.recipeTags.contains(DefaultTags.ORIENTAL))
            checkOriental.setSelected(true);
        if(recipeToModify.recipeTags.contains(DefaultTags.EUROPEAN))
            checkEuropean.setSelected(true);
        if(recipeToModify.recipeTags.contains(DefaultTags.ITALIAN))
            checkItalian.setSelected(true);
        if(recipeToModify.recipeTags.contains(DefaultTags.FRENCH))
            checkFrench.setSelected(true);
        if(recipeToModify.recipeTags.contains(DefaultTags.ENGLISH))
            checkEnglish.setSelected(true);
        if(recipeToModify.recipeTags.contains(DefaultTags.POLISH))
            checkPolish.setSelected(true);
        if(recipeToModify.recipeTags.contains(DefaultTags.ALCOHOL))
            checkAlcohol.setSelected(true);
    }

    public void getIngredientFromUser(){
        Stage stage = new Stage();
        try {
            FXMLLoader n1 = new FXMLLoader(getClass().getResource("/newIngredient.fxml"));
            Parent newWindow = n1.load();
            NewIngredientController tmpController = n1.getController();
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

        if(GlobalVars.dataAvailable) {
            Ingredient tmpIngredient = GlobalVars.tempIngredient;
            GlobalVars.dataAvailable = false;
            ingredientsTable.getItems().add(new ObservableIngredient(tmpIngredient));
        }
    }

    public void modifyThisRecipe(){

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

                //  if(tmp)
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

        parentController.setModifiedRecipe(tmpRecipe);

        Stage stage = (Stage) buttModifyRecipe.getScene().getWindow();
        stage.close();
    }

    public void deleteIngredient()
    {

        ObservableList<ObservableIngredient> ingredientSelected, allIngredients;
        allIngredients = ingredientsTable.getItems();
        ingredientSelected = ingredientsTable.getSelectionModel().getSelectedItems();

        ingredientSelected.forEach(allIngredients::remove);
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
}
