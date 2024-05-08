package app.controller;



import java.time.LocalDate;
import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestOverviewControllerTest {
    TestOverviewController instance = new TestOverviewController();

    @Test
    public void validateYear() {
        String year = String.valueOf(LocalDate.now().getYear()+1);
        boolean expresult = false;
        boolean result = instance.ValidateYear(year);
        assertEquals(false, result);
    }

    @Test
    public void validateYear2() {
        String year = String.valueOf(Calendar.YEAR - 1);
        boolean expresult = true;
        boolean result = instance.ValidateYear(year);
        assertEquals(expresult, result);
    }

    @Test
    public void validateMonthInt() {
        String month = String.valueOf(14);
        boolean expresult = false;
        boolean result = instance.ValidateMonthInt(month);
        assertEquals(expresult, result);
    }

    @Test
    public void validateMonthInt2() {
        String month = String.valueOf(10);
        boolean expresult = true;
        boolean result = instance.ValidateMonthInt(month);
        assertEquals(expresult, result);
    }


    @Test
    public void validateDayInt() {
        String year = String.valueOf(2020);
        String month = String.valueOf(2);
        String day = String.valueOf(29);
        boolean expresult = true;
        boolean result = instance.ValidateDayInt(day, month, year);
        assertEquals(expresult, result);
    }

    @Test
    public void validateDayInt2() {
        String year = String.valueOf(2021);
        String month = String.valueOf(2);
        String day = String.valueOf(29);
        boolean expresult = false;
        boolean result = instance.ValidateDayInt(day, month, year);
        assertEquals(expresult, result);
    }

    @Test
    public void validateTimeCohesion() {
        Date firstdate = new Date(2020, Calendar.OCTOBER, 2);
        Date seconddate = new Date(2020, Calendar.OCTOBER, 4);
        boolean expresult = true;
        boolean result = instance.ValidateTimeCohesion(firstdate, seconddate);
        assertEquals(expresult, result);
    }

    @Test
    public void validateTimeCohesion2() {
        Date firstdate = new Date(2020, Calendar.OCTOBER, 4);
        Date seconddate = new Date(2020, Calendar.OCTOBER, 2);
        boolean expresult = false;
        boolean result = instance.ValidateTimeCohesion(firstdate, seconddate);
        assertEquals(expresult, result);
    }

    @Test
    public void validateDate() {
        String date = "10/07/2016";
        boolean expresult = true;
        boolean result = instance.validateDate(date);
        assertEquals(expresult, result);
    }

    @Test
    public void validateDate3() {
        String date = "10/7/2016";
        boolean expresult = false;
        boolean result = instance.validateDate(date);
        assertEquals(expresult, result);
    }

    @Test
    public void validateDate4() {
        String date = "010/07/2016";
        boolean expresult = false;
        boolean result = instance.validateDate(date);
        assertEquals(expresult, result);
    }
    @Test
    public void validateDate5() {
        String date = "10*07*2016";
        boolean expresult = false;
        boolean result = instance.validateDate(date);
        assertEquals(expresult, result);
    }
    @Test
    public void validateDate2true(){
        String date="10/07/2016";
        boolean expresult=true;
        boolean result= instance.validateDate2(date);
        assertEquals(expresult,result);
    }
    @Test
    public void validateDate2false(){
        String date="32/07/2016";
        boolean expresult=false;
        boolean result= instance.validateDate2(date);
        assertEquals(expresult,result);
    }

    @Test
    public void ReturnDay() {
        String date = "10/07/2016";
        int expresult = 10;
        int result = instance.ReturnDay(date);
        assertEquals(expresult, result);
    }

    @Test
    public void ReturnDay2() {
        String date = "10/07/2016";
        int expresult = 11;
        int result = instance.ReturnDay(date);
        assertNotEquals(expresult, result);
    }

    @Test
    public void ReturnMonth() {
        String date = "10/07/2016";
        int expresult = 7;
        int result = instance.ReturnMonth(date);
        assertEquals(expresult, result);
    }

    @Test
    public void ReturnMonth2() {
        String date = "10/07/2016";
        int expresult = 10;
        int result = instance.ReturnMonth(date);
        assertNotEquals(expresult, result);
    }

    @Test
    public void ReturnYear() {
        String date = "10/07/2016";
        int expresult = 2016;
        int result = instance.ReturnYear(date);
        assertEquals(expresult, result);
    }

    @Test
    public void ReturnYear2() {
        String date = "10/07/2016";
        int expresult = 7;
        int result = instance.ReturnYear(date);
        assertNotEquals(expresult, result);
    }

    @Test
    public void DaysDifference() {
        Date firstdate = new Date(2020, 7, 10);
        Date seconddate = new Date(2020, 7, 15);
        long expresult = 5;
        long result = instance.DaysDifference(firstdate, seconddate);
        assertEquals(expresult, result);
    }

    @Test
    public void DaysDifference2() {
        Date firstdate = new Date(2020, 7, 10);
        Date seconddate = new Date(2020, 7, 15);
        long expresult = 6;
        long result = instance.DaysDifference(firstdate, seconddate);
        assertNotEquals(expresult, result);
    }

    @Test
    public void BruteForceAlgorithmTrue() {
        List<Integer> Array = new ArrayList<>();
        Array.addAll(Arrays.asList(1, -2, 3, -4, 5, -6, 7, -8));
        int expresult = 7;
        int result = instance.BruteForceAlgorithm(Array);
        assertEquals(expresult, result);
    }

    @Test
    public void BruteForceAlgorithmFalse() {
        List<Integer> Array = new ArrayList<>();
        Array.addAll(Arrays.asList(1, -2, 3, -4, 5, -6, 7, -8));
        int expresult = 5;
        int result = instance.BruteForceAlgorithm(Array);
        assertNotEquals(expresult, result);
    }

    @Test
    public void BruteForceAlgorithm2True() {
        List<Integer> Array = new ArrayList<>();
        Array.addAll(Arrays.asList(1, -2, 3, -4, 5, -6, 7, -8));
        List expresult = new ArrayList();
        expresult.addAll(Arrays.asList(7));
        List result = instance.BruteForceAlgorithm2(Array);
        assertEquals(expresult, result);
    }

    @Test
    public  void BruteForceAlgorithm2False() {
        List<Integer> Array = new ArrayList<>();
        Array.addAll(Arrays.asList(1, -2, 3, -4, 5, -6, 7, -8));
        List expresult = new ArrayList();
        expresult.addAll(Arrays.asList(-4, 5, -6));
        List result = instance.BruteForceAlgorithm2(Array);
        assertNotEquals(expresult, result);
    }


}
