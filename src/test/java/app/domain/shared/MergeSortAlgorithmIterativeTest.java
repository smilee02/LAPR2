package app.domain.shared;

import org.junit.Test;

import static org.junit.Assert.*;

public class MergeSortAlgorithmIterativeTest {

    @Test
    public void mergeSortName() {
        String[] arr = new String[5];
        arr[0] = "jose";
        arr[1] = "andre";
        arr[2] = "fred";
        arr[3] = "zara";
        arr[4] = "sandra";
        MergeSortAlgorithmIterative.mergeSortName(arr,arr.length);
    }


}