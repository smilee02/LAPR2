package app.ui.console;
import app.domain.model.ParameterCategory;
import app.domain.model.TypeOfTest;
import app.domain.model.Parameter;
import app.controller.CreateTypeTestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateTypeTestUI implements Runnable {
    static Scanner input = new Scanner(System.in);
    static CreateTypeTestController createTypeTestController = new CreateTypeTestController();
    public CreateTypeTestUI(){
        /**
         * We dont need any parameter
         */
    }

    @Override
    public void run() {
        String code, description, collectingMethods;
        CreateTypeTestController.display("Now, type the needed information");
        CreateTypeTestController.display("The code: ");
        code = input.nextLine();
        while (CreateTypeTestController.verifyCode(code) != 0){
            CreateTypeTestController.display("Invalid code, type in a new one: ");
            code = input.nextLine();
        }

        CreateTypeTestController.display("The description: ");
        description = input.nextLine();
        while (CreateTypeTestController.verifyDescription(description) != 0){
            CreateTypeTestController.display("Invalid description, type in a new one: ");
            description = input.nextLine();
        }

        CreateTypeTestController.display("The collecting method: ");
        collectingMethods = input.nextLine();
        while (CreateTypeTestController.verifyCollectingMethod(collectingMethods) != 0){
            CreateTypeTestController.display("Invalid collecting method, type in a new one: ");
            collectingMethods = input.nextLine();
        }

        CreateTypeTestController.display("Now, from this list, pick the category wanted: (if none just press ENTER)");
        List<ParameterCategory> parameterCategoryList = CreateTypeTestController.getParameterCategoryList();
        for (int i = 0; i < parameterCategoryList.size(); i++) {
            CreateTypeTestController.display(i + ") \n " + parameterCategoryList.get(i).toString());
        }

        List<String> arrayEscolhasUtilizador = new ArrayList<>();
        String escolhaUtilizador, escolhaContinuar;
        do {
            boolean seeIfNumber, seeIfExists, alreadyChosen;
            do {
                seeIfNumber = true;
                seeIfExists = true;
                alreadyChosen = true;
                escolhaUtilizador = input.nextLine();
                try {
                    Integer.parseInt(escolhaUtilizador);
                } catch (NumberFormatException e) {
                    CreateTypeTestController.display("Invalid option, try a number from the list: ");
                    seeIfNumber = false;
                    seeIfExists = false;
                }
                if (seeIfExists && Integer.parseInt(escolhaUtilizador) >= parameterCategoryList.size()){
                    CreateTypeTestController.display("Invalid option, try a number from the list: ");
                    seeIfExists = false;
                }
                if (arrayEscolhasUtilizador.contains(escolhaUtilizador)){
                    CreateTypeTestController.display("Invalid option, try a number from the list: ");
                    alreadyChosen = false;
                }
            } while (!seeIfNumber || !seeIfExists || !alreadyChosen);
            arrayEscolhasUtilizador.add(escolhaUtilizador);
            if (arrayEscolhasUtilizador.size() != parameterCategoryList.size()) {
                CreateTypeTestController.display("Do you wish to pick any other category? \n Yes \n No");
                escolhaContinuar = input.nextLine();
            }else {
                escolhaContinuar = "no";
            }
            while (!escolhaContinuar.equalsIgnoreCase("yes") && !escolhaContinuar.equalsIgnoreCase("y") && !escolhaContinuar.equalsIgnoreCase("n") && !escolhaContinuar.equalsIgnoreCase("no")) {
                CreateTypeTestController.display("Invalid option, do you wish to pick any other category? \n Yes \n No");
                escolhaContinuar = input.nextLine();
            }
        } while (escolhaContinuar.equalsIgnoreCase("yes") || escolhaContinuar.equalsIgnoreCase("y"));

        TypeOfTest createdTest = new TypeOfTest(code, description, collectingMethods);
        for (String s : arrayEscolhasUtilizador) {
            CreateTypeTestController.saveCategories(createdTest, Integer.parseInt(s));
        }
        CreateTypeTestController.display(createdTest.toString());
        CreateTypeTestController.display("");
        CreateTypeTestController.display("Valid data, do you wish to save it? (yes/no)");
        String wishToSave = input.nextLine();
        if (wishToSave.equalsIgnoreCase("yes") || wishToSave.equalsIgnoreCase("y")) {
            if (createTypeTestController.saveTypeTest(createdTest)){
                CreateTypeTestController.addCode(createdTest);
                CreateTypeTestController.display("Type of Test created with success");
            }else {
                CreateTypeTestController.display("There was a problem creating your type of test and it wasn't successful");
            }
        }
    }

}
