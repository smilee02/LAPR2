package app.ui.gui;

import app.controller.App;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.shared.Files;
import app.domain.shared.ImportedTestConnectorToInterface;
import app.domain.store.ClientStore;
import app.domain.store.EmployeeStore;
import app.domain.store.RecordResultStore;
import app.domain.store.RegisterTestStore;
import auth.AuthFacade;
import auth.domain.store.UserStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Imported test Graphical Interface Controller
 *
 * 
 */
public class ImportTestInterfaceController implements Initializable {
    private String filePath;
    private ObservableList<User> list;
    private List<importedTest> importedTestList = new ArrayList<>();
    private ImportedTestConnectorToInterface importedTestConnectorToInterface = new ImportedTestConnectorToInterface();
    private static AuthFacade authFacade;
    private static Company company = App.getInstance().getCompany();

    /**
     * Empty Constructor
     */
    public ImportTestInterfaceController() {
        /**
         * this constructor doesnt need any parameters
         */
    }

    /**
     *
     * @param authFacadeLog sets authfacade object
     */
    public static void setAuthFacade(AuthFacade authFacadeLog) {
        authFacade = authFacadeLog;
    }

    @FXML
    private ImageView imageAddFile;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> colClient;

    @FXML
    private TableColumn<User, String> colTest;

    /**
     *
     * @return fills interface column with imported tests info
     */
    public ObservableList<User> fillFile() {
        tableView.getItems().clear();
        importedTestConnectorToInterface.clearList();
        importedTestList = importedTestConnectorToInterface.filePart(filePath);
        list = FXCollections.observableArrayList();
        for (importedTest x : importedTestList) {
            list.add(new User(x.toStringClient(), x.toStringRecordResult()));
        }
        return list;
    }

    /**
     * Register clients and save them on company
     */
    public void addUser() {
        if (importedTestList.size() != 0) {
            for (importedTest x : importedTestList) {
                authFacade.addUserWithRole(x.getClient().getName(), x.getClient().getEmail(),
                        PasswordGenerationEmailSMS.emailClientRegistration(x.getClient().getEmail()),
                        Constants.ROLE_CLIENT);
            }
        }
    }

    /**
     * main func to choose file show imported test and serialize them
     * 
     * @param mouseEvent button click image
     */
    public void onActionAddFile(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        try {
            File file = fileChooser.showOpenDialog((Stage) ((Node) mouseEvent.getSource()).getScene().getWindow());
            filePath = file.getAbsolutePath();
            list = fillFile();
        } catch (Exception e) {

        }

        Alert alert;
        if (importedTestList.size() != 0) {
            tableView.setItems(list);
            if (importedTestConnectorToInterface.fail == 0) {
                alert = new Alert(Alert.AlertType.INFORMATION, "All file valid, check logs at /logs/logImport.txt");

            } else {
                alert = new Alert(Alert.AlertType.ERROR,
                        "File has some invalid data, check logs at /logs/logImport.txt");

            }
            alert.showAndWait();
            addUser();
            Files.encrypt("ClientStore.ser", ClientStore.getClients());
            Files.encrypt("RecordResultStore.ser", RecordResultStore.getTestResultsWithTests());
            Files.encrypt("RegisterTestStore.ser", RegisterTestStore.getRegisterTestList());
            Files.encrypt("EmployeeStore.ser", EmployeeStore.getEmployees());
            Files.encrypt("UserStore.ser", UserStore.getStore());
            // Files.encrypt("ValidateStoreDone.ser",company.getValidateStore().getValidateList());
            // Files.encrypt("TypeOfTestStore.ser",company.getTypeOfTestStore().getListOfTypeOfTest());
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "File was already imported or its an invalid file");
            alert.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colClient.setCellValueFactory(new PropertyValueFactory<User, String>("colClient"));
        colTest.setCellValueFactory(new PropertyValueFactory<User, String>("colTest"));
    }

    /**
     * returns to main interface
     * 
     * @param actionEvent button back
     */
    public void onActionBack(ActionEvent actionEvent) {
        Main.switchScene(actionEvent, "/fxml/laboratoryCoordinator.fxml");
    }
}
