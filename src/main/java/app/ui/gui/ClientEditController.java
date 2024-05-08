package app.ui.gui;

import app.domain.model.Client;
import app.domain.shared.ImportedTestConnectorToInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientEditController implements Initializable {
    private static Client client;
    private final String BACKPATH="/fxml/client.fxml";
    @FXML
    private TextField txtName;
    @FXML
    private Button lblLogout;
    @FXML
    private Button lblSave;
    @FXML
    private TextField txtNhs;
    @FXML
    private TextField txtTin;
    @FXML
    private TextField txtCcn;
    @FXML
    private TextField txtPhone;

    public static void setClient(Client clientLog) {
        client = clientLog;
    }

    public void onActionEdit(ActionEvent actionEvent) {
        boolean flag= true;
        if(ImportedTestConnectorToInterface.validateName(txtName.getText())){
            client.setName(txtName.getText());
        }
        else{
            error("Invalid Name (Must be letters only)");
            flag=false;
        }
        if(ImportedTestConnectorToInterface.checkNumber(txtCcn.getText(),16)){
            client.setCitizenCardNumber(Double.parseDouble(txtCcn.getText()));
        }
        else{
            error("Invalid CCN Number (Must be 16 length digit)");
            flag=false;
        }
        if(ImportedTestConnectorToInterface.checkNumber(txtNhs.getText(),10)){
            client.setNhsNumber(Double.parseDouble(txtNhs.getText()));
        }
        else{
            error("Invalid NHS Number (Must be 10 length digit)");
            flag=false;
        }
        if(ImportedTestConnectorToInterface.checkNumber(txtPhone.getText(),11)){
            client.setPhoneNumber(Double.parseDouble(txtPhone.getText()));
        }
        else{
            error("Invalid PhoneNumber (Must be 11 length digit)");
            flag=false;
        }
        if(ImportedTestConnectorToInterface.checkNumber(txtTin.getText(),10)){
            client.setTin(Double.parseDouble(txtTin.getText()));
        }
        else{
            error("Invalid TIN Number (Must be 10 length digit)");
            flag=false;
        }
        if(flag)success("Applied Changes");
    }
    public void error(String error){
        Alert alert = new Alert(Alert.AlertType.ERROR,error);
        alert.show();
    }
    public void success(String success){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,success);
        alert.show();
    }
    public void onActionLogout(ActionEvent actionEvent) {
        Main.switchScene(actionEvent,BACKPATH);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtName.setText(client.getName());
        txtPhone.setText(String.format("%.0f",client.getPhoneNumber()));
        txtCcn.setText(String.format("%.0f",client.getCitizenCardNumber()));
        txtTin.setText(String.format("%.0f",client.getTin()));
        txtNhs.setText(String.format("%.0f",client.getNhsNumber()));
    }
}
