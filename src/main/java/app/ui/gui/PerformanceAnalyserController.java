package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PerformanceAnalyserController implements Initializable {

    private String goBackPath = "/fxml/laboratoryCoordinator.fxml";
    @FXML
    private Button btnBack;
    @FXML
    private TextField lblDateFirst;
    @FXML
    private TextField lblDateLate;
    @FXML
    private Button btnGetPerformance;
    @FXML
    private ComboBox<String> comboBoxAlgorithm;
    @FXML
    private LineChart<String, Integer> lnChartPerformance;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionGoBack(ActionEvent actionEvent) {
        Main.switchScene(actionEvent,goBackPath);
    }

    public void showPerformance(ActionEvent actionEvent) {
    }
}
