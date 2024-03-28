import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// TODO Author-Leiste

/*
Building Jar
https://stackoverflow.com/questions/1082580/how-to-build-jars-from-intellij-properly+

Artifacts -> GUIDecorator -> Output Layer -> + -> Directory -> Ressources

Build | Build Artifact | Build

jar ist dann in out -> artifacts -> GUIDecorator_jar

Import in ein anderes Projekt

libs ordner erstellen im root und jar reinkopieren

build.gradle

repositories {
    flatDir {
        dirs 'lib'
    }
}

dependencies {
    implementation name: 'GUIDecorator'
}

Reload all gradle projects

Beim Project dann in der root.fxml
AnchorPane.bottomAnchor="0.0" AnchorPane.leftAnchor="0.0" AnchorPane.rightAnchor="0.0" AnchorPane.topAnchor="0.0" TODO Im decorator setzen
 */
public class Main extends Application {

    private static final String UNDECORATED_VIEW_PATH = "fxml/UserContentSample.fxml";

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
