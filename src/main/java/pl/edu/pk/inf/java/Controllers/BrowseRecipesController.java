package pl.edu.pk.inf.java.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.pk.inf.java.DataClasses.DefaultTags;
import pl.edu.pk.inf.java.DataClasses.Ingredient;
import pl.edu.pk.inf.java.DataClasses.Recipe;
import pl.edu.pk.inf.java.DialogBoxes.DecisionBox;
import pl.edu.pk.inf.java.GlobalData.GlobalVars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private Recipe modifiedRecipe;

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
            actionOnPropertyChanged();
        });

        recipeName.textProperty().addListener( (v, oldValue, newValue) -> {
            actionOnPropertyChanged();
        });

        buttRecipesBrowsing.selectedProperty().addListener( (v, oldValue, newValue) -> {
            if(!oldValue && newValue)
                actionOnPropertyChanged();
        });
    }

    public ObservableList<ObservableRecipe> getSelectedRecipe() {
        ObservableList<ObservableRecipe> recipesSelected;
        recipesSelected = recipesTable.getSelectionModel().getSelectedItems();

        return recipesSelected;
    }

    public void displaySelectedRecipe() {

        for(ObservableRecipe rcp : getSelectedRecipe())
        {
            displayRecipeToUser(rcp.getObservedRecipe());
        }
    }

    public void modifySelectedRecipe() {

        if(!getSelectedRecipe().isEmpty()) {
            for(ObservableRecipe rcp : getSelectedRecipe()) {

                try {
                    Stage stage = new Stage();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifyRecipe.fxml"));

                    Parent window = loader.load();
                    ModifyRecipeController controller = loader.getController();
                    controller.setRecipeToModify(rcp.getObservedRecipe());
                    controller.setObjectsValues();
                    controller.setParentController(this);

                    stage.setScene(new Scene(window));
                    stage.showAndWait();

                    GlobalVars.recipeContainer.eraseRecipe(rcp.getObservedRecipe());
                    GlobalVars.recipeContainer.addRecipe(modifiedRecipe);

                    actionOnPropertyChanged();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setModifiedRecipe(Recipe modifiedRecipe) {
        this.modifiedRecipe = modifiedRecipe;
    }

    public void removeSelectedRecipe() {

        if(!getSelectedRecipe().isEmpty()) {
            DecisionBox mess = new DecisionBox("Czy na pewno chcesz usunąć ten przepis z bazy?", "Usuwanie przepisu");
            mess.displayMessage();
            if (mess.isDecision()) {
                for (ObservableRecipe rcp : getSelectedRecipe()) {
                    GlobalVars.recipeContainer.eraseRecipe(rcp.getObservedRecipe());
                }
                actionOnPropertyChanged();
            }
        }
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
