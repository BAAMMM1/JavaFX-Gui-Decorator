package dev.graumann.guidecorator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import dev.graumann.guidecorator.GUIDecorator;


public class Main extends Application {

    private static final String UNDECORATED_VIEW_PATH = "/fxml/UserContentSample.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(UNDECORATED_VIEW_PATH));
        Parent wrapper = fxmlLoader.load();

        Rectangle2D bounds = Screen.getScreens().get(0).getBounds();
        double width = bounds.getWidth() / 2.5;
        double height = bounds.getHeight() / 2.5;

        Scene scene = new Scene(wrapper);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Example");

        new GUIDecorator().decorate(primaryStage);

        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
