# JavaFX Gui Decorator
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![JavaFX](https://img.shields.io/badge/javafx-%23FF0000.svg?style=for-the-badge&logo=javafx&logoColor=white) ![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white)

## Description
Used for decorating my JavaFX projects.

![](https://github.com/BAAMMM1/JavaFX-Gui-Decorator/blob/9e1551850ef61178f55a8c508c673cc1c5a07392/images/teaser.png)

## Requirements
- Java JDK v21 installed on your machine.

## How to run?
You can run it as an example, but it's intended to be used as a library.

Linux:
```
./gradlew run
```

Windows:
```
.\gradlew.bat run
```

## How to create a jar file?
Linux:
```
./gradlew jar
```

Windows:
```
.\gradlew.bat jar
```

## How do you use it?
Create the JAR file and import the GUIDecorator into your app as a library using Gradle.

```
dependencies {
    implementation files('libs/javafx-gui-decorator-0.2.1-SNAPSHOT.jar')
}
```

Now you can decorate the primaryStage as shown below.

```
public class App extends Application {

    private static final String MAIN_VIEW_PATH = "/dev/graumann/slideannotationenhancer/fxml/main.fxml";
    private static final String STYLE_CSS_PATH = "/dev/graumann/slideannotationenhancer/css/style.css";

    private static final String STAGE_TITLE = "Slide Annotation Enhancer";        
    private static final String REPOSITORY_URL = "https://github.com/BAAMMM1/PDF-Slide-Annotation-Enhancer";
    private static final String SVG_ICON_TITELBAR_LOGO = "M11.99 18.54l-7.37-5.73L3 14.07l9 7 9-7-1.63-1.27zM12 16l7.36-5.73L21 9l-9-7-9 7 1.63 1.27L12 16zm0-11.47L17.74 9 12 13.47 6.26 9 12 4.53z";

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(MAIN_VIEW_PATH));
        Parent userContent = fxmlLoader.load();
        userContent.getStylesheets().add(getClass().getResource(STYLE_CSS_PATH).toExternalForm());

        double width = Screen.getScreens().get(0).getBounds().getWidth() / 2.5;
        double height = Screen.getScreens().get(0).getBounds().getHeight() / 2.5;

        Scene scene = new Scene(userContent, width, height);

        primaryStage.setTitle(STAGE_TITLE);
        primaryStage.setScene(scene);
        
        GUIDecorator decorator = new GUIDecorator();
        decorator.decorate(primaryStage);
        decorator.setTitelbarSVGIconContent(SVG_ICON_TITELBAR_LOGO);
        decorator.setRepositoryURL(REPOSITORY_URL);
        decorator.setBtnTitelbarFullscreenDisable(true);
        decorator.setBtnTitelbarFullscreenVisible(false);        

        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
```
