package app.domain.shared;

import app.domain.model.Client;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BubbleSortTest {

    @Test
    public void bubbleSortTIN() {
        double[] arr = new double[3];
        arr[0] = 2;
        arr[1] = 2;
        arr[2] = 2;
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSortTIN(arr);
        double[] arr1 = new double[3];
        arr1[0] = 5;
        arr1[1] = 4;
        arr1[2] = 3;
        bubbleSort.bubbleSortTIN(arr1);
    }

    @Test
    public void bubbleSortName() {
        String[] arr = new String[3];
        arr[0] = "A";
        arr[1] = "A";
        arr[2] = "A";
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSortName(arr);
        String[] arr1 = new String[3];
        arr1[0] = "F";
        arr1[1] = "E";
        arr1[2] = "D";
        bubbleSort.bubbleSortName(arr1);
        List<Client> a = new ArrayList<>();
        bubbleSort.orderClients(a,true);
        bubbleSort.orderClients(a,false);
    }
}