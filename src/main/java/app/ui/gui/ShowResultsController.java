package app.ui.gui;

import app.controller.CheckingTestsController;
import app.domain.model.Client;
import app.mappers.dto.TestDetailsResultsDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowResultsController implements Initializable {
    private static Client client;
    private CheckingTestsController checkingTestsController = new CheckingTestsController();
    @FXML
    private TableView<TestDetailsResultsDTO> tblResults;
    @FXML
    private TableColumn<TestDetailsResultsDTO,String > tblViewResults;
    @FXML
    private Button btnClose;
    private String pathToBack = "/fxml/showClients.fxml";

    public void onActionGoBack(ActionEvent actionEvent) {
        Main.switchScene(actionEvent,pathToBack);
    }//Returns to the before scene

    public static void setClient(Client client) {
        ShowResultsController.client = client;
    }//Sets the client to get the results for


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblViewResults.setCellValueFactory(new PropertyValueFactory<>("string")); //Sets the value for the TableColumn object being a RecordResult Object
        tblResults.getItems().clear();//Clear the Table for it's items
        List<TestDetailsResultsDTO> list = checkingTestsController.getResultsOfClient(client);//Retrieves the results of a client in a DTO form
        ObservableList<TestDetailsResultsDTO> testDetailsResultsDTOS = FXCollections.observableList(list);//Transform the results of a client list into a observable list so it can be shown in the table
        tblResults.setItems(testDetailsResultsDTOS);//Sets the items in the table to display
    }
}
