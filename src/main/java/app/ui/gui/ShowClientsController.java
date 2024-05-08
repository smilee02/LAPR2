package app.ui.gui;

import app.controller.CheckingTestsController;
import app.domain.model.Client;
import app.domain.store.ClientStore;
import app.mappers.ClientMapper;
import app.mappers.dto.ClientDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShowClientsController implements Initializable {
    private final CheckingTestsController checkingTestsController = new CheckingTestsController();
    public ComboBox<String> sortingAlgorithm;
    public Button btnSort;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private String pathToBack = "/fxml/clinicalChemistryTechnologistUI.fxml";
    private String pathToResults = "/fxml/showResults.fxml";
    private final ClientMapper clientMapper = new ClientMapper();
    @FXML
    private Button btnBack;
    @FXML
    private TableView<ClientDTO> tblClients;
    @FXML
    private TableColumn<ClientDTO, String> tblViewClients;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sortingAlgorithm.getItems().clear();
        List<String> algorithms = new ArrayList<>();
        algorithms.add("Name");
        algorithms.add("TIN");
        sortingAlgorithm.setItems(FXCollections.observableList(algorithms));
        sortingAlgorithm.getSelectionModel().selectFirst();
        tblViewClients.setCellValueFactory(new PropertyValueFactory<>("string")); //Sets the value for the TableColumn object being a Client Object
        tblClients.getItems().clear(); //Clear the Table for it's items
        List<Client> clientList = checkingTestsController.getOrderedClients(false);//Retrieves the ordered client list
        List<ClientDTO> clientDTOList = clientMapper.toDTO(clientList);//Returns a DTO of the ordered client list
        ObservableList<ClientDTO> list = FXCollections.observableList(clientDTOList);//Transform the ordered client list into a observable list so it can be shown in the table
        tblClients.setItems(list);//Sets the items in the table to display

    }

    public void checkTestDetailsResults(MouseEvent mouseEvent) {
        ClientDTO object = tblClients.getSelectionModel().getSelectedItem();//Gets the selected object
        Client client = object.getClient();//Gets the client from the object
        ShowResultsController.setClient(client);//Sets the client in the ShowResultsController
        Main.switchScene(mouseEvent, pathToResults);//Switches scene
    }


    public void onActionSortClients(ActionEvent actionEvent) {
        String selected = sortingAlgorithm.getSelectionModel().getSelectedItem();
        if (selected.equals("Name")) {
            List<Client> clientList = checkingTestsController.getOrderedClients(false);//Retrieves the ordered client list
            List<ClientDTO> clientDTOList = clientMapper.toDTO(clientList);//Returns a DTO of the ordered client list
            ObservableList<ClientDTO> list = FXCollections.observableList(clientDTOList);//Transform the ordered client list into a observable list so it can be shown in the table
            tblClients.getItems().clear();
            tblClients.setItems(list);//Sets the items in the table to display
        } else if (selected.equals("TIN")) {
            List<Client> clientList = checkingTestsController.getOrderedClients(true);//Retrieves the ordered client list
            List<ClientDTO> clientDTOList = clientMapper.toDTO(clientList);//Returns a DTO of the ordered client list
            ObservableList<ClientDTO> list = FXCollections.observableList(clientDTOList);//Transform the ordered client list into a observable list so it can be shown in the table
            tblClients.getItems().clear();
            tblClients.setItems(list);//Sets the items in the table to display
        }
    }

    public void btnBack(MouseEvent mouseEvent) {
        Main.switchScene(mouseEvent, "/fxml/clinicalChemistryTechnologistUI.fxml");
    }
}

