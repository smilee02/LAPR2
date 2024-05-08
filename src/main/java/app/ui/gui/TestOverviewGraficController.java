package app.ui.gui;

import app.ui.console.PerformanceGraphic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TestOverviewGraficController implements Initializable {
    @FXML
    private DatePicker pickerDate1;
    @FXML
    private DatePicker pickerDate2;
    private Date date1,date2;
    private PerformanceGraphic performanceGraphic = new PerformanceGraphic();
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private LineChart lineChart;
    private XYChart.Series series;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //System.out.println("yes");
    }

    public void btnBack(MouseEvent mouseEvent) {
        Main.switchScene(mouseEvent,"/fxml/laboratoryCoordinator.fxml");
    }
    public void setupGraph(ActionEvent actionEvent){
        int min = pickerDate1.getValue().getDayOfYear();
        int max = pickerDate2.getValue().getDayOfYear();
        int total = max-min+1;
        //System.out.println(total);
        if(total>0){
            xAxis = new NumberAxis(1,total,1);
            xAxis.setLabel("Dates");
            yAxis = new NumberAxis(-200,200,20);
            yAxis.setLabel("Amount");
            lineChart = new LineChart(xAxis,yAxis);
            series = new XYChart.Series();
            series.setName("Performance");
            List<Integer> integerList= performanceGraphic.PerformanceCalculation(date1,date2);
            int x=0;
            int size=24;
            for(int i =1;i<=integerList.size()/24;i++){
                fillGraph(x,size,integerList,i);
                //System.out.println(i);
                if(x==0){
                    x=24;
                }else{
                    x=x+24;
                }
                size=size+24;
                System.out.println(i);
            }
            series.getData().add(new XYChart.Data(total+1, 0));
            String sum = String.valueOf(performanceGraphic.MaximumSum(date1,date2));
            String value = String.valueOf(performanceGraphic.ValuesofSum(date1,date2));
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Maximum Sum:"+sum+"\n"+"Values Of Sum:"+value);
            alert.show();
            stage.setOnCloseRequest(windowEvent -> stageMain.show());

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Date 2 must be >= Date 1");
            alert.show();
        }


    }
    public void fillGraph(int x,int size,List<Integer> integerList,int i){
        for(;x<size; x++){
            //System.out.println(calculatePartOfDay(i,integerList.get(x)));
            //System.out.println(integerList.get(x));
            //System.out.println(integerList.get(x));
            //System.out.println(calculatePartOfDay(i,integerList.get(x)));
            series.getData().add(new XYChart.Data(calculatePartOfDay(i,integerList.get(x)), integerList.get(x)));
            //System.out.println("done");
        }
    }
    public double calculatePartOfDay(int today,int pos){
        double trueHour = pos+1.0;
        return ((double)today)+(trueHour/24.0);
    }
    public void onActionShowGraph(ActionEvent actionEvent) {
        date1=new Date(pickerDate1.getValue().getYear(),pickerDate1.getValue().getMonthValue(),pickerDate1.getValue().getDayOfMonth());
        date2=new Date(pickerDate2.getValue().getYear(),pickerDate2.getValue().getMonthValue(),pickerDate2.getValue().getDayOfMonth());;
        setupGraph(actionEvent);
    }

}
