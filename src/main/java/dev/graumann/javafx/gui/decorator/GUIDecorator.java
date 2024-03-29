package dev.graumann.javafx.gui.decorator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import dev.graumann.javafx.gui.decorator.controller.GUIDecoratorController;

import java.io.IOException;

/*
How to import:
- https://www.youtube.com/watch?v=HRfLgjppJ5Y&ab_channel=ITBeginner
- Project structure -> Module ->  + Import Module -> create Modul from existing source -> gradle wrapper wegklicken
- Dabei drauf achten, dass bei Project structure -> Modules -> Project
name -> Dependenscies reiter -> Projektname aufgeklappt ist und dann bei main und test "+" -> Modul dependencie
GUIDecorator hinzugefügt wird.
- Dann noch bei Project structure -> Modules -> GUIDecorator -> Source Reiter -> GUIDDecorator resources als resources deklariert
- Die fonts zum classpath hinzufügen, per Abfrage von Intellij
- beim UserContent im root pane AnchorPane.bottomAnchor="0.0" AnchorPane.leftAnchor="0.0" AnchorPane.rightAnchor="0.0" AnchorPane.topAnchor="0.0" setzen.
 */
public class GUIDecorator {

    private static final String WRAPPER_VIEW_PATH = "/fxml/GUIWrapper.fxml";

    private static final String SVG_ICON_TITELBAR_LOGO = "M11.99 18.54l-7.37-5.73L3 14.07l9 7 9-7-1.63-1.27zM12 16l7.36-5.73L21 9l-9-7-9 7 1.63 1.27L12 16zm0-11.47L17.74 9 12 13.47 6.26 9 12 4.53z";
    private static final String SVG_ICON_TITELBAR_FULLSCREEN = "M7 14H5v5h5v-2H7v-3zm-2-4h2V7h3V5H5v5zm12 7h-3v2h5v-5h-2v3zM14 5v2h3v3h2V5h-5z";
    private static final String SVG_ICON_TITELBAR_MINIMIZE = "M6 19h12v2H6v-2z";
    private static final String SVG_ICON_TITELBAR_RESTORE = "M19 12h-2v3h-3v2h5v-5zM7 9h3V7H5v5h2V9zm14-6H3c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h18c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm0 16.01H3V4.99h18v14.02z";
    private static final String SVG_ICON_TITELBAR_CLOSE = "M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12 19 6.41z";

    private static final String SVG_ICON_ENDBAR_GIT = "M2.6,10.59L8.38,4.8L10.07,6.5C9.83,7.35 10.22,8.28 11,8.73V14.27C10.4,14.61 10,15.26 10,16A2,2 0 0,0 12,18A2,2 0 0,0 14,16C14,15.26 13.6,14.61 13,14.27V9.41L15.07,11.5C15,11.65 15,11.82 15,12A2,2 0 0,0 17,14A2,2 0 0,0 19,12A2,2 0 0,0 17,10C16.82,10 16.65,10 16.5,10.07L13.93,7.5C14.19,6.57 13.71,5.55 12.78,5.16C12.35,5 11.9,4.96 11.5,5.07L9.8,3.38L10.59,2.6C11.37,1.81 12.63,1.81 13.41,2.6L21.4,10.59C22.19,11.37 22.19,12.63 21.4,13.41L13.41,21.4C12.63,22.19 11.37,22.19 10.59,21.4L2.6,13.41C1.81,12.63 1.81,11.37 2.6,10.59Z";
    private static final String SVG_ICON_ENDBAR_GITHUB = "M12,2A10,10 0 0,0 2,12C2,16.42 4.87,20.17 8.84,21.5C9.34,21.58 9.5,21.27 9.5,21C9.5,20.77 9.5,20.14 9.5,19.31C6.73,19.91 6.14,17.97 6.14,17.97C5.68,16.81 5.03,16.5 5.03,16.5C4.12,15.88 5.1,15.9 5.1,15.9C6.1,15.97 6.63,16.93 6.63,16.93C7.5,18.45 8.97,18 9.54,17.76C9.63,17.11 9.89,16.67 10.17,16.42C7.95,16.17 5.62,15.31 5.62,11.5C5.62,10.39 6,9.5 6.65,8.79C6.55,8.54 6.2,7.5 6.75,6.15C6.75,6.15 7.59,5.88 9.5,7.17C10.29,6.95 11.15,6.84 12,6.84C12.85,6.84 13.71,6.95 14.5,7.17C16.41,5.88 17.25,6.15 17.25,6.15C17.8,7.5 17.45,8.54 17.35,8.79C18,9.5 18.38,10.39 18.38,11.5C18.38,15.32 16.04,16.16 13.81,16.41C14.17,16.72 14.5,17.33 14.5,18.26C14.5,19.6 14.5,20.68 14.5,21C14.5,21.27 14.66,21.59 15.17,21.5C19.14,20.16 22,16.42 22,12A10,10 0 0,0 12,2Z";

