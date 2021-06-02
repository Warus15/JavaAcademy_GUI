package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage window;

    public static void run(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/start-screen.fxml"));

        Scene scene = new Scene(root, 700, 500);

        window.setTitle("Java Academy Technical Task");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

    public static Stage getWindow() {
        return window;
    }
}
