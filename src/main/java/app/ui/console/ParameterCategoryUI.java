package app.ui.console;
import app.controller.ParameterCategoryController;
import app.domain.model.ParameterCategory;

import java.util.Scanner;

public class ParameterCategoryUI implements Runnable{
    static Scanner ler = new Scanner(System.in);
    public ParameterCategoryUI(){
        /**
         * We dont need any parameter
         */
    }
    @Override
    public void run() {

        String code;
        String shortDescription;
        String resposta2;
        String resposta4;
        int resposta;

        ParameterCategoryController parameterController = new ParameterCategoryController();
        int xy = 0;
        while (xy < 2) {
            parameterController.print("What is the funcionality you pretend?");
            parameterController.print("1-Add the category code and the category name");
            parameterController.print("2-Exit");
            resposta = ler.nextInt();
            ler.nextLine();
            if (resposta == 1) {
                parameterController.print("Please type the code of the parameter category. (5 alphanumeric characters)");
                code = ler.nextLine();
                while (parameterController.validatecode(code) == 1) {
                    parameterController.print("You have written an invalid code, please type a code with 5 alphanumeric characters");
                    code = ler.nextLine();
                }
                if (parameterController.validatecode(code) == 0) {
                    parameterController.print("Your code meets the requirements needed.");
                }
                parameterController.print("Your code is " + code + ". Do you want to change it? (Y/N)");
                resposta2 = ler.nextLine();
                if (resposta2.equalsIgnoreCase("y")) {
                    parameterController.print("Please type the new code.");
                    code = parameterController.alterarcodigo(code);

                } else {
                    parameterController.print("Your code wasn't changed.");
                }
                while (parameterController.validatecode(code) == 1) {
                    parameterController.print("You have written an invalid code, please type a code with 5 alphanumeric characters");
                    code = ler.nextLine();
                }
                parameterController.print("Please wait, your code is being saved.");
                parameterController.print("The code was saved successfuly");
                parameterController.print("Type the description of the category (less than 10 characters) ");
                shortDescription = ler.nextLine();
                while (parameterController.validateshortDescription(shortDescription) == 1) {
                    parameterController.print("You have written an invalid description. Please type a description with 10 characters");
                    shortDescription = ler.nextLine();
                }
                if (parameterController.validateshortDescription(shortDescription) == 0) {
                    parameterController.print("Your description meets the requirements needed");
                }
                parameterController.print("Do you want to change your category description? (Y/N)");
                resposta4 = ler.nextLine();
                do {
                    if((!resposta4.equalsIgnoreCase("n") && (!resposta4.equalsIgnoreCase("y")))) {
                        parameterController.print("please type a vaild answer. (Y/N)");
                        resposta4 = ler.nextLine();
                    }
                    if (resposta4.equalsIgnoreCase("n")) {
                        parameterController.print("Please wait, your description is being saved");
                    }
                    else if (resposta4.equalsIgnoreCase("y")) {
                        parameterController.print("Please enter the short description you pretend.");
                        shortDescription = parameterController.alterardescriçao(shortDescription);

                        while (parameterController.validateshortDescription(shortDescription) == 1) {
                            parameterController.print("You have written an invalid description. Please type a description with 10 characters");
                            shortDescription = ler.nextLine();
                        }
                    }
                }while (!resposta4.equalsIgnoreCase("y") && !resposta4.equalsIgnoreCase("n"));
                ParameterCategory PC = parameterController.createParameterCategory(code,shortDescription);
                parameterController.SaveParameterCategory(PC);
                parameterController.print("Your description was saved successfuly");
            }
            if (resposta == 2) {
                parameterController.print("We appreciate your usage of our app.");
                break;
            }

            xy++;
        }

    }
}