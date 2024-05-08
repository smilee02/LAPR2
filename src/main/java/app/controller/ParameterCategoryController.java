package app.controller;
import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;

import java.util.Scanner;

public class ParameterCategoryController {
    static Scanner ler = new Scanner(System.in);
    ParameterCategory Teste3;
    int flag1 = 0;

    public int validatecode(String code) {
        boolean seeIfExists = false;
        for (int i = 0; i < Company.getCodeList().size(); i++) {
            if (code.equals(Company.getCodeList().get(i))) {
                seeIfExists = true;
                break;
            }
        }
        if (code.length() != 5 || seeIfExists) {
            return 1;
        }else {
            return 0;
        }
    }

    public int validateshortDescription(String shortDescription) {
        if (shortDescription.length() > 10) {
            return 1;
        } else return 0;
    }

    public String alterarcodigo(String code) {
        String newcode;
        String resposta3;
        newcode = ler.nextLine();
        if (newcode.equals(code)) {
            print("WATCH OUT! Before moving on to the next step mind that the new code you just introduced and the first are the same!");
        }
        print("You're about to change your code from " + code + " to " + newcode + ". Would you like to continue?(y/n)");
        resposta3 = ler.nextLine();
        do {
            if (resposta3.equalsIgnoreCase("n")) {
                print("The new code wasn't saved, therefore, the one about to be saved is going to be the first you introduced.");
                return code;
            }
            if (resposta3.equalsIgnoreCase("y")) {
                print("Your new code is " + newcode);
                return newcode;
            } else {
                print("Please enter a valid answer. (Y/N)");
                resposta3 = ler.nextLine();
            }
        } while (!resposta3.equalsIgnoreCase("n") || !resposta3.equalsIgnoreCase("y"));
        return "Se esta mensagem aparecer, o método alterarcódigo tem um erro.";
    }

    public String alterardescriçao(String shortDescription) {
        String newshortDescription;
        String resposta;
        newshortDescription = ler.nextLine();
        if (newshortDescription.equals(shortDescription)) {
            print("WATCH OUT! Before moving on to the next step mind that the new category description you just introduced and the first are exactly the same!");
        }
        print("You are about to change the description from " + shortDescription + " to " + newshortDescription + ". Would you like to continue? (Y/N)");
        resposta = ler.nextLine();
        do {
            if (resposta.equalsIgnoreCase("y")) {
                print("Your description is now " + newshortDescription);
                flag1 = 1;
                return newshortDescription;

            }
            if (resposta.equalsIgnoreCase("n")) {
                print("The new description wasn't saved.");
                return shortDescription;
            }
        } while (!resposta.equalsIgnoreCase("y") && !resposta.equalsIgnoreCase("n"));

        return "Se esta mensagem aparecer, o método alterardescrição tem um erro";
    }

    public boolean SaveParameterCategory(ParameterCategory PC) {
        return Company.getParameterCategoryStore().SaveParameterCategory(PC);
    }

    public ParameterCategory createParameterCategory(String code, String description){
        this.Teste3 = new ParameterCategory(code,description);
        return Teste3;
    }

    public static void addParameter(Parameter parameter, int categoryNumber){
        Company.getParametercategorylist().get(categoryNumber).addParameter(parameter);
    }

    public void print(String string){
        System.out.println(string);
    }
}