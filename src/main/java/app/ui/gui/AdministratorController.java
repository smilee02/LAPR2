package app.ui.gui;

import app.controller.App;
import app.domain.model.Employee;
import app.domain.shared.Constants;
import app.ui.console.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdministratorController implements Initializable {
    private static Employee employee;
    private String pathLogout = "/fxml/sample.fxml";
    private String pathSendReport = "/fxml/sendNHSReport.fxml";
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
    @FXML
    private Button btnSendNHSReport;
    @FXML
    private Button btnRegisterEmployee;
    @FXML
    private Button btnRegisterParameterCategory;
    @FXML
    private Button btnRegisterLab;
    @FXML
    private Button btnRegisterTypeOfTest;
    @FXML
    private Button btnRegisterParameter;

    public static void setEmployee(Employee employeeLog) {
        employee = employeeLog;
    }

    public void onActionLogout(ActionEvent actionEvent) {
        app.doLogout();
        Main.switchScene(actionEvent, pathLogout);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblEmail.setText("Email : " + employee.getEmail());
        lblName.setText("Welcome " + employee.getName());
        lblPhone.setText("Phone : " + employee.getPhoneNumber());
        lblAddress.setText("Address : " + employee.getAddress());
        lblSoc.setText("SOC : " + employee.getStandardOcupationalCode());
    }

    public void onActionSendReport(ActionEvent actionEvent) {
        Main.switchScene(actionEvent,pathSendReport);
    }

    public void onActionRegisterEmployee(ActionEvent actionEvent) {
        Main.minimizeMaximizeWindow(actionEvent,true);
        new EmployeeUI().run();
        Main.minimizeMaximizeWindow(actionEvent,false);
    }

    public void onActionRegisterParameterCategory(ActionEvent actionEvent) {
        Main.minimizeMaximizeWindow(actionEvent,true);
        new ParameterCategoryUI().run();
        Main.minimizeMaximizeWindow(actionEvent,false);
    }

    public void onActionRegisterLaboratory(ActionEvent actionEvent) {
        Main.minimizeMaximizeWindow(actionEvent,true);
        new RegisterClinicalAnalysisLaboratoryUI().run();
        Main.minimizeMaximizeWindow(actionEvent,false);
    }

    public void onActionRegisterTypeOfTest(ActionEvent actionEvent) {
        Main.minimizeMaximizeWindow(actionEvent,true);
        new CreateTypeTestUI().run();
        Main.minimizeMaximizeWindow(actionEvent,false);
    }

    public void onActionRegisterParameter(ActionEvent actionEvent) {
        Main.minimizeMaximizeWindow(actionEvent,true);
        new ParameterUI().run();
        Main.minimizeMaximizeWindow(actionEvent,false);
    }
}
