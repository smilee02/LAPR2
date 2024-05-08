package app.domain.shared;

import org.junit.Test;
import static org.junit.Assert.*;


import java.util.Arrays;


public class CalculationsTest {

    @Test
    public void summ() {
        double[] arr = new double[4];
        Arrays.fill(arr, 2.0);
        double result = Calculations.summ(arr);
        assertEquals(8.0,result,0.1);
    }

    @Test
    public void average() {
        double[] arr = new double[4];
        Arrays.fill(arr, 2.0);
        double result = Calculations.average(arr);
        assertEquals(2.0,result,0.1);
    }


    @Test
    public void multiplyMatrix() {
        double[][] arr = new double[2][2];
        Arrays.fill(arr[0],2.0);
        Arrays.fill(arr[1],2.0);
        double[][] arr2 = new double[2][2];
        Arrays.fill(arr2[0], 2.0);
        Arrays.fill(arr2[1], 2.0);
        double[][] result = Calculations.multiplyMatrix(arr,arr2);
        double[][] resultRight= new double[2][2];
        Arrays.fill(resultRight[0],8.0);
        Arrays.fill(resultRight[1],8.0);
        assertEquals(resultRight[1][0],result[1][0],0.1);
    }

    @Test
    public void invert() {
        double[][] arr = new double[2][2];
        arr[0][0]=1.0;
        arr[1][0]=3.0;
        arr[1][1]=4.0;
        arr[0][1]=2.0;
        double[][] result = Calculations.invert(arr);
        assertEquals(1,result[0][1],0.1);
    }


    @Test
    public void matrixTransposta() {
        double[][] arr = new double[2][2];
        Arrays.fill(arr[0],2.0);
        Arrays.fill(arr[1],2.0);
        double[][] result = Calculations.matrixTransposta(arr);
        assertEquals(arr[1][0],result[1][0],0.1);
    }
}