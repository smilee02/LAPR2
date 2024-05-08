package app.ui.console;

import app.controller.ReportController;
import app.domain.model.Report;
import app.domain.store.RecordResultStore;

import java.util.Scanner;

/**
 * 
 */
public class ReportUI implements Runnable {

    /**
     * The scanner to read the inputs from the user
     */
    static Scanner input = new Scanner(System.in);

    /**
     * Initializing the ReportController
     */
    static ReportController reportController = new ReportController();

    /**
     * Creates an instance of ReportUI
     */
    public ReportUI() {
        /**
         * We dont need any parameter
         */
    }

    /**
     * It's the class that communicates with the user
     */
    @Override
    public void run() {
        String respostaUtilizador;
        String choiceFromMenu;
        int id;

        do {
            ReportController.inConsole(
                    "What do you wish to do? \n 1- Create a new report of an existing test \n 2- See all existing reports \n 3- Exit");
            do {
                choiceFromMenu = input.nextLine();
            } while (validateErrors(choiceFromMenu, 3));

            if (Integer.parseInt(choiceFromMenu) == 1) {
                if (RecordResultStore.getTestResultsWithTests().isEmpty()) {
                    ReportController.inConsole("There are no testes to make a report on ");
                } else {
                    for (int i = 0; i < RecordResultStore.getTestResultsWithTests().size(); i++) {
                        ReportController
                                .inConsole(i + ") " + RecordResultStore.getTestResultsWithTests().get(i).toString());
                    }
                    ReportController.inConsole(
                            "Now, from this list pick the number from the test you wish to write a report on: ");
                    do {
                        respostaUtilizador = input.nextLine();
                    } while (validateErrors(respostaUtilizador, 1));
                    id = Integer.parseInt(respostaUtilizador);
                    String report;
                    ReportController.inConsole("Here are the evaluations on the reference values:");
                    for (int i = 0; i < RecordResultStore.getTestResultsWithTests().get(id).getResultList()
                            .size(); i++) {
                        ReportController.inConsole(reportController.validateTestParameterResult(
                                RecordResultStore.getTestResultsWithTests().get(id).getResultList().get(i)));
                    }
                    ReportController.inConsole("Now, type in the report: ");
                    do {
                        report = input.nextLine();
                        if (!reportController.validateReport(report)) {
                            ReportController.inConsole("Invalid report, write a new one:");
                        } else {
                            ReportController.inConsole("Valid report");
                        }
                    } while (!reportController.validateReport(report));
                    ReportController.inConsole(report);
                    ReportController.inConsole("Do you wish to save this report? \n Yes \n No");
                    do {
                        respostaUtilizador = input.nextLine();
                    } while (validateErrors(respostaUtilizador, 2));
                    Report report1 = reportController.createReport(report, id);
                    int resultsSize = RecordResultStore.getTestResultsWithTests().get(id).getResultList().size();
                    if (respostaUtilizador.equalsIgnoreCase("y") || respostaUtilizador.equalsIgnoreCase("yes")) {
                        if (reportController.saveReport(report1)) {
                            for (int i = 0; i < resultsSize; i++) {
                                reportController.addDiagnosis(report1, reportController.validateTestParameterResult(
                                        RecordResultStore.getTestResultsWithTests().get(id).getResultList().get(i)));
                            }
                            ReportController.inConsole("Your report was saved successfully!");
                        } else {
                            ReportController.inConsole("There was a problem and we could not save your report");
                        }
                    } else {
                        ReportController.inConsole("Your report was not saved.");
                    }
                }
            }

            if (Integer.parseInt(choiceFromMenu) == 2) {
                if (reportController.getReportList().isEmpty()) {
                    ReportController.inConsole("There are no reports to be shown");
                } else {
                    ReportController.inConsole("Here are the reports and the respective tests: ");
                    for (int i = 0; i < reportController.getReportList().size(); i++) {
                        ReportController.inConsole(reportController.getReportList().get(i).getReport());
                        ReportController.inConsole(RecordResultStore.getTestResultsWithTests()
                                .get(reportController.getReportList().get(i).getId()).toString());
                    }
                }
            }
        } while (Integer.parseInt(choiceFromMenu) != 3);
    }

    /**
     * Validates the given answer to see if it is an error or not
     *
     * @param userAnswer            The given answer
     * @param typeOfErrorToValidate The number to identify the validation to be done
     * @return A boolean that signals success or no success
     */
    public boolean validateErrors(String userAnswer, int typeOfErrorToValidate) {
        if (typeOfErrorToValidate == 1) {
            try {
                Integer.parseInt(userAnswer);
            } catch (NumberFormatException e) {
                ReportController.inConsole("Invalid answer, try a number");
                return true;
            }
            if (Integer.parseInt(userAnswer) >= RecordResultStore.getTestResultsWithTests().size()
                    || Integer.parseInt(userAnswer) < 0) {
                ReportController.inConsole("Invalid answer, try a number from the list");
                return true;
            }
            return false;
        }

        else if (typeOfErrorToValidate == 2) {
            if (!(userAnswer.equalsIgnoreCase("yes") || userAnswer.equalsIgnoreCase("y")
                    || userAnswer.equalsIgnoreCase("no") || userAnswer.equalsIgnoreCase("m"))) {
                ReportController.inConsole("Invalid answer, try one of the options shown");
                return true;
            }
            return false;
        }

        else {
            try {
                Integer.parseInt(userAnswer);
            } catch (NumberFormatException e) {
                ReportController.inConsole("Invalid answer, try a number");
                return true;
            }
            if (Integer.parseInt(userAnswer) > 3 || Integer.parseInt(userAnswer) < 0) {
                ReportController.inConsole("Invalid answer, try a number from the list");
                return true;
            }
            return false;
        }
    }
}