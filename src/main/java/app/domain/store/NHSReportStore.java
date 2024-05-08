package app.domain.store;

import app.domain.model.Client;
import app.domain.model.RecordResult;
import app.domain.shared.*;
import com.nhs.report.Report2NHS;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

/**
 * 
 */
public class NHSReportStore implements Serializable {

    /**
     * Some variables to keep the values for some actions
     */
    private static final int SIMPLE = 1;
    private static final int GET_NUMBER_OF_POSITIVES = 1;
    private static final int GET_NUMBER_OF_TESTS = 2;
    private static final int GET_AGES = 3;

    private static final SimpleDateFormat simpleDateFormat = Constants.SIMPLE_DATE_FORMAT;
    private static final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * DecimalFormat for the numbers in the report
     */
    private static final DecimalFormat df = new DecimalFormat("#.##");
    private static final DecimalFormat df4 = new DecimalFormat("#.####");

    public NHSReportStore() {

    }

    /**
     * Method to get the array X with the chosen data between the 2 given dates
     * 
     * @param date1               1st given date
     * @param date2               2nd given date
     * @param independentVariable independent variable chosen
     * @return an array with the daily values of the chosen independent variable
     */
    public double[] getX(Date date1, Date date2, int independentVariable) {
        List<RecordResult> list = getTestsWithResults();
        Collections.sort(list);
        int testListSize = list.size();
        boolean isFirst = true;
        Date lastSeenDate;

        List<Double> arrayListX = new ArrayList<>();
        List<Double> arrayDaily = new ArrayList<>();

        if (independentVariable == SIMPLE) {
            if (compareTwoDates(list.get(0).getCreatedAt(), date1) >= 0
                    && compareTwoDates(list.get(0).getCreatedAt(), date2) <= 0
                    && list.get(0).getTest().getTypeOfTest().getCode().equalsIgnoreCase("Covid")) {
                arrayDaily.add(1.0);
            }

            lastSeenDate = list.get(0).getCreatedAt();
            for (int i = 1; i < testListSize; i++) {
                if (compareTwoDates(list.get(i).getCreatedAt(), date1) >= 0
                        && compareTwoDates(list.get(i).getCreatedAt(), date2) <= 0
                        && list.get(i).getTest().getTypeOfTest().getCode().equalsIgnoreCase("Covid")) {
                    if (compareTwoDates(list.get(i).getCreatedAt(), lastSeenDate) != 0 && !isFirst) {
                        arrayListX.add(Calculations.summ(toArray(arrayDaily)));
                        arrayDaily.removeAll(arrayDaily);
                    } else {
                        isFirst = false;
                    }

                    lastSeenDate = list.get(i).getCreatedAt();
                    arrayDaily.add(1.0);
                }
            }
            arrayListX.add(Calculations.summ(toArray(arrayDaily)));
        } else {

            if (compareTwoDates(list.get(0).getCreatedAt(), date1) >= 0
                    && compareTwoDates(list.get(0).getCreatedAt(), date2) <= 0
                    && list.get(0).getTest().getTypeOfTest().getCode().equalsIgnoreCase("Covid")) {
                for (int j = 0; j < getClientList().size(); j++) {
                    if (getClientList().get(j).getTin() == list.get(0).getTest().getCCN()) {
                        arrayDaily.add(calculateAge(getClientList().get(0).getBirthDate()));
                    }
                }
            }

            lastSeenDate = list.get(0).getCreatedAt();
            for (int i = 1; i < testListSize; i++) {
                if (compareTwoDates(list.get(i).getCreatedAt(), date1) >= 0
                        && compareTwoDates(list.get(i).getCreatedAt(), date2) <= 0
                        && list.get(i).getTest().getTypeOfTest().getCode().equalsIgnoreCase("Covid")) {
                    if (compareTwoDates(list.get(i).getCreatedAt(), lastSeenDate) != 0 && !isFirst) {
                        arrayListX.add(Calculations.average(toArray(arrayDaily)));
                        arrayDaily.removeAll(arrayDaily);
                    } else {
                        isFirst = false;
                    }

                    lastSeenDate = list.get(i).getCreatedAt();
                    for (int j = 0; j < getClientList().size(); j++) {
                        if (getClientList().get(j).getTin() == list.get(i).getTest().getCCN()) {
                            arrayDaily.add(calculateAge(getClientList().get(i).getBirthDate()));
                        }
                    }

                }
            }
            arrayListX.add(Calculations.average(toArray(arrayDaily)));
        }
        return toArray(arrayListX);
    }

