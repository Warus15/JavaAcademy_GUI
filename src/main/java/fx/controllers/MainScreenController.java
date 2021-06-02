package fx.controllers;

import fx.UserData;
import fx.UserDataHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import task.Record;
import task.Solution;
import task.TableViewRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainScreenController implements Initializable {
    @FXML
    private Button nextResultButton;

    @FXML
    private Label totalLabel;

    @FXML
    private TableView<TableViewRecord> resultsTableView;

    @FXML
    private TableColumn lettersInGroupColumn, wordLengthColumn, frequencyColumn, exactValueColumn;

    private Solution solution;

    private Scanner scanner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeResultsTableView();

        //Getting data from Singleton instance
        UserDataHolder holder = UserDataHolder.getInstance();
        UserData userData = holder.getUserData();

        File dataFile = userData.getDataFile();
        String key = userData.getKey();

        try {
            scanner = new Scanner(dataFile);
            solution = new Solution(key);

            //This method call is to show first result right after opening this scene
            setNextResult();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //This method sets CellValueFactory, so that TableView can store and display data
    //Also sets size of columns
    private void initializeResultsTableView(){
        lettersInGroupColumn.setCellValueFactory(new PropertyValueFactory<>("lettersInGroup"));
        lettersInGroupColumn.setMinWidth(125);
        lettersInGroupColumn.setPrefWidth(125);
        lettersInGroupColumn.setMaxWidth(125);

        wordLengthColumn.setCellValueFactory(new PropertyValueFactory<>("wordLength"));
        wordLengthColumn.setMinWidth(125);
        wordLengthColumn.setPrefWidth(125);
        wordLengthColumn.setMaxWidth(125);

        frequencyColumn.setCellValueFactory(new PropertyValueFactory<>("frequency"));
        frequencyColumn.setMinWidth(125);
        frequencyColumn.setPrefWidth(125);
        frequencyColumn.setMaxWidth(125);

        exactValueColumn.setCellValueFactory(new PropertyValueFactory<>("exactValue"));
        exactValueColumn.setMinWidth(125);
        exactValueColumn.setPrefWidth(125);
        exactValueColumn.setMaxWidth(125);
    }

    @FXML
    public void setNextResult(){
        //Clearing TableView, if contains records
        if(!resultsTableView.getItems().isEmpty())
            resultsTableView.getItems().removeAll(resultsTableView.getItems());

        if(scanner.hasNextLine()){
            String line = scanner.nextLine();

            //If the line in file is empty, there's no reason to check it
            if(line.isEmpty()){
                setNextResult();
            } else {
                solution.solve(line);

                ArrayList<Record> results = solution.getResults();
                listResults(results);

                totalLabel.setText(solution.getTotalFrequencyText());

                solution.clear();
            }
        } else {
            totalLabel.setText("End of data");
        }
    }

    //This method creates TableView records
    private void listResults(ArrayList<Record> results){
        for(Record r : results){
            TableViewRecord tvRecord = new TableViewRecord(
                    r.getChars(),
                    r.getWordLength(),
                    r.getFrequency(),
                    "(" + r.getOccurrences() + "/" + r.getTotalOccurrences() + ")"
            );

            resultsTableView.getItems().add(tvRecord);
        }
    }
}
