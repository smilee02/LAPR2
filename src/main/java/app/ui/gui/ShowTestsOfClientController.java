package app.ui.gui;

import app.domain.model.Client;
import app.mappers.dto.ClientDTO;
import app.mappers.dto.RecordResultDTO;
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
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowTestsOfClientController implements Initializable {
    private static Client client;
    private final String pathGoBack = "/fxml/client.fxml";
    private final String pathToShowResults = "/fxml/showResultsForOnlyOneTest.fxml";
    private final app.controller.ClientController clientController = new app.controller.ClientController();
    @FXML
    private Button btnBack;
    @FXML
    private TableView<TestDetailsResultsDTO> tblTests;
    @FXML
    private TableColumn<TestDetailsResultsDTO,String> tblViewTests;

    public void onActionGoBack(ActionEvent actionEvent) {
        Main.switchScene(actionEvent,pathGoBack);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblViewTests.setCellValueFactory(new PropertyValueFactory<>("test"));
        tblTests.getItems().clear();
        List<TestDetailsResultsDTO> testList = clientController.getTestsOfClient(client);
        ObservableList<TestDetailsResultsDTO> testDetailsResultsDTOS = FXCollections.observableList(testList);
        tblTests.setItems(testDetailsResultsDTOS);

    }

    public static void setClient(Client client1) {
        client = client1;
    }

    public void showResultsForThisTest(MouseEvent mouseEvent) {
        ShowResultsForAClientTestController.setClient(client);
        Main.switchScene(mouseEvent,pathToShowResults);
    }
}