    /**
     * Method to get the average Covid-19 positives between 2 dates
     * 
     * @param date1 1st date
     * @param date2 2nd date
     * @return the average positive Covid-19 daily cases in array format
     */
    public double[] getY(Date date1, Date date2) {
        List<RecordResult> list = getTestsWithResults();
        Collections.sort(list);
        int testListSize = list.size();
        boolean isFirst = true;
        Date lastSeenDate;

        List<Double> arrayListY = new ArrayList<>();
        List<Double> arrayDaily = new ArrayList<>();

        if (compareTwoDates(list.get(0).getCreatedAt(), date1) >= 0
                && compareTwoDates(list.get(0).getCreatedAt(), date2) <= 0
                && list.get(0).getTest().getTypeOfTest().getCode().equalsIgnoreCase("Covid")
                && list.get(0).getResultList().get(0).getResult() > 1.4) {
            arrayDaily.add(1.0);
        }

        lastSeenDate = list.get(0).getCreatedAt();
        for (int i = 1; i < testListSize; i++) {
            if (compareTwoDates(list.get(i).getCreatedAt(), date1) >= 0
                    && compareTwoDates(list.get(i).getCreatedAt(), date2) <= 0
                    && list.get(i).getTest().getTypeOfTest().getCode().equalsIgnoreCase("Covid")
                    && list.get(i).getResultList().get(0).getResult() > 1.4) {
                if (compareTwoDates(list.get(i).getCreatedAt(), lastSeenDate) != 0 && !isFirst) {
                    arrayListY.add(Calculations.summ(toArray(arrayDaily)));
                    arrayDaily.removeAll(arrayDaily);
                } else {
                    isFirst = false;
                }
                arrayDaily.add(1.0);
                lastSeenDate = list.get(i).getCreatedAt();
            }
        }

        arrayListY.add(Calculations.summ(toArray(arrayDaily)));
        return toArray(arrayListY);
    }

    /**
     * Gets the array with the values of the chosen category
     * 
     * @param date    date from when the values will be gathered from
     * @param getWhat what values to gather
     * @return the array with the wanted values
     */
    public double[] getDailyArray(Date date, int getWhat) {
        List<RecordResult> list = getTestsWithResults();
        Collections.sort(list);
        int testListSize = list.size();
        List<Double> arrayList = new ArrayList<>();

        if (getWhat == GET_NUMBER_OF_POSITIVES) {

            if (compareTwoDates(list.get(0).getCreatedAt(), date) == 0
                    && list.get(0).getTest().getTypeOfTest().getCode().equalsIgnoreCase("Covid")
                    && list.get(0).getResultList().get(0).getResult() > 1.4) {
                arrayList.add(list.get(0).getResultList().get(0).getResult());
            }

            for (int i = 0; i < testListSize; i++) {
                if (compareTwoDates(list.get(i).getCreatedAt(), date) == 0
                        && list.get(0).getTest().getTypeOfTest().getCode().equalsIgnoreCase("Covid")
                        && list.get(i).getResultList().get(0).getResult() > 1.4) {
                    arrayList.add(list.get(i).getResultList().get(0).getResult());
                }
            }
        } else if (getWhat == GET_NUMBER_OF_TESTS) {

            if (compareTwoDates(list.get(0).getCreatedAt(), date) == 0
                    && list.get(0).getTest().getTypeOfTest().getCode().equalsIgnoreCase("Covid")) {
                arrayList.add(list.get(0).getResultList().get(0).getResult());
            }

            for (int i = 0; i < testListSize; i++) {
                if (compareTwoDates(list.get(i).getCreatedAt(), date) == 0
                        && list.get(0).getTest().getTypeOfTest().getCode().equalsIgnoreCase("Covid")) {
                    arrayList.add(list.get(i).getResultList().get(0).getResult());
                }
            }
        } else {
            if (compareTwoDates(list.get(0).getCreatedAt(), date) == 0
                    && list.get(0).getTest().getTypeOfTest().getCode().equalsIgnoreCase("Covid")) {
                for (int j = 0; j < getClientList().size(); j++) {
                    if (getClientList().get(j).getTin() == list.get(0).getTest().getCCN()) {
                        arrayList.add(calculateAge(getClientList().get(j).getBirthDate()));
                    }
                }
            }

            for (int i = 1; i < testListSize; i++) {
                if (compareTwoDates(list.get(i).getCreatedAt(), date) == 0
                        && list.get(i).getTest().getTypeOfTest().getCode().equalsIgnoreCase("Covid")) {

                    for (int j = 0; j < getClientList().size(); j++) {
                        if (getClientList().get(j).getTin() == list.get(i).getTest().getCCN()) {
                            arrayList.add(calculateAge(getClientList().get(j).getBirthDate()));
                        }
                    }

                }
            }
        }
        return toArray(arrayList);
    }

