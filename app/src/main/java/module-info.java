module dev.graumann.guidecorator {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;

    exports dev.graumann.guidecorator;
    exports dev.graumann.guidecorator.controller;

    opens dev.graumann.guidecorator;
    opens dev.graumann.guidecorator.controller;
}