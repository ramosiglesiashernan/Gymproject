package es.her.ui.views;

import es.her.ui.Router;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenuView {

    private final VBox root;

    public MainMenuView(Router router) {
        Label title = new Label("Menú principal");

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> router.goToLogin());

        root = new VBox(12, title, logoutButton);
        root.setPadding(new Insets(16));
    }

    public Parent getRoot() {
        return root;
    }
}
