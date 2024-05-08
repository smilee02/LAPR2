package app.domain.shared;

import app.controller.App;
import app.controller.ManualNHSReportController;
import auth.domain.model.User;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class NhsShared implements  Files{
    private App app = App.getInstance();
    private static Properties pros;
    private static final int SIMPLE = 1;
    private static final int MULTIPLE = 2;

    public static void setPros(Properties prosLog) {
        pros = prosLog;
    }

    public static void getVariablesNHSReport() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        ManualNHSReportController manualNHSReportController = new ManualNHSReportController();
        int numberOfHistoricalPoints;
        double significanceLevel = 0;
        int regressionModelChosen;
        try {
            regressionModelChosen = Integer.parseInt(pros.getProperty("regressionModel"));
            String nHistoricPoints = pros.getProperty("numberOfHistoricalPoints");
            String sigLevel = pros.getProperty("significanceLevel");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date firstDate = format.parse(pros.getProperty("firstDate"));
            Date lastDate = format.parse(pros.getProperty("lastDate"));
            try {
                numberOfHistoricalPoints = Integer.parseInt(nHistoricPoints);
                try {
                    significanceLevel = Double.parseDouble(sigLevel);
                }catch (NumberFormatException e){
                    System.out.println("It was not possible to convert the significance level defined in the configuration file. It should be a number between 0 and 1.");
                }
                if (manualNHSReportController.getNHSReportStore().getTestsWithResults().size() > 2) {
                    if (regressionModelChosen == 1) {
                        double testR2 = manualNHSReportController.createLinearRegression(firstDate,lastDate,1).R2();
                        double meanAgeR2 = manualNHSReportController.createLinearRegression(firstDate,lastDate,2).R2();
                        if (testR2>meanAgeR2){
                            SendReportTask sendReportTask = new SendReportTask(firstDate, lastDate, 1, numberOfHistoricalPoints, SIMPLE, significanceLevel); //as datas estao bem, o 1 é por ser testes e o outro presumi q seja simples
                        } else if (meanAgeR2>testR2){
                            SendReportTask sendReportTask = new SendReportTask(firstDate, lastDate, 2, numberOfHistoricalPoints, SIMPLE, significanceLevel); //as datas estao bem, o 2 é por ser ages e o outro é simples (por em variaveis dps os 1s e 2s)
                        } else {
                            SendReportTask sendReportTask = new SendReportTask(firstDate, lastDate, 1, numberOfHistoricalPoints, SIMPLE, significanceLevel); //as datas estao bem, o 1 é por ser testes e o outro é simples
                        }
                    } else if (regressionModelChosen == 2) {
                        SendReportTask sendReportTask = new SendReportTask(firstDate, lastDate, 1, numberOfHistoricalPoints, MULTIPLE, significanceLevel); //as datas estao bem, o 1 é por ser testes e o outro é multipla
                    } else {
                        System.out.println("The regression model chosen is not available in the system.");
                    }
                }
            }catch (NumberFormatException e){
                System.out.println("It was not possible to convert the historical points defined in the configuration file. Make sure they are a valid positive number.");
            }
        }catch (NumberFormatException e){
            System.out.println("It was not possible to find the desired regression model. In the config file type 1 for Simple Linear Regression, and 2 for Multiple Linear Regression.");
        }

    }
}
