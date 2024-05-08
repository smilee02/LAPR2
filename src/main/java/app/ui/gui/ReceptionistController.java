package app.ui.gui;

import app.controller.App;
import app.domain.model.Employee;
import app.domain.shared.Constants;
import app.ui.console.ClientUI;
import app.ui.console.ListClientUI;
import app.ui.console.RegisterTestUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ReceptionistController implements Initializable {
    private static Employee employee;
    private App app = Constants.APP;
    private String pathToLogout = "/fxml/sample.fxml";
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblSoc;
    @FXML
    private Label lblEmail;
    @FXML
    private Button btnListClients;
    @FXML
    private Button btnRegisterANewClient;
    @FXML
    private Button btnRegisterATest;
    @FXML
    private Button btnLogout;
    @FXML
    private Label lblName;

    public static void setEmployee(Employee employeeLog) {
        employee = employeeLog;
    }

    public void onActionLogout(ActionEvent actionEvent) {
        app.doLogout();
        Main.switchScene(actionEvent, pathToLogout);
    }

    public void onActionRegisterTest(ActionEvent actionEvent) {
        Main.minimizeMaximizeWindow(actionEvent,true);
        new RegisterTestUI().run();
        Main.minimizeMaximizeWindow(actionEvent,false);

    }

    public void onActionRegisterAClient(ActionEvent actionEvent) {
        Main.minimizeMaximizeWindow(actionEvent,true);
        new ClientUI().run();
        Main.minimizeMaximizeWindow(actionEvent,false);
    }

    public void onActionListClients(ActionEvent actionEvent) {
        Main.minimizeMaximizeWindow(actionEvent,true);
        new ListClientUI().run();
        Main.minimizeMaximizeWindow(actionEvent,false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblEmail.setText("Email : " + employee.getEmail());
        lblName.setText("Welcome " + employee.getName());
        lblPhone.setText("Phone : " + employee.getPhoneNumber());
        lblAddress.setText("Address : " + employee.getAddress());
        lblSoc.setText("SOC : " + employee.getStandardOcupationalCode());
    }
}
