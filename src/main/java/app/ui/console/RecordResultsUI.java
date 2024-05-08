package app.ui.console;

import app.controller.RecordResultsController;
import app.domain.model.RecordResult;
import app.domain.model.TestParameterResult;
import app.domain.model.Parameter;
import app.domain.model.RegisterTest;
import app.domain.shared.BarcodeAdapter;
import app.domain.shared.BarcodeBarbecueAdapter;
import app.domain.shared.Constants;
import app.domain.store.RecordResultStore;
import app.ui.console.utils.Utils;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecordResultsUI implements Runnable {
    static Scanner in = new Scanner(System.in);
    static int option = 0;
    static RecordResultsController recordResultsController = new RecordResultsController();
    TestParameterResult t;
    @Override
    public void run() {
        int cont = 0;
        String barcode = Utils.readLineFromConsole("Please insert the code from the sample:\n");
        if(recordResultsController.verifyTestRegisteredBySampleCode(barcode)){
            RegisterTest testRegistered = recordResultsController.getTestRegisteredBySampleCode(barcode);
            String n = "";
            do {
                n = Utils.readLineFromConsole("Insert the access key for the record of the test results");
            } while (!checkNumber(n));
            int accessKey = Integer.parseInt(n);
            int size = recordResultsController.getParametersFromARegisteredTest(testRegistered).size();
            RecordResult recordResult = recordResultsController.createNewTestResultsRecord(testRegistered);
            List<Parameter> parameterList = recordResultsController.createCopyParameterList(testRegistered);
            while (cont < size) {
                t = RecordResultsController.recordTestResults(parameterList, accessKey, testRegistered);
                if (RecordResultsController.validateTestParameterResult(t)) {
                    int option = Utils.readIntegerFromConsole(recordResultsController.toDTO(t).toString() + "\nThis is a valid data, would you like to save it?\n(1)Yes\n(2)No");
                    if (option == 1) {
                        parameterList = recordResultsController.removeSelectedParameter(t, parameterList);
                        recordResultsController.addTestParameterResult(t);
                        cont++;
                    }
                } else {
                    RecordResultsController.print("This is not a valid result for this parameter\n");
                }
            }
            RecordResultsController.print("Is this information correct:\n");
            RecordResultsController.print(recordResultsController.toDTO(recordResult).toString());
            option = Utils.readIntegerFromConsole("\n(1)Yes\n(2)No");
            if (option == 1) {
                recordResultsController.saveRecordResults();
            }
        }
    }

    public boolean checkNumber(String aux){
        try {
            int op = Integer.parseInt(aux);
        }catch (NumberFormatException e){
            RecordResultsController.print("\nThe access key must be a number\n");
            return false;
        }
        return true;
    }
}
