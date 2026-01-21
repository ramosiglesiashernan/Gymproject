package es.her;

import es.her.ui.Router;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        es.her.infrastructure.db.DatabaseInit.init();

        Router router = new Router(stage);
        router.goToLogin();

        stage.setTitle("Gym Manager");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
