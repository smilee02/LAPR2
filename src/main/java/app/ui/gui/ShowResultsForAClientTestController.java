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

public class ShowResultsForAClientTestController implements Initializable {
    private final String pathGoBack = "/fxml/showTestsClients.fxml";
    private final CheckingTestsController checkingTestsController = new CheckingTestsController();
    private static Client client;
    @FXML
    private Button btnClose;
    @FXML
    private TableView<TestDetailsResultsDTO> tblResults;
    @FXML
    private TableColumn<TestDetailsResultsDTO,String> tblViewResults;

    public static void setClient(Client cliente) {
        client = cliente;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblViewResults.setCellValueFactory(new PropertyValueFactory<>("resultsForATest"));
        tblResults.getItems().clear();
        List<TestDetailsResultsDTO> list = checkingTestsController.getResultsOfClient(client);
        ObservableList<TestDetailsResultsDTO> testDetailsResultsDTOS = FXCollections.observableList(list);
        tblResults.setItems(testDetailsResultsDTOS);
    }

    public void onActionGoBack(ActionEvent actionEvent) {
        Main.switchScene(actionEvent,pathGoBack);
    }
}
