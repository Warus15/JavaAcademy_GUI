package fx.controllers;

import fx.App;
import fx.UserData;
import fx.UserDataHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class StartScreenController {
    @FXML
    private Button selectFileButton, startButton;

    @FXML
    private TextField keywordInput;

    @FXML
    private Label filePathLabel, errorLabel;

    private File dataFile;

    @FXML
    private void selectFile(){
        dataFile = new FileChooser().showOpenDialog(App.getWindow());

        if(dataFile != null)
            filePathLabel.setText(dataFile.getAbsolutePath());
        else
            filePathLabel.setText("<here you'll see path to selected file>");
    }

    @FXML
    private void start(){
        if(keywordInput.getText().isEmpty() || dataFile == null){
            errorLabel.setText("Enter keyword and select a file");
        } else {
            try {
                //Create UserData object from user data, to store it in Singleton and access it in main screen
                UserData userData = new UserData(keywordInput.getText(), dataFile);

                UserDataHolder holder = UserDataHolder.getInstance();
                holder.setUserData(userData);

                Parent root = FXMLLoader.load(getClass().getResource("/fxml/main-screen.fxml"));

                Scene scene = new Scene(root, 700, 500);

                Stage window = App.getWindow();
                window.setScene(scene);
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