    private static final String URL_PROFIL = "https://github.com/BAAMMM1";
    private static final String URL_REPOSITORY = "https://github.com/BAAMMM1";


    private Parent wrapper;
    private GUIDecoratorController gUIDecoratorController;

    public GUIDecorator() {

    }

    public void decorate(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);

        Parent toWrapp = stage.getScene().getRoot();

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(WRAPPER_VIEW_PATH));
        wrapper = fxmlLoader.load();


        stage.getScene().setRoot(wrapper);
        stage.getScene().setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        
        gUIDecoratorController = fxmlLoader.getController();
        gUIDecoratorController.addUserContent(toWrapp);

        gUIDecoratorController.setLblStageTitelText(stage.getTitle());

        this.addStyleSheet("/css/titlebar-default.css");

        this.setTitelbarSVGIconContent(SVG_ICON_TITELBAR_LOGO);
        this.setTitelbarSVGFullscreenContent(SVG_ICON_TITELBAR_FULLSCREEN);
        this.setTitelbarSVGMinimizeContent(SVG_ICON_TITELBAR_MINIMIZE);
        this.setTitelbarSVGRestoreContent(SVG_ICON_TITELBAR_RESTORE);
        this.setTitelbarSVGCloseContent(SVG_ICON_TITELBAR_CLOSE);

        this.setEndbarSVGGitContent(SVG_ICON_ENDBAR_GIT);
        this.setEndbarSVGGithubContent(SVG_ICON_ENDBAR_GITHUB);

        // TODO nicht default und boolean visible
        this.setProfilURL(URL_PROFIL);
        this.setRepositoryURL(URL_REPOSITORY);


        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);


    }

    public void addStyleSheet(String path){
        this.wrapper.getStylesheets().clear();
        this.wrapper.getStylesheets().add(getClass().getResource(path).toExternalForm());
    }

    public void setTitelbarSVGIconContent(String content){
        this.gUIDecoratorController.setSVGTitelbarIconContent(content);
    }

    public void setTitelbarSVGFullscreenContent(String content){
        this.gUIDecoratorController.setSVGTitelbarFullscreenContent(content);
    }

    public void setTitelbarSVGMinimizeContent(String content){
        this.gUIDecoratorController.setSVGTitelbarMinimizeContent(content);
    }

    public void setTitelbarSVGRestoreContent(String content){
        this.gUIDecoratorController.setSVGTitelbarRestoreContent(content);
    }

    public void setTitelbarSVGCloseContent(String content){
        this.gUIDecoratorController.setSVGTitelbarCloseContent(content);
    }

    public void setEndbarSVGGitContent(String content){
        this.gUIDecoratorController.setSVGEndbarGitContent(content);
    }

    public void setEndbarSVGGithubContent(String content){
        this.gUIDecoratorController.setSVGEndbarGithubContent(content);
    }

    public void setProfilURL(String url){
        this.gUIDecoratorController.setProfilUrl(url);
    }

    public void setRepositoryURL(String url){
        this.gUIDecoratorController.setRepositoryUrl(url);
    }

    public void setBtnTitelbarFullscreenVisible(boolean value){
        this.gUIDecoratorController.setBtnTitelbarFullscreenVisible(value);
    }

    public void setBtnTitelbarFullscreenDisable(boolean value){
        this.gUIDecoratorController.setBtnTitelbarFullscreenDisable(value);
    }

}
