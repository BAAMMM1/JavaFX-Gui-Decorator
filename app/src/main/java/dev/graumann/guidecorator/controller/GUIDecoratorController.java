package dev.graumann.guidecorator.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIDecoratorController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane resizePane;

    @FXML
    private AnchorPane userContent;

    @FXML
    private AnchorPane titelbarPane;

    @FXML
    private Label lblStageTitel;

    double x, y;
    boolean stageIsMaximized = false;

    Boolean resizebottom = false;
    double dx;
    double dy;
    double xOffset;
    double yOffset;

    double tempX, tempY, tempPostionX, tempPostionY;


    @FXML
    private Button btnTitelbarFullscreen;

    @FXML
    private Button btnTitelbarMinimize;

    @FXML
    private Button btnTitelbarRestore;

    @FXML
    private Button btnTitelbarClose;

    @FXML
    private SVGPath svgTitelbarIcon;

    @FXML
    private SVGPath svgTitelbarFullscreen;

    @FXML
    private SVGPath svgTitelbarMinimize;

    @FXML
    private SVGPath svgTitelbarRestore;

    @FXML
    private SVGPath svgTitelbarClose;

    @FXML
    private SVGPath svgEndbarGit;

    @FXML
    private SVGPath svgEndbarGithub;

    @FXML
    private Button btnEndbarGithub;

    @FXML
    private Button btnEndbarGit;

    private Insets fullscreenPadding;

    private String profilUrl;

    private String repositoryUrl;

    private int anchorRightX;

    private int posY;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Window Move
        titelbarPane.setOnMousePressed(event -> {
            if (!event.isPrimaryButtonDown()) return;

            x = event.getSceneX();
            y = event.getSceneY();
        });


        titelbarPane.setOnMouseDragged(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            if (stageIsMaximized || stage.isFullScreen()) return; // TODO btnRestore ausführen

            if (!event.isPrimaryButtonDown()) return;

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });


        // Windows Resize
        // Abfragen der Größe
        root.setOnMousePressed(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            if (stageIsMaximized || stage.isFullScreen()) return;

            if (event.getX() > stage.getWidth() - 20 && event.getY() > stage.getHeight() - 20 ) {
                resizebottom = true;
                dx = stage.getWidth() - event.getX();
                dy = stage.getHeight() - event.getY();

            } else if (event.getX() < 0 + 20 && event.getY() > stage.getHeight() - 20) {
                resizebottom = true;
                dx = stage.getWidth() - event.getX();
                dy = stage.getHeight() - event.getY();

            } else if (event.getX() < 0 + 20 && event.getY() < 0 + 20) {
                resizebottom = true;
                dx = stage.getWidth() - event.getX();
                dy = stage.getHeight() - event.getY();

            } else if (event.getX() > stage.getWidth() - 20 && event.getY() < 0 + 20) {
                resizebottom = true;
                dx = stage.getWidth() - event.getX();
                dy = stage.getHeight() - event.getY();

            } else {
                resizebottom = false;
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }

        });

        // Setzen des Cursors
        root.setOnMouseMoved(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            if (stageIsMaximized || stage.isFullScreen()) return;

            if (event.getX() > stage.getWidth() - 20 && event.getY() > stage.getHeight() - 20) {
                ((Node) event.getSource()).getScene().setCursor(Cursor.NW_RESIZE);

            } else if (event.getX() < 0 + 20 && event.getY() > stage.getHeight() - 20) {
                ((Node) event.getSource()).getScene().setCursor(Cursor.NE_RESIZE);

            } else if (event.getX() < 0 + 20 && event.getY() < 0 + 20) {
                ((Node) event.getSource()).getScene().setCursor(Cursor.NW_RESIZE);

            } else if (event.getX() > stage.getWidth() - 20 && event.getY() < 0 + 20) {
                ((Node) event.getSource()).getScene().setCursor(Cursor.NE_RESIZE);


            } else {
                ((Node) event.getSource()).getScene().setCursor(Cursor.DEFAULT);

            }
        });

        // Fenstergröße anpassen
        root.setOnMouseDragged(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            if (stageIsMaximized || stage.isFullScreen()) return;

            if (resizebottom) {
                stage.setWidth(event.getX() + dx);
                stage.setHeight(event.getY() + dy);
            }

        });


        // Titelbar

        btnTitelbarFullscreen.setOnMouseClicked(event -> {
            if (!event.getButton().equals(MouseButton.PRIMARY)) return;

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            if (stage.isFullScreen()) {
                root.setPadding(this.fullscreenPadding);
                stage.setFullScreen(false);
                stageIsMaximized = false;
            } else {
                tempX = stage.getX();
                tempY = stage.getY();
                tempPostionX = stage.getWidth();
                tempPostionY = stage.getHeight();
                stageIsMaximized = true;
                this.fullscreenPadding = root.getPadding();
                root.setPadding(new Insets(0, 0, 0, 0));
                stage.setFullScreen(true);
            }
        });

        root.setOnKeyPressed(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            if (stage.isFullScreen() && event.getCode().equals(KeyCode.ESCAPE)) {
                root.setPadding(this.fullscreenPadding);
                stage.setFullScreen(false);
                stageIsMaximized = false;
            }


        });


        btnTitelbarMinimize.setOnMouseClicked(event -> {
            if (!event.getButton().equals(MouseButton.PRIMARY)) return;

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setIconified(true);
        });

        btnTitelbarRestore.setOnMouseClicked(event -> {
            if (!event.getButton().equals(MouseButton.PRIMARY)) return;

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            if (!stageIsMaximized) {
                tempX = stage.getX();
                tempY = stage.getY();
                tempPostionX = stage.getWidth();
                tempPostionY = stage.getHeight();
                stage.setX(bounds.getMinX() - 10);
                stage.setY(bounds.getMinY() - 10);
                stage.setWidth(bounds.getWidth() + 20);
                stage.setHeight(bounds.getHeight() + 20);
                stageIsMaximized = true;
            } else {
                stage.setX(tempX);
                stage.setY(tempY);
                stage.setWidth(tempPostionX);
                stage.setHeight(tempPostionY);
                stageIsMaximized = false;
            }
        });

        btnTitelbarClose.setOnMouseClicked(event -> {
            if (!event.getButton().equals(MouseButton.PRIMARY)) return;

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            System.exit(0);
        });


        titelbarPane.setOnMouseClicked(e -> {
            if (!e.getButton().equals(MouseButton.PRIMARY)) return;
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            if (stage.isFullScreen()) return;

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();


            if (e.getClickCount() == 2) {
                if (!stageIsMaximized) {
                    tempX = stage.getX();
                    tempY = stage.getY();
                    tempPostionX = stage.getWidth();
                    tempPostionY = stage.getHeight();
                    stage.setX(bounds.getMinX() - 10);
                    stage.setY(bounds.getMinY() - 10);
                    stage.setWidth(bounds.getWidth() + 20);
                    stage.setHeight(bounds.getHeight() + 20);
                    stageIsMaximized = true;
                } else {
                    stage.setX(tempX);
                    stage.setY(tempY);
                    stage.setWidth(tempPostionX);
                    stage.setHeight(tempPostionY);
                    stageIsMaximized = false;
                }
            }
        });

        this.btnEndbarGithub.setOnMouseClicked(event -> {
            try {
                Desktop.getDesktop().browse(new URI(profilUrl));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

        btnEndbarGit.setOnMouseClicked(event -> {
            try {
                Desktop.getDesktop().browse(new URI(repositoryUrl));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

    }

    public void addUserContent(Parent userContent) {
        this.userContent.getChildren().addAll(userContent);
    }

    public void setLblStageTitelText(String stageTitel) {
        this.lblStageTitel.setText(stageTitel);
    }

    public void setSVGTitelbarIconContent(String content) {
        this.svgTitelbarIcon.setContent(content);
    }

    public void setSVGTitelbarFullscreenContent(String content) {
        this.svgTitelbarFullscreen.setContent(content);
    }

    public void setSVGTitelbarMinimizeContent(String content) {
        this.svgTitelbarMinimize.setContent(content);
    }

    public void setSVGTitelbarRestoreContent(String content) {
        this.svgTitelbarRestore.setContent(content);
    }

    public void setSVGTitelbarCloseContent(String content) {
        this.svgTitelbarClose.setContent(content);
    }

    public void setSVGEndbarGitContent(String content) {
        this.svgEndbarGit.setContent(content);
    }

    public void setSVGEndbarGithubContent(String content) {
        this.svgEndbarGithub.setContent(content);
    }

    public void setProfilUrl(String profilUrl) {
        this.profilUrl = profilUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public void setBtnTitelbarFullscreenVisible(boolean value){
        this.btnTitelbarFullscreen.setVisible(value);
    }

    public void setBtnTitelbarFullscreenDisable(boolean value){
        this.btnTitelbarFullscreen.setDisable(value);
    }

}
