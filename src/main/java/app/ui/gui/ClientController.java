package app.ui.gui;

import app.controller.App;
import app.domain.model.Client;
import app.domain.shared.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    private final String showTestsPath = "/fxml/showTestsClients.fxml";
    private final String loginpath = "/fxml/sample.fxml";
    private final String EDITPATH = "/fxml/editClient.fxml";
    private static Client client;
    private App app = App.getInstance();
    @FXML
    private Label lblEmail;
    @FXML
    private Button lblLogout;
    @FXML
    private Button lblEdit;
    @FXML
    private Button lblShow;
    @FXML
    private Label lblName;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblCcn;
    @FXML
    private Label lblTin;
    @FXML
    private Label lblNhs;
    @FXML
    private Label lblBirth;

    public ClientController() {
    }

    public void onActionShow(ActionEvent actionEvent) {
        ShowTestsOfClientController.setClient(client);
        Main.switchScene(actionEvent, showTestsPath);
    }

    public void onActionEdit(ActionEvent actionEvent) {
        ClientEditController.setClient(client);
        Main.switchScene(actionEvent, EDITPATH);
    }

    public void onActionLogout(ActionEvent actionEvent) {
        app.doLogout();
        Main.switchScene(actionEvent, loginpath);
    }

    public static void setClient(Client clientLog) {
        client = clientLog;
    }

    public String ccnConverter(double ccn) {
        String cCN = String.valueOf(ccn);
        for (int i = cCN.length(); i < 18; i++) {
            cCN = 0 + "" + cCN;
        }
        return cCN;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] citizenCardNumber = ccnConverter(client.getCitizenCardNumber()).split("\\.0");
        lblEmail.setText("Email : " + client.getEmail());
        lblName.setText("Welcome " + client.getName());
        lblPhone.setText("Phone : " + String.format("%.0f", client.getPhoneNumber()));
        lblCcn.setText("CCN : " + citizenCardNumber[0]);
        lblTin.setText("TIN : " + String.format("%.0f", client.getTin()));
        lblNhs.setText("NHS : " + String.format("%.0f", client.getNhsNumber()));
        lblBirth.setText("Birthdate : " + client.getBirthDate());
    }
}
