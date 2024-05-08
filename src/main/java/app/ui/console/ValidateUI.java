package app.ui.console;

import app.controller.ValidateController;
import app.ui.console.utils.Utils;

import java.util.Scanner;

public class ValidateUI implements Runnable {
    static Scanner in = new Scanner(System.in);
    static int option;
    static String line;
    static ValidateController validateController = new ValidateController();

    public void run() {
        int size = ValidateController.getValidatedList().size();
        int number = Utils.showAndSelectIndex(ValidateController.getValidateDTOList(), "Work to Be Validated");
        if (number < size) {
            ValidateController.printConsole("\nThere is no work to be validated at the moment.");
        } else {
            ValidateController.printConsole("\nWant to validate this work?");
            do {
                line = Utils.readLineFromConsole("\n(1)Yes\n(2)No");
            }while (!checkNumber(line,1,2));
            if (Integer.parseInt(line) == 1){
                if(validateController.addValidated(number)){
                    ValidateController.printConsole("\nIt has been validated successfully");
                }
                validateController.removeValidated(number);
                validateController.sendNotification(number);
            }
        }
    }

    public static boolean checkNumber(String aux, int min, int max){
        int op;
        try{
            op = Integer.parseInt(aux);
        }catch (NumberFormatException e){
            ValidateController.printConsole("This is not a valid option.");
            return false;
        }
        if (op<min && op>max){
            return false;
        }
        return true;
    }

}
