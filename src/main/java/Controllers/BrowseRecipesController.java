package Controllers;

import DataClasses.DefaultTags;
import DataClasses.Recipe;
import GlobalData.GlobalVars;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Michał on 2016-07-02.
 */
public class BrowseRecipesController extends MainController {

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

    public Button buttDisplayRecipe;
    public Button buttModifyRecipe;
    public Button buttRemoveRecipe;

    private ObservableList<ObservableRecipe> observableRecipeList;

    public TableView<ObservableRecipe> recipesTable;
    public TableColumn<ObservableRecipe, String> tableColumnName;
    public TableColumn<ObservableRecipe, String> tableColumnDifficulty;
    public TableColumn<ObservableRecipe, Integer> tableColumnMinTime;
    public TableColumn<ObservableRecipe, Integer> tableColumnMaxTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        System.out.println("Test");
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("recipeName"));
        tableColumnDifficulty.setCellValueFactory(new PropertyValueFactory<>("recipeDifficulty"));
        tableColumnMinTime.setCellValueFactory(new PropertyValueFactory<>("recipePrepTimeFrom"));
        tableColumnMaxTime.setCellValueFactory(new PropertyValueFactory<>("recipePrepTimeTo"));
        observableRecipeList = FXCollections.observableArrayList();
        recipesTable.setItems(observableRecipeList);

        setListenersForObjects();


    }

    private void setListenersForObjects() {

        checkVegan.selectedProperty().addListener( (v, oldValue, newValue) -> {
            actionOnPropertyChanged();
        });

        checkVegetarian.selectedProperty().addListener( (v, oldValue, newValue) -> {
            actionOnPropertyChanged();
        });

        checkOriental.selectedProperty().addListener( (v, oldValue, newValue) -> {
            actionOnPropertyChanged();
        });

        checkEuropean.selectedProperty().addListener( (v, oldValue, newValue) -> {
            actionOnPropertyChanged();
        });

        checkItalian.selectedProperty().addListener( (v, oldValue, newValue) -> {
            actionOnPropertyChanged();
        });

        checkFrench.selectedProperty().addListener( (v, oldValue, newValue) -> {
            actionOnPropertyChanged();
        });

        checkEnglish.selectedProperty().addListener( (v, oldValue, newValue) -> {
            actionOnPropertyChanged();
        });

        checkPolish.selectedProperty().addListener( (v, oldValue, newValue) -> {
            actionOnPropertyChanged();
        });

        checkAlcohol.selectedProperty().addListener( (v, oldValue, newValue) -> {
            System.out.println(v.toString() + " " + newValue.toString());
            actionOnPropertyChanged();
        });

        recipeName.textProperty().addListener( (v, oldValue, newValue) -> {
            actionOnPropertyChanged();
        });
    }

    public void displaySelectedRecipe() {

    }

    public void modifySelectedRecipe() {

    }

    public void removeSelectedRecipe() {

    }

    private void loadToTableRecipeList(ArrayList<Recipe> recipeArrayList) {
        recipesTable.getItems().clear();

        for(Recipe rcp : recipeArrayList)
        {
            recipesTable.getItems().add(new ObservableRecipe(rcp));
        }
    }

    private ArrayList<DefaultTags> getChosenTags() {

        ArrayList<DefaultTags> retList = new ArrayList<>();

        if(checkVegan.isSelected())
            retList.add(DefaultTags.VEGAN);
        if(checkVegetarian.isSelected())
            retList.add(DefaultTags.VEGETARIAN);
        if(checkMediterrean.isSelected())
            retList.add(DefaultTags.MEDITERREAN);
        if(checkOriental.isSelected())
            retList.add(DefaultTags.ORIENTAL);
        if(checkEuropean.isSelected())
            retList.add(DefaultTags.EUROPEAN);
        if(checkItalian.isSelected())
            retList.add(DefaultTags.ITALIAN);
        if(checkFrench.isSelected())
            retList.add(DefaultTags.FRENCH);
        if(checkEnglish.isSelected())
            retList.add(DefaultTags.ENGLISH);
        if(checkPolish.isSelected())
            retList.add(DefaultTags.POLISH);
        if(checkAlcohol.isSelected())
            retList.add(DefaultTags.ALCOHOL);

        return retList;
    }

    private void actionOnPropertyChanged() {

        System.out.println("lel");
        loadToTableRecipeList(GlobalVars.recipeContainer.findRecipe(getChosenTags(), recipeName.getText()));

    }


    public class ObservableRecipe {

        private String recipeName;
        private String recipeDifficulty;
        private Integer recipePrepTimeFrom;
        private Integer recipePrepTimeTo;

        private Recipe observedRecipe;

        public ObservableRecipe(Recipe observedRecipe) {
            this.observedRecipe = observedRecipe;

            this.recipeName = observedRecipe.recipeName;
            this.recipeDifficulty = observedRecipe.getRecipeDifficulty().toString();
            this.recipePrepTimeFrom = observedRecipe.getPrepTime(0);
            this.recipePrepTimeTo = observedRecipe.getPrepTime(1);
        }

        public ObservableRecipe(String recipeName, String recipeDifficulty, Integer recipePrepTimeFrom, Integer recipePrepTimeTo) {
            this.recipeName = recipeName;
            this.recipeDifficulty = recipeDifficulty;
            this.recipePrepTimeFrom = recipePrepTimeFrom;
            this.recipePrepTimeTo = recipePrepTimeTo;
            this.observedRecipe = new Recipe();
        }

        public ObservableRecipe() {
            this.observedRecipe = new Recipe();

            this.recipeName = "";
            this.recipeDifficulty = "";
            this.recipePrepTimeFrom = -1;
            this.recipePrepTimeTo = -1;
        }

        public String getRecipeName() {
            return recipeName;
        }

        public void setRecipeName(String recipeName) {
            this.recipeName = recipeName;
        }

        public String getRecipeDifficulty() {
            return recipeDifficulty;
        }

        public void setRecipeDifficulty(String recipeDifficulty) {
            this.recipeDifficulty = recipeDifficulty;
        }

        public Integer getRecipePrepTimeFrom() {
            return recipePrepTimeFrom;
        }

        public void setRecipePrepTimeFrom(Integer recipePrepTimeFrom) {
            this.recipePrepTimeFrom = recipePrepTimeFrom;
        }

        public Integer getRecipePrepTimeTo() {
            return recipePrepTimeTo;
        }

        public void setRecipePrepTimeTo(Integer recipePrepTimeTo) {
            this.recipePrepTimeTo = recipePrepTimeTo;
        }

        public Recipe getObservedRecipe() {
            return observedRecipe;
        }
    }
}