    /**
     * Gets a string with the prediction values from the number of days chosen
     * before the current day on the simple linear regression
     * 
     * @param X                array X
     * @param Y                array Y
     * @param linearRegression the linear regression originated from array X and Y
     * @param historicalPoints number of days chosen to go back and make the
     *                         prediction values on
     * @return the string to insert on the report with the wanted information
     */
    public String getPredictionValueDates(double[] X, double[] Y, LinearRegression linearRegression,
            int historicalPoints, double significanceLevel) {
        Date today = new Date();
        StringBuilder finalString = new StringBuilder();
        for (int i = 1; i <= historicalPoints; i++) {
            today = getYesterday(today);
            double[] todaysTests = getDailyArray(today, GET_NUMBER_OF_TESTS);
            double[] todaysPositives = getDailyArray(today, GET_NUMBER_OF_POSITIVES);
            PredictionValues predictionValues = new PredictionValues(X, Y, todaysTests, linearRegression,
                    significanceLevel);
            finalString.append(convertFromDateToString(today)).append("                    ")
                    .append(todaysPositives.length).append("                                      ")
                    .append(df4.format(linearRegression.predict(todaysTests.length))).append("\t\t\t\t\t")
                    .append(df4.format(predictionValues.getY0() - predictionValues.getTriangle())).append("-")
                    .append(df4.format(predictionValues.getY0() + predictionValues.getTriangle())).append(" \n");
        }
        return finalString.toString();
    }

    /**
     * Gets a string with the prediction values from the number of days chosen
     * before the current day on the multiple linear regression
     * 
     * @param historicalPoints              number of days chosen to go back and
     *                                      make the prediction values on
     * @param multipleLinearRegression      the multiple linear regression created
     *                                      from all the arrays
     * @param multipleVarianceAnalysisAnova the object created from the for the
     *                                      variance analysis anova table to get
     *                                      some values from
     * @param significanceLevel             the significance level for the
     *                                      confidence intervals to be made from
     * @return the string to insert on the report with the wanted information
     */
    public String getMultiplePredictionValuesDates(int historicalPoints,
            MultipleLinearRegression multipleLinearRegression,
            MultipleVarianceAnalysisAnova multipleVarianceAnalysisAnova, double significanceLevel) {
        Date today = new Date();
        StringBuilder finalString = new StringBuilder();
        for (int i = 1; i <= historicalPoints; i++) {
            today = getYesterday(today);
            double[] todaysTests1 = getDailyArray(today, GET_NUMBER_OF_TESTS);
            double[] todatsTests2 = getDailyArray(today, GET_AGES);
            MultipleHypothesisTests multipleHypothesisTests = new MultipleHypothesisTests(multipleLinearRegression,
                    multipleVarianceAnalysisAnova, significanceLevel, todaysTests1.length,
                    Calculations.average(todatsTests2));
            finalString.append(convertFromDateToString(today)).append("                    ")
                    .append(getDailyArray(today, GET_NUMBER_OF_POSITIVES).length)
                    .append("                                      ")
                    .append(df4.format(
                            multipleLinearRegression.predict(todaysTests1.length, Calculations.average(todatsTests2))))
                    .append("\t\t\t\t\t\t").append(df4.format(multipleHypothesisTests.getIC1())).append("-")
                    .append(df4.format(multipleHypothesisTests.getIC2())).append(" \n");
        }
        return finalString.toString();
    }

