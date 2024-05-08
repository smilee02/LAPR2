package app.ui.gui;
import app.controller.App;
import app.domain.shared.NhsShared;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private MainController mainController = new MainController();
    private Stage stage;
    private String primaryStagePath = "/fxml/sample.fxml";
    private String title = "ManyLabs";
    private String iconPath = "/images/icon.png";
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(primaryStagePath));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.setResizable(false);

        Image icon = new Image((iconPath));
        primaryStage.getIcons().add(icon);

        primaryStage.show();
        NhsShared.getVariablesNHSReport();
    }

    public static void switchScene(ActionEvent event, String path){
        try{
            Parent root = FXMLLoader.load(Main.class.getResource(path));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void switchScene(MouseEvent event, String path){
        try {
            Parent root1 = FXMLLoader.load(Main.class.getResource(path));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root1);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void minimizeMaximizeWindow(ActionEvent actionEvent, boolean minimize){
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setIconified(minimize);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
