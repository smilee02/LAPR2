package app.ui.console;
import app.controller.ParameterCategoryController;
import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.controller.ParameterController;
import app.domain.model.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParameterUI implements Runnable{
    static Scanner input = new Scanner(System.in);
    static ParameterController parameterController = new ParameterController();
    public ParameterUI(){
        /**
         * We dont need any parameter
         */
    }
    @Override
    public void run(){
        String code, name, description;
        ParameterController.inConsoleNoLn("Type in the parameter's code: ");
        code = input.nextLine();
        while (ParameterController.validateCode(code) != 0){
            ParameterController.inConsoleNoLn("Invalid code, type in a new one: ");
            code = input.nextLine();
        }

        ParameterController.inConsoleNoLn("Type in the parameter's short name: ");
        name = input.nextLine();
        while (ParameterController.validateName(name) != 0){
            ParameterController.inConsoleNoLn("Invalid name, type in a new one: ");
            name = input.nextLine();
        }

        ParameterController.inConsoleNoLn("Type in the parameter's description: ");
        description = input.nextLine();
        while (ParameterController.validateDescription(description) != 0){
            ParameterController.inConsoleNoLn("Invalid description, type in a new one: ");
            description = input.nextLine();
        }

        ParameterController.inConsole("Now, from the list , pick the category wanted: ");
        List<ParameterCategory> parameterCategoryList = Company.getParametercategorylist();
        for (int i = 0; i < parameterCategoryList.size(); i++){
            ParameterController.inConsole(i + ") " + parameterCategoryList.get(i).toString());
        }

        String escolhaUtilizador;
        boolean seeIfNumber;
        do {
            seeIfNumber = true;
            escolhaUtilizador = input.nextLine();
            try {
                Integer.parseInt(escolhaUtilizador);
            } catch (NumberFormatException e) {
                ParameterController.inConsoleNoLn("Invalid option, try a number from the list: ");
                seeIfNumber = false;
            }
        } while (!seeIfNumber);
        Parameter parameter = new Parameter(code, name, description);
        ParameterCategoryController.addParameter(parameter, Integer.parseInt(escolhaUtilizador));
        System.out.println(parameter);
        ParameterController.inConsoleNoLn("Valid data, do you wish to save? \n Yes \n No \n");
        boolean invalidAnswer;
        do {
            invalidAnswer = false;
            String wishToSave = input.nextLine();
            if (wishToSave.equalsIgnoreCase("yes") || wishToSave.equalsIgnoreCase("y")) {
                if (parameterController.saveParameter(parameter)) {
                    ParameterController.inConsole("Parameter created with success");
                } else {
                    ParameterController.inConsole("There was a problem creating your type of test and it wasn't successful");
                }
            } else if (!(wishToSave.equalsIgnoreCase("no") || wishToSave.equalsIgnoreCase("n"))) {
                ParameterController.inConsoleNoLn("Invalid answer, type in a new one: ");
                invalidAnswer = true;
            }
        }while (invalidAnswer);
    }
}