    /**
     * Compares two dates and returns a value to identify if one is greater than the
     * other
     * 
     * @param date1 1st date to compare
     * @param date2 2nd date to compare
     * @return a positive number if the 1st date is bigger, negative if the 1st date
     *         is smaller and 0 if they are equal
     */
    public int compareTwoDates(Date date1, Date date2) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        int answer = 0;
        try {
            answer = formatter.parse(formatter.format(date1)).compareTo(formatter.parse(formatter.format(date2)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return answer;
    }

    /**
     * Converts a List to normal array
     * 
     * @param list list to be converted
     * @return the List in the array format
     */
    public static double[] toArray(List<Double> list) {
        double[] arrayFinal = new double[list.size()];
        for (int i = 0; i < arrayFinal.length; i++) {
            arrayFinal[i] = list.get(i);
        }
        return arrayFinal;
    }

    /**
     * Converts the given date to string format
     * 
     * @param date given date
     * @return string format of the given date
     */
    public String convertFromDateToString(Date date) {
        String dateString = simpleDateFormat.format(date);
        String[] dateStringSplit = dateString.split(" ");
        return dateStringSplit[0];
    }

    /**
     * Returns the day before the given day
     * 
     * @param date given day
     * @return day before the given day
     */
    public Date getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);

        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, -1);
        }
        return calendar.getTime();
    }

    /**
     * Based on the string of a given birth date, it calculates the age of the
     * person born in that day
     * 
     * @param birthDate the birth date in string format
     * @return the age of the person
     */
    public double calculateAge(String birthDate) {
        Date date1 = new Date();
        try {
            date1 = simpleDateFormat2.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant1 = date.toInstant();
        LocalDate localDate1 = instant1.atZone(defaultZoneId).toLocalDate();
        Instant instant2 = date1.toInstant();
        LocalDate localDate2 = instant2.atZone(defaultZoneId).toLocalDate();
        return Period.between(localDate2, localDate1).getYears();
    }

    public List<RecordResult> getTestsWithResults() {
        return RecordResultStore.getTestResultsWithTests();
    }

    public List<Client> getClientList() {
        return ClientStore.getClients();
    }

    public String createReport(Date date1, Date date2, int independentVariable, int historicalPoints, int choice,
            double significanceLevel) {
        if (choice == SIMPLE) {
            double[] X = getX(date1, date2, independentVariable);
            double[] Y = getY(date1, date2);
            LinearRegression linearRegression = new LinearRegression(X, Y);
            SimpleVarianceAnalysisAnova varianceAnalysisAnova = new SimpleVarianceAnalysisAnova(X, Y, linearRegression);
            return ("The regression model fitted using data from the interval \n" +
                    "^y=" + df.format(linearRegression.intercept()) + "+" + df.format(linearRegression.slope()) + "x \n"
                    +
                    "// \n" +
                    "Other statistics \n" +
                    "R2 = " + df.format(linearRegression.R2()) + " \n" +
                    "R2adjusted= " + df.format(linearRegression.R2Adjusted()) + " \n" +
                    "R = " + df.format(Math.sqrt(linearRegression.R2())) + " \n" +
                    "// \n" +
                    "Hypothesis tests for regression coefficients \n" +
                    "H0:b=0 (a=0) H1: b<>0 (a<>0) \n" +
                    "t_obs: t_a=" + df.format(HypothesisTests.hypothesisTestA(linearRegression, X, Y)) + ", t_b="
                    + df.format(HypothesisTests.hypothesisTestB(linearRegression, X, Y)) + "  \n" +
                    "Decision: \n" +
                    HypothesisTests.makeDecision(linearRegression, X, Y, 1, significanceLevel) + ", "
                    + HypothesisTests.makeDecision(linearRegression, X, Y, 2, significanceLevel) + " \n" +
                    "// \n" +
                    " \n" +
                    "Significance model with Anova \n" +
                    "H0: b=0  H1:b<>0 \n" +
                    "\t\tdf\tSS\t\tMS\t\tF \n" +
                    "Regression\t1\t" + df.format(varianceAnalysisAnova.getSR()) + "\t\t"
                    + df.format(varianceAnalysisAnova.getMSR()) + "\t\t" + df.format(varianceAnalysisAnova.getF())
                    + "\n" +
                    "Residual\t" + (Y.length - 2) + "\t" + df.format(varianceAnalysisAnova.getSE()) + "\t\t"
                    + df.format(varianceAnalysisAnova.getMSE()) + "\n" +
                    "Total\t\t" + (Y.length - 1) + "\t" + df.format(varianceAnalysisAnova.getST()) + "\n" +
                    "\n" +
                    "Decision: \n" +
                    df.format(varianceAnalysisAnova.getF()) + " > f1, " + (Y.length - 2) + ", " + significanceLevel
                    + "=" + df.format(varianceAnalysisAnova.getfSnedecorValue(significanceLevel, (Y.length - 2))) + "\n"
                    +
                    varianceAnalysisAnova.makeDecision(varianceAnalysisAnova.getF(),
                            varianceAnalysisAnova.getfSnedecorValue(significanceLevel, (Y.length - 2)))
                    + "\n" +
                    "\n" +
                    "// Prediction values \n" +
                    "\n" +
                    "Date           Number of OBSERVED positive cases        Number of ESTIMATED/EXPECTED positive cases \t\t"
                    + (100 - (significanceLevel * 100)) + "% intervals \n" +
                    getPredictionValueDates(X, Y, linearRegression, historicalPoints, significanceLevel));
        } else {
            double[] X1 = getX(date1, date2, 1);
            double[] X2 = getX(date1, date2, 2);
            double[] Y = getY(date1, date2);
            MultipleLinearRegression multipleLinearRegression = new MultipleLinearRegression(X1, X2, Y);
            MultipleVarianceAnalysisAnova multipleVarianceAnalysisAnova = new MultipleVarianceAnalysisAnova(
                    multipleLinearRegression);
            return ("The regression model fitted using data from the interval \n" +
                    "y=" + df.format(multipleLinearRegression.getB0()) + " + "
                    + df.format(multipleLinearRegression.getB1()) + "x1 + "
                    + df.format(multipleLinearRegression.getB2()) + "x2 \n" +
                    "// \n" +
                    "Other statistics \n" +
                    "R2 = " + df.format(multipleVarianceAnalysisAnova.getR2()) + " \n" +
                    "R2adjusted= " + df.format(multipleVarianceAnalysisAnova.getR2Adjusted()) + " \n" +
                    "R = " + df.format(Math.sqrt(multipleVarianceAnalysisAnova.getR2())) + " \n" +
                    "// \n" +
                    "Hypothesis tests for regression coefficients \n" +
                    "H0:B1=0 B2=0 H1: B1<>0 B2<>0 \n" +
                    "t_obs: t_B1="
                    + df.format(MultipleHypothesisTests
                            .calculateActualTValue(multipleLinearRegression, multipleVarianceAnalysisAnova, 1))
                    + " t_B2="
                    + df.format(MultipleHypothesisTests.calculateActualTValue(multipleLinearRegression,
                            multipleVarianceAnalysisAnova, 2))
                    + " \n" +
                    "Decision: \n" +
                    MultipleHypothesisTests.makeDecision(multipleLinearRegression, significanceLevel,
                            MultipleHypothesisTests.calculateActualTValue(multipleLinearRegression,
                                    multipleVarianceAnalysisAnova, 1),
                            1)
                    + ", "
                    + MultipleHypothesisTests.makeDecision(multipleLinearRegression, significanceLevel,
                            MultipleHypothesisTests.calculateActualTValue(multipleLinearRegression,
                                    multipleVarianceAnalysisAnova, 2),
                            2)
                    + " \n" +
                    "// \n" +
                    " \n" +
                    "Significance model with Anova \n" +
                    "H0: b=0  H1:b<>0 \n" +
                    "\t\tdf\tSS\t\tMS\t\tF \n" +
                    "Regression\t2\t" + df.format(multipleVarianceAnalysisAnova.getSQR()) + "\t\t"
                    + df.format(multipleVarianceAnalysisAnova.getMQR()) + "\t\t"
                    + df.format(multipleVarianceAnalysisAnova.getF()) + "\n" +
                    "Residual\t" + (Y.length - 3) + "\t" + df.format(multipleVarianceAnalysisAnova.getSQE()) + "\t\t"
                    + df.format(multipleVarianceAnalysisAnova.getMQE()) + "\n" +
                    "Total\t\t" + (Y.length - 1) + "\t" + df.format(multipleVarianceAnalysisAnova.getSQT()) + "\n" +
                    "\n" +
                    "Decision: \n" +
                    df.format(multipleVarianceAnalysisAnova.getF()) + " > f" + significanceLevel + ", 2, "
                    + (Y.length - 3) + "="
                    + df.format(multipleVarianceAnalysisAnova.getfSnedecorValue(significanceLevel, (Y.length - 3)))
                    + "\n" +
                    multipleVarianceAnalysisAnova.makeDecision(multipleVarianceAnalysisAnova.getF(),
                            multipleVarianceAnalysisAnova.getfSnedecorValue(significanceLevel, (Y.length - 3)))
                    + "\n" +
                    "\n" +
                    "// Prediction values \n" +
                    "\n" +
                    "Date           Number of OBSERVED positive cases        Number of ESTIMATED/EXPECTED positive cases \t\tNumber of Covid tests in "
                    + (100 - (significanceLevel * 100)) + "% intervals \n" +
                    getMultiplePredictionValuesDates(historicalPoints, multipleLinearRegression,
                            multipleVarianceAnalysisAnova, significanceLevel));
        }
    }

    public void sendReport(String report) {
        Report2NHS.writeUsingFileWriter(report);
    }

    public LinearRegression createLinearRegression(Date date1, Date date2, int independentVarialbe) {
        double[] X = getX(date1, date2, independentVarialbe);
        double[] Y = getY(date1, date2);
        return new LinearRegression(X, Y);
    }
}
