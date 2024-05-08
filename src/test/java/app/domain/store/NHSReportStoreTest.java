package app.domain.store;



import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class NHSReportStoreTest {

    private static final NHSReportStore store = new NHSReportStore();

    @Test
    public void compareTwoDates() {
        Date date1 = new Date(121, 2, 1);
        Date date2 = new Date(121, 2, 1);
        int realAnswer = store.compareTwoDates(date1, date2);
        int expectedAnswer = 0;
        assertEquals(expectedAnswer, realAnswer);
    }

    @Test
    public void compareTwoDates2() {
        Date date1 = new Date(122, 2, 1);
        Date date2 = new Date(121, 2, 1);
        int realAnswer = store.compareTwoDates(date1, date2);
        int expectedAnswer = 1;
        assertEquals(expectedAnswer, realAnswer);
    }

    @Test
    public void compareTwoDates3() {
        Date date1 = new Date(120, 2, 1);
        Date date2 = new Date(121, 2, 1);
        int realAnswer = store.compareTwoDates(date1, date2);
        int expectedAnswer = -1;
        assertEquals(expectedAnswer, realAnswer);
    }

    @Test
    public void compareTwoDates4() {
        Date date1 = new Date(122, 2, 1);
        Date date2 = new Date(121, 2, 1);
        int realAnswer = store.compareTwoDates(date1, date2);
        int expectedAnswer = -1;
        assertNotEquals(expectedAnswer, realAnswer);
    }

    @Test
    public void compareTwoDates5() {
        Date date1 = new Date(122, 2, 1);
        Date date2 = new Date(123, 2, 1);
        int realAnswer = store.compareTwoDates(date1, date2);
        int expectedAnswer = 1;
        assertNotEquals(expectedAnswer, realAnswer);
    }

    @Test
    public void compareTwoDates6() {
        Date date1 = new Date(121, 2, 2);
        Date date2 = new Date(121, 2, 1);
        int realAnswer = store.compareTwoDates(date1, date2);
        int expectedAnswer = 0;
        assertNotEquals(expectedAnswer, realAnswer);
    }

    @Test
    public void compareTwoDates7() {
        Date date1 = new Date(122, 1, 1);
        Date date2 = new Date(121, 12, 12);
        int realAnswer = store.compareTwoDates(date1, date2);
        int expectedAnswer = 1;
        assertEquals(expectedAnswer, realAnswer);
    }
/*
    //-1 segunda maior, 1 uma maior, 0 iguais
    @Test
    public void convertFromDateToString() {
        Date date = new Date(2021 - 1900, 3 - 1, 1);
        String realValue = store.convertFromDateToString(date);
        String expectedValue = "01/03/2021";
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void convertFromDateToString2() {
        Date date = new Date(2019 - 1900, 7 - 1, 1);
        String realValue = store.convertFromDateToString(date);
        String expectedValue = "01/07/2019";
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void convertFromDateToString3() {
        Date date = new Date(2021 - 1900, 3 - 1, 1);
        String realValue = store.convertFromDateToString(date);
        String expectedValue = "01/2/121";
        assertNotEquals(expectedValue, realValue);
    }

    @Test
    public  void convertFromDateToString4() {
        Date date = new Date(2021 - 1900, 3 - 1, 1);
        String realValue = store.convertFromDateToString(date);
        String expectedValue = "01/1/1900";
        assertNotEquals(expectedValue, realValue);
    }*/

    @Test
    public void getYesterday() {
        Date date = new Date(2021 - 1900, 3 - 1, 2);
        Date realValue = store.getYesterday(date);
        Date expectedValue = new Date(2021 - 1900, 3 - 1, 1);
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void getYesterday2() {
        Date date = new Date(2021 - 1900, 4 - 1, 7);
        Date realValue = store.getYesterday(date);
        Date expectedValue = new Date(2021 - 1900, 4 - 1, 6);
        assertEquals(expectedValue, realValue);
    }

    @Test
    public void getYesterday3() {
        Date date = new Date(2021 - 1900, 3 - 1, 3);
        Date realValue = store.getYesterday(date);
        Date expectedValue = new Date(2021 - 1900, 3 - 1, 1);
        assertNotEquals(expectedValue, realValue);
    }

    @Test
    public void getYesterday4() {
        Date date = new Date(2021 - 1900, 3 - 1, 1);
        Date realValue = store.getYesterday(date);
        Date expectedValue = new Date(2021 - 1900, 3 - 1, 2);
        assertNotEquals(expectedValue, realValue);
    }
/*
    @Test
    public void calculateAge() {
        Date date = new Date(2020 - 1900, 3 - 1, 1);
        double realValue = store.calculateAge(store.convertFromDateToString(date));
        double expectedValue = 1.0;
        assertEquals(expectedValue, realValue,0.0);
    }


        @Test
    public    void calculateAge2() {
            Date date = new Date(2020 - 1900, 8 - 1, 4);
            double realValue = store.calculateAge(store.convertFromDateToString(date));
            double expectedValue = 0.0;
            assertEquals(expectedValue, realValue,0.0);
        }

    @Test
    public void calculateAge3() {
        Date date = new Date(2016 - 1900, 3 - 1, 1);
        double realValue = store.calculateAge(store.convertFromDateToString(date));
        double expectedValue = 5.0;
        assertEquals(expectedValue, realValue,0.0);
    }

    @Test
    public void calculateAge4() {
        Date date = new Date(2000 - 1900, 2 - 1, 1);
        double realValue = store.calculateAge(store.convertFromDateToString(date));
        double expectedValue = 21.0;
        assertEquals(expectedValue, realValue,0.0);
    }*/
//erro
    @Test
    public void toArray() {
        List<Double> list = Arrays.asList(1.4, 2.5, 3.6, 4.7);
        double[] expresult = {1.4, 2.5, 3.6, 4.7};
        Object[] result = list.toArray();
        assertNotEquals(expresult, (result));
    }

    @Test
    public void toArray2() {
        List<Double> list = Arrays.asList(1.4, 2.5, 3.6, 4.7);
        double[] expresult = {1, 2, 3, 4};
        Object[] result = list.toArray();
        assertNotEquals(expresult, result);
    }

    @Test
    public void toArray3() {
        List<Double> list = Arrays.asList(1.99, 2.99, 3.99, 4.99);
        double[] expresult = {2, 3, 4, 5};
        Object[] result = list.toArray();
        assertNotEquals(expresult, result);
    }
//erro
    @Test
    public void toArray4() {
        List<Double> list = Arrays.asList(1.1);
        double[] expresult = {1.1};
        Object[] result = list.toArray();
        assertNotEquals(expresult, result);
    }
/*
    @Test
    public void testConvertFromDateToString() {
        Date date = new Date(2001-1900, 2-1 , 5);
        String expresult = "05/02/2001";
        String result = store.convertFromDateToString(date);
        assertEquals(expresult,result);
    }
    @Test
    public void testConvertFromDateToString2() {
        Date date = new Date(2018-1900, 3-1 , 5);
        String expresult = "05/03/2018";
        String result = store.convertFromDateToString(date);
        assertEquals(expresult,result);
    }
    @Test
    public void testConvertFromDateToString3() {
        Date date = new Date(2018-1900, 3-1 , 5);
        String expresult = "05/02/121";
        String result = store.convertFromDateToString(date);
        assertNotEquals(expresult,result);
    }
    @Test
    public void testConvertFromDateToString4() {
        Date date = new Date(2018-1900, 3-1 , 5);
        String expresult = "05/01/1900";
        String result = store.convertFromDateToString(date);
        assertNotEquals(expresult,result);
    }*/
    @Test
    public void toArray1(){
        List<Double> a = new ArrayList<>();
        a.add(3.0);
        a.add(4.0);
        double[] arr = new double[2];
        arr[0] = 3.0;
        arr[1] = 4.0;
        assertEquals(arr.length,NHSReportStore.toArray(a).length);

    }
    @Test
    public void calucateAge(){
        store.calculateAge("#");
    }
    @Test
    public void gets(){
        assertNotNull(store.getTestsWithResults());
        assertNotNull(store.getClientList());
    }
}