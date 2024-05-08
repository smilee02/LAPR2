package app.ui.gui;

import app.controller.App;
import app.domain.model.Employee;
import app.ui.console.ValidateUI;
import app.ui.console.ValidatedWorkUI;
import app.ui.console.WorkToBeValidatedListUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LaboratoryCoordinatorController implements Initializable {
    @FXML
    private Button btnValidateWork;
    private App app = App.getInstance();
    private String pathToLogOut = "/fxml/sample.fxml";
    private static Employee labCoordinator;
    @FXML
    private Button btnShowTestResult;
    @FXML
    private Button btnImport;
    @FXML
    private Button btnLogout;
    @FXML
    private Label lblName;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblSoc;
    @FXML
    private Label lblEmail;

    public LaboratoryCoordinatorController(){
        /**
         * This constructor doesnt need any parameters
         */
    }

    public static void setLabCoordinator(Employee labCoordinatorLog) {
        labCoordinator = labCoordinatorLog;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblEmail.setText("Email : " + labCoordinator.getEmail());
        lblName.setText("Welcome " + labCoordinator.getName());
        lblPhone.setText("Phone : " + labCoordinator.getPhoneNumber());
        lblAddress.setText("Address : " + labCoordinator.getAddress());
        lblSoc.setText("SOC : " + labCoordinator.getStandardOcupationalCode());
    }

    public void onActionShowTests(ActionEvent actionEvent) {
        /*NumberAxis xAxis = new NumberAxis(1960,2020,10);
        xAxis.setLabel("Dates");
        NumberAxis yAxis = new NumberAxis(0,350,50);
        yAxis.setLabel("Amount");
        LineChart lineChart = new LineChart(xAxis,yAxis);
        XYChart.Series series = new XYChart.Series();
        series.setName("Performance");
        series.getData().add(new XYChart.Data(1980,200));
        series.getData().add(new XYChart.Data(1990,250));
        series.getData().add(new XYChart.Data(2000,340));
        lineChart.autosize();
        lineChart.getData().add(series);
        Group root = new Group(lineChart);
        Scene scene = new Scene(root,600,400);
        Stage stageMain = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        root.setAutoSizeChildren(true);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stageMain.hide();
        stage.setOnCloseRequest(windowEvent -> stageMain.show());*/

        Main.switchScene(actionEvent,"/fxml/TestOverviewController.fxml");
    }

    public void onActionImport(ActionEvent actionEvent) {
        Main.switchScene(actionEvent,"/fxml/importFile.fxml");
    }

    public void onActionLogout(ActionEvent actionEvent) {
        app.doLogout();
        Main.switchScene(actionEvent,pathToLogOut);
    }

    public void onActionValidateWork(ActionEvent actionEvent) {
        Main.minimizeMaximizeWindow(actionEvent,true);
        new ValidateUI().run();
        Main.minimizeMaximizeWindow(actionEvent,false);
    }
}
