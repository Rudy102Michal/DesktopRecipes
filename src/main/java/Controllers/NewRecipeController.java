package Controllers;

import DataClasses.DefaultTags;
import DataClasses.Recipe;
import DataClasses.Unit;
import DataClasses.Ingredient;
import GlobalData.GlobalVars;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class NewRecipeController implements Initializable {

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

    private ObservableList<ObservableIngredient> observableRecipeIngredients;

    public TextArea recipeComment;

    public TableView<ObservableIngredient> ingredientsTable;
    public TableColumn<ObservableIngredient, String> tableColumnName;
    public TableColumn<ObservableIngredient, Double> tableColumnQuantity;
    public TableColumn<ObservableIngredient, String> tableColumnUnit;


    private Recipe tmpRecipe;
    private Ingredient tmpIngredient;

    public NewRecipeController() {
        tmpRecipe = new Recipe();
        this.checkBoxes = new ArrayList<CheckBox>(Arrays.asList(checkVegan, checkVegan, checkVegetarian, checkMediterrean, checkOriental, checkEuropean, checkItalian, checkFrench, checkEnglish, checkPolish, checkAlcohol));

    }

    public void initialize(URL location, ResourceBundle resources) {
        tableColumnName.setCellValueFactory(new PropertyValueFactory<ObservableIngredient, String>("ingredientName"));
        tableColumnQuantity.setCellValueFactory(new PropertyValueFactory<ObservableIngredient, Double>("ingredientQuantity"));
        tableColumnUnit.setCellValueFactory(new PropertyValueFactory<ObservableIngredient, String>("ingredientUnit"));
        observableRecipeIngredients = FXCollections.observableArrayList();
        ingredientsTable.setItems(observableRecipeIngredients);
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

        Ingredient in1 = new Ingredient("bekon", 2.5, Unit.KG);
        Ingredient in2 = new Ingredient("piwo", 3, Unit.AMOUNT);
        Ingredient in3 = new Ingredient("czosnek", 1, Unit.SPOON);

        tmpRecipe.addIngredient(in1);
        tmpRecipe.addIngredient(in2);
        tmpRecipe.addIngredient(in3);

        tmpRecipe.testPrint();
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
        try {
            FXMLLoader n1 = new FXMLLoader(getClass().getResource("../newIngredient.fxml"));
            Parent newWindow = n1.load();
            stage.setTitle("Dodaj nowy składnik");
            stage.setScene(new Scene(newWindow));
            stage.showAndWait();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        if(GlobalVars.dataAvailable) {
            tmpIngredient = GlobalVars.tempIngredient;
            GlobalVars.dataAvailable = false;
            System.out.print("test1\n");
            ingredientsTable.getItems().add(new ObservableIngredient(tmpIngredient));
            System.out.print("test2\n");
        }
    }

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
}








