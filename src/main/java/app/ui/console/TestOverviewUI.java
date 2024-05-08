package app.ui.console;

import app.controller.ClientController;
import app.controller.TestOverviewController;
import app.domain.model.RegisterTest;
import app.domain.model.Validate;

import java.util.*;

public class TestOverviewUI implements Runnable {
    static TestOverviewController testOverviewController = new TestOverviewController();

    static Scanner ler = new Scanner(System.in);

    public void run() {
        Date firstDateformat, secondDateformat;
        String firstAnswer, secondAnswer, firstdateformat, seconddateformat;
        final int q1answers = 4, q2answers = 4;
        int firstAnswerInt, secondAnswerInt, index = 1, intervaloftime = 0, hour, starthour = 8, endinghour = 20, minute = 0, count = 0;
        Calendar cal = Calendar.getInstance();


        boolean exit1 = false, exit2 = false;
        List<RegisterTest> registerTest = new ArrayList<>();
        List<Validate> validateTest = new ArrayList<>();
        List<Integer> ResSubVal = new ArrayList<>();


        while (!exit1) {

            testOverviewController.Show("What is the funcionality you desire?");
            testOverviewController.Show("1 - Check all tests registered in the system");
            testOverviewController.Show("2 - Select an algorithm to analyze the Tests");
            testOverviewController.Show("3 - Exit");


            firstAnswer = ler.nextLine();
            while (!(testOverviewController.ValidateFirstAnswer(firstAnswer, q1answers))) {
                testOverviewController.Show("You have written an invalid answer, please write a number between 1 and " + q1answers);
                firstAnswer = ler.nextLine();
            }

            firstAnswerInt = Integer.parseInt(firstAnswer);

            switch (firstAnswerInt) {
                case 1:

                    for (int i = 1; i < testOverviewController.getRegisterTest().size() + 1; i++) {
                        if (!(testOverviewController.SpecificTest(i) == null)) {
                            testOverviewController.Show(i + " - " + testOverviewController.SpecificTest(i));
                            testOverviewController.Show(" ");
                        }
                    }
                    break;

                case 2:
                    while (!exit2) {
                        testOverviewController.Show("Which option do you prefer to analyse the tests?");
                        testOverviewController.Show("1 - Check the number of clients of the laboratory");
                        testOverviewController.Show("2 - Check the number of tests waiting for results");
                        testOverviewController.Show("3 - Brute Force Algorithm");
                        testOverviewController.Show("4 - Exit");
                        secondAnswer = ler.nextLine();
                        while (!testOverviewController.ValidateSecondAnswer(secondAnswer, q2answers)) {
                            testOverviewController.Show("Your answer was invalid. Please type a number between 1 and " + q2answers);
                            secondAnswer = ler.nextLine();
                        }
                        secondAnswerInt = Integer.parseInt(secondAnswer);
                        switch (secondAnswerInt) {
                            case 1:
                                testOverviewController.Show("The List of clients existent in the database are :");
                                for (int x = 1; x < ClientController.clientList().size() + 1; x++) {
                                    testOverviewController.Show(x + " " + testOverviewController.getSpecificclient(x));
                                    testOverviewController.Show("");
                                }
                                for (int x = 0; x < 2; x++) {
                                    testOverviewController.Show("        ");
                                }
                                break;
                            case 2:
                                testOverviewController.Show("The tests waiting for results are:");
                                for (int i = 1; i < testOverviewController.getRegisterTest().size(); i++) {
                                    if (testOverviewController.findIfThereIsResult(testOverviewController.SpecificTest(i))) {
                                        testOverviewController.Show(index + " - " + testOverviewController.SpecificTest(i));
                                        index++;
                                    }
                                }
                                for (int x = 0; x < 2; x++) {
                                    testOverviewController.Show("        ");
                                }
                                index = 0;
                                break;

                            case 3:
                                /**
                                 * For every half an hour, every test is checked and put into a List, one for the test results and other for the valid tests registered in that time. The difference between the two is calculated and until the day ends, there is an array with every result from every 30 minutes. A positive value means that are more results than tests registered, as a negative value signifies otherwise.
                                 */
                                testOverviewController.Show("Dates should be introduced in format dd/mm/yyyy");

                                testOverviewController.Show("First date:");
                                firstdateformat = ler.nextLine();
                                testOverviewController.Show("Second date:");
                                seconddateformat = ler.nextLine();
                                while (!(testOverviewController.validateDate(firstdateformat)) || !(testOverviewController.validateDate(seconddateformat)) || !(testOverviewController.validateDate2(firstdateformat)) || !(testOverviewController.validateDate2(seconddateformat))) {
                                    testOverviewController.Show("Test should be written in format dd/mm/yyyy with valid data");
                                    testOverviewController.Show("First date:");
                                    firstdateformat = ler.nextLine();
                                    testOverviewController.Show("Second date:");
                                    seconddateformat = ler.nextLine();
                                }

                                firstDateformat = new Date(testOverviewController.ReturnYear(firstdateformat), testOverviewController.ReturnMonth(firstdateformat)-1, testOverviewController.ReturnDay(firstdateformat));
                                secondDateformat = new Date(testOverviewController.ReturnYear(seconddateformat), testOverviewController.ReturnMonth(seconddateformat)-1, testOverviewController.ReturnDay(seconddateformat));
                                if (!testOverviewController.ValidateTimeCohesion(firstDateformat, secondDateformat)) {
                                    Date aux1 = firstDateformat;
                                    firstDateformat = secondDateformat;
                                    secondDateformat = aux1;
                                    testOverviewController.Show("Your first date is now " + firstDateformat + " and your second date is now " + secondDateformat + " as you introduced the oldest day second.");
                                }
                                //System.out.println(firstDateformat);
                                Date currentday = firstDateformat;
                                while (!currentday.after(secondDateformat)) {
                                    hour = starthour;
                                    while (hour < endinghour) {
                                        for (int i = 1; i < testOverviewController.getRegisterTest().size(); i++) {
                                            if (testOverviewController.SpecificTest(i).getData().getDay() == currentday.getDay() /*&& testOverviewController.SpecificTest(i).getTest_Validation_DateHour().getMonth() == currentday.getMonth() && testOverviewController.SpecificTest(i).getTest_Validation_DateHour().getYear() == currentday.getYear()*/) {
                                                if (testOverviewController.SpecificTest(i).getData().getHours() == hour && testOverviewController.SpecificTest(i).getData().getMinutes() >= minute && testOverviewController.SpecificTest(i).getData().getMinutes() < minute + 30) {
                                                    registerTest.add(testOverviewController.SpecificTest(i));
                                                    //System.out.println("testes do dia " + currentday.getDay()+" "+ testOverviewController.SpecificTest(i).getData().getDay()+" date"+ testOverviewController.SpecificTest(i).getData());
                                                }
                                                if (testOverviewController.testResult(i).getValidateDate().getHours() == hour && testOverviewController.testResult(i).getValidateDate().getMinutes() > minute && testOverviewController.testResult(i).getValidateDate().getMinutes() < minute + 30) {
                                                    validateTest.add(testOverviewController.testResult(i));
                                                    //System.out.println("validate hora = " + testOverviewController.testResult(i).getValidateDate());
                                                }
                                                 }
                                        }
                                        //System.out.println("registertestsize " + registerTest.size() + "  validatetestsize" + validateTest.size());
                                        ResSubVal.add(intervaloftime, registerTest.size() - validateTest.size());
                                        // diferenca de novos testes ou seja da rececionista, e de resultados obtidos, ou seja validados
                                        validateTest.removeAll(validateTest);
                                        registerTest.removeAll(registerTest);
                                        minute = minute + 30;
                                        count++;
                                        intervaloftime = intervaloftime + 1;
                                        if (count == 2) {
                                            hour = hour + 1;
                                            minute = 0;
                                            count = 0;
                                        }
                                    }
                                    hour = starthour;
                                    minute = 0;
                                    count = 0;
                                    currentday = testOverviewController.addOneDay(currentday);
                                }
                                for (int u = 0; u < ResSubVal.size(); u++) {
                                    //System.out.println(u + " - " + ResSubVal.get(u));
                                }
                                testOverviewController.Show("Maximum contiguous sum is " + testOverviewController.BruteForceAlgorithm(ResSubVal));
                                testOverviewController.Show("Sum values are: ");
                                for (int i = 0; i < testOverviewController.BruteForceAlgorithm2(ResSubVal).size() - 1; i++) {
                                    testOverviewController.Show(testOverviewController.BruteForceAlgorithm2(ResSubVal).get(i));

                                }
                                testOverviewController.Show(testOverviewController.BruteForceAlgorithm2(ResSubVal).get(testOverviewController.BruteForceAlgorithm2(ResSubVal).size() - 1));


                                break;

                            case 4:
                                exit2 = true;
                                break;
                        }
                    }
                    exit2 = false;
                    break;
                case 3:
                    exit1 = true;
                    break;
            }
        }

    }
}