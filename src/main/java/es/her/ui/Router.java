package es.her.ui;

import es.her.ui.views.LoginView;
import es.her.ui.views.MainMenuView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Router {

    private final Stage stage;

    public Router(Stage stage) {
        this.stage = stage;
    }

    public void goToLogin() {
        LoginView view = new LoginView(this);
        Scene scene = new Scene(view.getRoot(), 420, 260);
        stage.setScene(scene);
    }

    public void goToMainMenu() {
        MainMenuView view = new MainMenuView(this);
        Scene scene = new Scene(view.getRoot(), 640, 420);
        stage.setScene(scene);
    }
}
