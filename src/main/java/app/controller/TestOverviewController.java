package app.controller;

import app.domain.model.*;
import app.domain.store.RegisterTestStore;
import app.domain.store.TestOverviewStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.util.concurrent.TimeUnit;

import static app.domain.store.TestOverviewStore.RecordResultList;

public class TestOverviewController {
    Company company= App.getInstance().getCompany();
    RegisterTestStore registerTestStore = company.getRegisterTestStore();
    TestOverviewStore testOverviewStore = company.getTestOverviewStore();


    /**
     * Prints a message.
     *
     * @param message that is going to be printed. Can be a object of any type.
     */
    public void Show(Object message) {
        System.out.println(message);
    }


    /**
     * Validates if the number written by the user is a answer between 1 and q1answers.
     *
     * @param firstAnswer is the answer given by the user.
     * @param q1answers   is the number of valid answers the user can choose from.
     * @return if the number is valid.
     */
    public boolean ValidateFirstAnswer(String firstAnswer, int q1answers) {
        try {
            Integer.parseInt(firstAnswer);
        } catch (NumberFormatException e) {
            return false;
        }
        int firstAnswerINT = Integer.parseInt(firstAnswer);
        if (firstAnswerINT <= q1answers || firstAnswerINT >= 1) {
            return true;
        }
        return false;
    }

