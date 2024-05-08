package app.ui.gui;

import app.controller.App;
import app.domain.model.Employee;
import app.domain.shared.Constants;
import app.ui.console.ClientUI;
import app.ui.console.ListClientUI;
import app.ui.console.RegisterTestUI;
import app.ui.console.SampleRecorderUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicalLabTechnician implements Initializable {
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
    private Button btnRecordSample;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblEmail.setText("Email : " + employee.getEmail());
        lblName.setText("Welcome " + employee.getName());
        lblPhone.setText("Phone : " + employee.getPhoneNumber());
        lblAddress.setText("Address : " + employee.getAddress());
        lblSoc.setText("SOC : " + employee.getStandardOcupationalCode());
    }

    public void onActionRecordSample(ActionEvent actionEvent) {
        Main.minimizeMaximizeWindow(actionEvent,true);
        new SampleRecorderUI().run();
        Main.minimizeMaximizeWindow(actionEvent,false);
    }
}
