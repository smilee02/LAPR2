package app.ui.gui;

import app.controller.App;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.shared.Constants;
import app.domain.store.ClientStore;
import app.domain.store.EmployeeStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    public Label lblLoginError;
    private Main main;
    private Client client;
    private final App app = App.getInstance();
    private String role;
    private Employee employee;
    private final String pathtoUIAdmin = "/fxml/administrator.fxml";
    private final String pathtoUIRecep = "/fxml/receptionist.fxml";
    private final String pathtoUICCT = "/fxml/clinicalChemistryTechnologistUI.fxml";
    private final String pathtoUIMlt = "/fxml/medicalLabTechnician.fxml";
    private final String pathtoUIClient = "/fxml/client.fxml";
    private final String pathtoUILb = "/fxml/laboratoryCoordinator.fxml";
    private final String pathtoSpecialDoc = "/fxml/specialDoc.fxml";

    public MainController() {
        /**
         * This constructor doesnt need any parameters
         */
    }

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;

    public boolean validateLogin(String user, String password) {
        boolean control = true;
        try {
            control = app.doLogin(user, password);
        } catch (Exception e) {
            return false;
        }
        return control;
    }


    public void onActionbtnLogin(ActionEvent actionEvent) throws IOException {

        boolean login = validateLogin(txtUsername.getText(), txtPassword.getText());
        if (login) {
            lblLoginError.setText("");
            redirectToUI(actionEvent);
        } else {
            lblLoginError.setText("ERROR: User doesn't exist");
        }
    }

    public void redirectToUI(ActionEvent actionEvent) {
        ImportTestInterfaceController.setAuthFacade(app.getCompany().getAuthFacade());
        if (app.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_CLIENT)) {
            getEmailClient(txtUsername.getText());
            ClientController.setClient(client);
            Main.switchScene(actionEvent, pathtoUIClient);
        } else {
            getEmailEmployee(txtUsername.getText());
            if (app.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST)) {
                ClinicalChemistryTechnologistController.setEmployee(employee);
                Main.switchScene(actionEvent, pathtoUICCT);
            } else if (app.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_ADMIN)) {
                AdministratorController.setEmployee(employee);
                Main.switchScene(actionEvent,pathtoUIAdmin);
            } else if (app.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_LABORATORY_COORDINATOR)) {
                LaboratoryCoordinatorController.setLabCoordinator(employee);
                Main.switchScene(actionEvent, pathtoUILb);
            } else if (app.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_RECEPTIONIST)){
                ReceptionistController.setEmployee(employee);
                Main.switchScene(actionEvent,pathtoUIRecep);
            } else if (app.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_MEDICAL_LAB_TECHNICIAN)){
                MedicalLabTechnician.setEmployee(employee);
                Main.switchScene(actionEvent,pathtoUIMlt);
            } else if (app.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_SPECIALIST_DOCTOR)){
                SpecialDocController.setEmployee(employee);
                Main.switchScene(actionEvent,pathtoSpecialDoc);
            }

        }
    }

    public void getEmailEmployee(String user) {
        for (Employee x : EmployeeStore.getEmployees()) {
            if (x.getEmail().equals(user)) employee = x;
        }
    }

    public void getEmailClient(String user) {
        for (Client x : ClientStore.getClients()) {
            if (x.getEmail().equals(user)) client = x;
        }
    }

    public void setClient(Client clientLog) {
        this.client = clientLog;
    }

    public Client getClient() {
        return client;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