    /**
     * Validates a integer received, checking if it is a Integer or a String. This method is useful in a way that helps the program not ending if it's supposed to write a Integer and the user doesn't do so, instead having a loop, until a Integer is written.
     *
     * @param FirstAnswer Inputs received that need to be Integers.
     * @return True if is a Integer, False if it is not.
     */
    public boolean ValidateInteger(String FirstAnswer) {
        try {
            Long.parseLong(FirstAnswer);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Validates if the answer of the second question is a valid number and in range of the offered possibilities.
     *
     * @param secondAnswer that was typed by the user.
     * @param q2answers    possible choices that the user posess.
     * @return if the answer was valid.
     */
    public boolean ValidateSecondAnswer(String secondAnswer, int q2answers) {
        try {
            Integer.parseInt(secondAnswer);
        } catch (NumberFormatException e) {
            return false;
        }
        int secondAnswerInt = Integer.parseInt(secondAnswer);
        if (secondAnswerInt <= q2answers || secondAnswerInt >= 1) {
            return true;
        }
        return false;
    }

    /**
     * Lists all the tests existent in the database.
     *
     * @return the list of tests.
     */
    public List<RegisterTest> getRegisterTest() {
        return registerTestStore.getRegisterTestList();
    }

    /**
     * Lists all the clients existent in the database.
     *
     * @return the list of clients.
     */
    public Client getSpecificclient(int pos) {
        return testOverviewStore.getSpecificClient(pos - 1);
    }

    public boolean ValidateTestSelected(String TestSelected) {
        if (Integer.parseInt(TestSelected) < 1) {
            return false;
        }
        if (ValidateInteger(TestSelected)) {
            if (testOverviewStore.getTestList().size() + 1 >= Integer.parseInt(TestSelected)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reaches a test.
     * @param i position to be reached.
     * @return the test
     */
    public RegisterTest SpecificTest(int i) {
        return testOverviewStore.getTestList().get(i - 1);
    }

    /**
     * Reaches a validated test.
     * @param i position to be reached.
     * @return the test
     */
    public Validate testResult(int i) {
        return company.getValidateStore().getValidateList().get(i - 1);
    }

    /**
     * Checks if a there is a result for a specific test
     * @param rt test search the result
     * @return the result
     */
    public boolean findIfThereIsResult(RegisterTest rt) {
        for (RecordResult x : RecordResultList()) {
            if (x.getTest().equals(rt)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates if a year is valid
     * @param year to be validated
     * @return if its valid
     */
    public boolean ValidateYear(String year) {
        boolean flag = true;
        try {
            Integer.parseInt(year);
            /*int n = Integer.parseInt(year);
            if (n > Calendar.YEAR) {
                return false;
            }*/
        } catch (NumberFormatException e) {
            flag = false;
        }
        int n = Integer.parseInt(year);
        if (n > LocalDate.now().getYear()) {
            flag = false;
        }
        return flag;
    }

    /**
     * Validates if a day is valid
     * @param day of the date
     * @param month of the date
     * @param year of the date
     * @return if its valid.
     */
  /*  public boolean ValidateDay(String day, String month, String year) {
        if (!ValidateYear(year)) {
            return false;
        }
        if (!ValidateMonth(month)) {
            return false;
        }
        try {
            Integer.parseInt(day);
        } catch (NumberFormatException e) {
            return false;
        }
        int Day = Integer.parseInt(day);

        if (month.equalsIgnoreCase("january") || month.equalsIgnoreCase("march") || month.equalsIgnoreCase("may") || month.equalsIgnoreCase("july") || month.equalsIgnoreCase("august") || month.equalsIgnoreCase("october") || month.equalsIgnoreCase("december")) {
            if (Day < 32 && Day > 0) {
                return true;
            }
        }
        if (month.equalsIgnoreCase("february")) {
            if (Integer.parseInt(year) % 4 == 0) {
                if (Day < 30 && Day > 0) {
                    return true;
                }
            } else {
                if (Day < 29 && Day > 0) {
                    return true;
                }
            }
        } else {
            if (Day < 31 && Day > 0) {
                return true;
            }

        }
        return false;
    }*/

    /**
     * Validates a month as a string
     * @param month to be validated
     * @return if its valid
     */
   /* public boolean ValidateMonth(String month) {
        if (month.equalsIgnoreCase("january") || month.equalsIgnoreCase("february") || month.equalsIgnoreCase("march") || month.equalsIgnoreCase("april") || month.equalsIgnoreCase("may") || month.equalsIgnoreCase("june") || month.equalsIgnoreCase("july") || month.equalsIgnoreCase("august") || month.equalsIgnoreCase("september") || month.equalsIgnoreCase("october") || month.equalsIgnoreCase("november") || month.equalsIgnoreCase("december")) {
            return true;
        }
        return false;
    }
*/
    /**
     * Validates a month as a int
     * @param month to be validated
     * @return if its valid
     */
    public boolean ValidateMonthInt(String month) {
       /* if (!ValidateDayInt(day, month, year)) {
            return false;
        }
        if (!ValidateYear(year)) {
            return false;
        }*/
        try {
            Integer.parseInt(month);
        } catch (NumberFormatException e) {
            return false;
        }
        return Integer.parseInt(month) < 13 && Integer.parseInt(month) > 0;
    }

    /**
     * Validates a day as a int
     * @param day to be validated
     * @param month to be validated
     * @param year to be validated
     * @return if its valid
     */
    public boolean ValidateDayInt(String day, String month, String year) {
       /* if (!ValidateYear(year)) {
            return false;
        }
        if (!ValidateMonthInt(day, month, year)) {
            return false;
        }*/
        try {
            Integer.parseInt(day);
        } catch (NumberFormatException e) {
            return false;
        }
        int Day = Integer.parseInt(day);
        int Month = Integer.parseInt(month);
        if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10 || Month == 12) {
            if (Day < 32 && Day > 0) {
                return true;
            }
        }
        if (Month == 2) {
            if (Integer.parseInt(year) % 4 == 0) {
                if (Day < 30 && Day > 0) {
                    return true;
                }
            } else {
                if (Day < 29 && Day > 0) {
                    return true;
                }
            }
        } else {
            if (Day < 31 && Day > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a date is before another.
     * @param firstdate to be validated
     * @param seconddate to be validated
     * @return if it respects the date cohesion
     */
    public boolean ValidateTimeCohesion(Date firstdate, Date seconddate) {
        if (firstdate.before(seconddate)) {
            return true;
        }
        return false;
    }

    /**
     * Converts a month string into the respective month number.
     * @param month to ba validated
     * @return the respective month
     */
   /* public int MonthAsInt(String month) {
        if (month.equalsIgnoreCase("january")) {
            return 1;
        }
        if (month.equalsIgnoreCase("february")) {
            return 2;
        }
        if (month.equalsIgnoreCase("march")) {
            return 3;
        }
        if (month.equalsIgnoreCase("april")) {
            return 4;
        }
        if (month.equalsIgnoreCase("may")) {
            return 5;
        }
        if (month.equalsIgnoreCase("june")) {
            return 6;
        }
        if (month.equalsIgnoreCase("july")) {
            return 7;
        }
        if (month.equalsIgnoreCase("august")) {
            return 8;
        }
        if (month.equalsIgnoreCase("september")) {
            return 9;
        }
        if (month.equalsIgnoreCase("october")) {
            return 10;
        }
        if (month.equalsIgnoreCase("november")) {
            return 11;
        }
        if (month.equalsIgnoreCase("december")) {
            return 12;
        }
        return 0;
    }
*/
    /**
     * Validates if a date respects the format(dd/mm/yyyy)
     * @param date to be checked
     * @return if its valid
     */
    public boolean validateDate(String date) {
        boolean flag = true;
        int cont = 0;
        final int char1 = 2; /*como esta no formato dd/mm/yyyy as barras vao estar nas posicoes 3 e 6, em char será 2 e 5 */
        final int char2 = 5;
        for (int i = 0; i < date.length(); i++) {//1º verificação para saber se só há digitos
            if (!Character.isDigit(date.charAt(i))) {
                if (!Character.toString(date.charAt(i)).equals("/")) {//2º verificação para saber se o é char /
                    flag = false;
                } else {
                    if (i != char1 && i != char2) {//3º verificação para saber se o char / está na posição correta
                        flag = false;
                    } else {
                        cont++;
                    }
                }
            }
        }
        if (cont != 2) flag = false;//4º verificação para saber se o numero de char / está correto
        return flag;
    }

    /**
     * Validates if the date received respects the usual date norms.
     * @param date to be validated
     * @return if its valid.
     */
    public boolean validateDate2(String date) {
        if (validateDate(date)) {
            String[] dateFix = date.split("/");
            String day = dateFix[0];
            String month = dateFix[1];
            String year = dateFix[2];

            if (ValidateMonthInt(month) && ValidateYear(year) && ValidateDayInt(day, month, year)) {

                return true;
            }
        }
        return false;
    }

    /**
     * Returns the day, after receiving as a string a date in the format(dd/mm/yyyy)
     * @param date to be received
     * @return the day
     */
    public int ReturnDay(String date) {
        if (validateDate2(date)) {
            String[] dateFix = date.split("/");
            String day = dateFix[0];
            return Integer.parseInt(day);
        }
        return 0;
    }
    /**
     * Returns the month, after receiving as a string a date in the format(dd/mm/yyyy)
     * @param date to be received
     * @return the month
     */
    public int ReturnMonth(String date) {
        if (validateDate2(date)) {
            String[] dateFix = date.split("/");
            String month = dateFix[1];
            return Integer.parseInt(month);
        }
        return 0;
    }
    /**
     * Returns the year, after receiving as a string a date in the format(dd/mm/yyyy)
     * @param date to be received
     * @return the year
     */
    public int ReturnYear(String date) {
        if (validateDate2(date)) {
            String[] dateFix = date.split("/");
            String year = dateFix[2];
            return Integer.parseInt(year);
        }
        return 0;
    }

    /**
     * Checks how many days is there between two dates, after validating cohesion.
     * @param firstdate to be received, should be the oldest
     * @param seconddate to be received, should be the newest
     * @return
     */
    public long DaysDifference(Date firstdate, Date seconddate) {
        Long diffInMillies = Math.abs(seconddate.getTime() - firstdate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        System.out.println("diferenca de dias" + diff);
        return diff;
    }

    /**
     * Algorithm that gets the maximum sum of a array (could be one number only)
     * @param array to be analysed
     * @return the biggest sum
     */
    public int BruteForceAlgorithm(List<Integer> array) {
        int somamaxima = Integer.MIN_VALUE, valoresdoarray = 0;
        for (int i = 0; i < array.size(); i++) {
            valoresdoarray = valoresdoarray + array.get(i);
            if (somamaxima < valoresdoarray) {
                somamaxima = valoresdoarray;
            }
            if (valoresdoarray < 0) {
                valoresdoarray = 0;
            }
        }
        return somamaxima;
    }

    /**
     * Algorithm that gets the elements that constitute the maximum sum, calculated in BruteForceAlgorithm.
     * @param array to be analysed
     * @return the elements of the sum
     */
    public List<Integer> BruteForceAlgorithm2(List<Integer> array) {
        int somamaxima = Integer.MIN_VALUE, valoresdoarray = 0, valormaximo = Integer.MIN_VALUE, start = 0, end = 0, s = 0;


        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > valormaximo) {
                valormaximo = array.get(i);
            }
        }
        List<Integer> valoresdasoma = new ArrayList<>(array.size());
        for (int i = 0; i < array.size(); i++) {
            valoresdoarray = valoresdoarray + array.get(i);
            if (somamaxima < valoresdoarray) {
                somamaxima = valoresdoarray;
                start = s;
                end = i;
            }
            if (valoresdoarray < 0) {
                valoresdoarray = 0;
                s = i + 1;
            }
        }
        if (somamaxima <= 1) {
            valoresdasoma.removeAll(valoresdasoma);
            valoresdasoma.add(valormaximo);
        }
        if (somamaxima > 1) {
            for (int j = start; j < end + 1; j++) {
                valoresdasoma.add(array.get(j));
            }
        }

        return valoresdasoma;
    }

    public Date addOneDay(Date date) {

        Date tomorrow = new Date(date.getTime() + (1000 * 60 * 60 * 24));
        return tomorrow;
        }
}




