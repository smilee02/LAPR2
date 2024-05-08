package app.ui.gui;

import app.controller.App;
import app.domain.model.Employee;
import app.domain.shared.Constants;
import app.ui.console.RecordResultsUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClinicalChemistryTechnologistController implements Initializable {
    private static Employee employee;
    public Button btnRecordResults;
    private String pathShowClients = "/fxml/showClients.fxml";
    private String pathLogout = "/fxml/sample.fxml";
    private App app = Constants.APP;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblSoc;
    @FXML
    private Label lblEmail;
    @FXML
    private Button btnShowTestResult;
    @FXML
    private Label lblName;
    @FXML
    private Button btnLogout;

    public static void setEmployee(Employee employeeLog) {
        employee = employeeLog;
    }

    public void onActionLogout(ActionEvent actionEvent) {
        app.doLogout();
        Main.switchScene(actionEvent, pathLogout);
    }

    public void onActionShowTests(ActionEvent actionEvent) {
        Main.switchScene(actionEvent,pathShowClients);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblEmail.setText("Email : " + employee.getEmail());
        lblName.setText("Welcome " + employee.getName());
        lblPhone.setText("Phone : " + employee.getPhoneNumber());
        lblAddress.setText("Address : " + employee.getAddress());
        lblSoc.setText("SOC : " + employee.getStandardOcupationalCode());
    }

    public void onActionRecordResults(ActionEvent actionEvent) {
        Main.minimizeMaximizeWindow(actionEvent,true);
        new RecordResultsUI().run();
        Main.minimizeMaximizeWindow(actionEvent,false);
    }
}
