package group2JP2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage movieStage;
    public static Integer width = 1250;
    public static Integer height = 800;
    @Override
    public void start(Stage primaryStage) throws Exception {
        movieStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.setTitle("HOME");
        primaryStage.setScene(new Scene(root,width,height));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
