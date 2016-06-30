import GlobalData.GlobalVars;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    //wydaje mi sie że tu powinien być ten ogólno światowy kontener

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("newRecipe.fxml"));
        primaryStage.setTitle("42");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        final GlobalVars appGlobalVars = new GlobalVars();
        launch(args);
    }
}
