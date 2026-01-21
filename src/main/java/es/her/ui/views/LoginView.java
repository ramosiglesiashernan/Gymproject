package es.her.ui.views;

import es.her.ui.Router;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginView {

    private final VBox root;

    public LoginView(Router router) {

        Label title = new Label("Login");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Label messageLabel = new Label();

        Button loginButton = new Button("Entrar");
        loginButton.setOnAction(e -> {
            // Por ahora: navegación directa. En el Paso 3 haremos autenticación real con BD y hash.
            if (emailField.getText().isBlank() || passwordField.getText().isBlank()) {
                messageLabel.setText("Rellena email y password.");
                return;
            }
            messageLabel.setText("");
            router.goToMainMenu();
        });

        root = new VBox(10, title, emailField, passwordField, loginButton, messageLabel);
        root.setPadding(new Insets(16));
    }

    public Parent getRoot() {
        return root;
    }
}
