package app.ui.gui;


import app.controller.ManualNHSReportController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class RegressionPrevisionController implements Initializable {
    private Alert alert = new Alert(Alert.AlertType.NONE);
    @FXML
    private TextField txtSignificanceLevel;
    @FXML
    private DatePicker datePickerFirstDate;
    @FXML
    private DatePicker datePickerLastDate;
    @FXML
    private Button btnBack;
    @FXML
    private ComboBox<String> comboBoxRegressionModel;
    @FXML
    private ComboBox<String> comboBoxTime;
    @FXML
    private TextField txtNumberOfHistoricalPoints;
    @FXML
    private CheckBox checkNumberOfTests;
    @FXML
    private CheckBox checkAverageAge;
    @FXML
    private Button btnSendReport;
    private String timeChosen;
    private String regressionChosen;
    private int numberOfHistoricalPoints;
    private int daysInAWeek = 7;
    private Date firstDateForRegressionModel;
    private Date lastDateForRegressionModel;
    private double significanceLevel;
    private ZoneId defaultZoneId = ZoneId.systemDefault();
    private int independentVariable; //Number of Covid-19 tests or the Average Age
    private static final int CHOICE1 = 1;
    private static final int CHOICE2 = 2;

    public void onActionGoBack(ActionEvent actionEvent) {
        Main.switchScene(actionEvent, "/fxml/administrator.fxml");
    }

    public void onActionSendReport(ActionEvent actionEvent) {
        if (getRegressionModel()) {
            regressionChosen = comboBoxRegressionModel.getSelectionModel().getSelectedItem();
            if (regressionChosen.equals("Simple")) {
                checkNumberOfTests.setDisable(false);
                checkAverageAge.setDisable(false);
               doRegression(true);
            } else if (regressionChosen.equals("Multiple")) {
                checkNumberOfTests.setDisable(true);
                checkAverageAge.setDisable(true);
                doRegression(false);
                System.out.println("multiple");
            }
        } else {
            showAlert("Choose a regression model", "You haven't chosen any regression model", Alert.AlertType.WARNING);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> regressions = new ArrayList<>();
        regressions.add("Simple");
        regressions.add("Multiple");
        ObservableList<String> list = FXCollections.observableList(regressions);
        comboBoxRegressionModel.setItems(list);
        comboBoxRegressionModel.getSelectionModel().selectFirst();
        List<String> time = new ArrayList<>();
        time.add("Days");
        time.add("Weeks");
        ObservableList<String> times = FXCollections.observableList(time);
        comboBoxTime.setItems(times);
        comboBoxTime.getSelectionModel().selectFirst();
        checkNumberOfTests.setSelected(true);
        checkAverageAge.setSelected(false);
    }

    public boolean validateHistoricalPoints() {
        String aux = txtNumberOfHistoricalPoints.getText();
        try {
            Integer.parseInt(aux);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validateSignificanceLevel() {
        String aux = txtSignificanceLevel.getText();
        try {
            Double.parseDouble(aux);
            if (aux.isEmpty()) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean getRegressionModel() {
        String aux = comboBoxRegressionModel.getSelectionModel().getSelectedItem();
        return aux != null;
    }

    public boolean getTimeChosen() {
        String aux = comboBoxTime.getSelectionModel().getSelectedItem();
        return aux != null;
    }

    public void dates(){
        //Get Dates
        if (validateDate(datePickerFirstDate)) {
            firstDateForRegressionModel = getDate(datePickerFirstDate);
            if (validateDate(datePickerLastDate)) {
                lastDateForRegressionModel = getDate(datePickerLastDate);

            }
        }
    }

    public  void getCheckBoxes(){
        //Check Boxes
        if (checkAverageAge.isSelected() && !checkNumberOfTests.isSelected()) {
            independentVariable = 2;
        } else if (checkNumberOfTests.isSelected() && !checkAverageAge.isSelected()) {
            independentVariable = 1;
        } else if (checkNumberOfTests.isSelected() && checkAverageAge.isSelected()) {
            showAlert("2 Independent Variables chosen", "Both independent variables can not be selected at the same time", Alert.AlertType.ERROR);
        } else if (!checkNumberOfTests.isSelected() && !checkAverageAge.isSelected()) {
            showAlert("No Independent Variables have been chosen", "You need choose at least one of the independent variables available", Alert.AlertType.ERROR);
        }
    }

    public Date getDate(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        return date;
    }

    public boolean validateDate(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();
        if (localDate==null){
            showAlert("Date cannot be blank","Please choose a valid date", Alert.AlertType.ERROR);
            return false;
        }
        if(localDate.isAfter(LocalDate.now())) {
            showAlert("Wrong Date","The date cannot be after this day", Alert.AlertType.WARNING);
            return false;
        }
        if (localDate.isAfter(datePickerLastDate.getValue())){
            showAlert("Wrong Date","The date cannot be after the last date", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    public void doRegression(boolean simple){
        if (validateHistoricalPoints()) {//Historical Points
            if (validateSignificanceLevel()) {//Significance Level
                significanceLevel = Double.parseDouble(txtSignificanceLevel.getText());
                if (getTimeChosen()) {

                    //Historical Points chosen
                    timeChosen = comboBoxTime.getSelectionModel().getSelectedItem();
                    if (timeChosen.equals("Days")) {
                        numberOfHistoricalPoints = Integer.parseInt(txtNumberOfHistoricalPoints.getText());
                    } else if (timeChosen.equals("Weeks")) {
                        numberOfHistoricalPoints = Integer.parseInt(txtNumberOfHistoricalPoints.getText()) * daysInAWeek;
                    }
                    dates();
                    getCheckBoxes();
                    ManualNHSReportController manualNHSReportController = new ManualNHSReportController();
                    if (firstDateForRegressionModel!=null && lastDateForRegressionModel!=null && validateDate(datePickerFirstDate) && validateDate(datePickerLastDate)){
                        if (((checkNumberOfTests.isSelected() && !checkAverageAge.isSelected()) || (!checkNumberOfTests.isSelected() && checkAverageAge.isSelected())) && simple) {
                            System.out.println(firstDateForRegressionModel + " | " + lastDateForRegressionModel + " | " + independentVariable + " | " + numberOfHistoricalPoints + " | " + 1 + " | " + significanceLevel);
                            String report = manualNHSReportController.createReport(firstDateForRegressionModel, lastDateForRegressionModel, independentVariable, numberOfHistoricalPoints,  CHOICE1, significanceLevel);
                            manualNHSReportController.sendReport(report);
                        }else {
                            System.out.println("We are going to do multiple");
                            System.out.println(firstDateForRegressionModel + " | " + lastDateForRegressionModel + " | " + independentVariable + " | " + numberOfHistoricalPoints + " | " + 1 + " | " + significanceLevel);
                            String report = manualNHSReportController.createReport(firstDateForRegressionModel, lastDateForRegressionModel, independentVariable, numberOfHistoricalPoints,  CHOICE2, significanceLevel);
                            manualNHSReportController.sendReport(report);
                            System.out.println("multiple is done");
                        }
                    }
                } else {
                    showAlert("Choose Days or Weeks", "You haven't chosen the time for the historical points", Alert.AlertType.WARNING);
                }
            } else {
                showAlert("Significance Level needs to be a number", txtSignificanceLevel.getText() + "cannot be converted into a significance level.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Historical Points need to be a number", txtNumberOfHistoricalPoints.getText() + " cannot be converted into a number.", Alert.AlertType.ERROR);
        }
    }

    public void showAlert(String header, String message, Alert.AlertType alertType) {
        alert.setAlertType(alertType);
        alert.setContentText(message);
        alert.setHeaderText(header);
        alert.show();
    }
}
