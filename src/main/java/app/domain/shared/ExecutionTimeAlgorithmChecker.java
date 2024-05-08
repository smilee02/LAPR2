package app.domain.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExecutionTimeAlgorithmChecker {
    private static final double[] arr = getTestArray();
    public static double[] getTestArray(){
        Random random = new Random();
        String numbers = "1234567890";
        int n = 10000;
        List<Long> integerList = new ArrayList<>();
        for (int i = 0; i<n;i++){
            String s = "";
            while(s.length()!=10){
                int position = random.nextInt(numbers.length());
                s = s + numbers.charAt(position);
            }
            integerList.add(Long.parseLong(s));
        }
        double[] arr = new double[integerList.size()];
        for (int i = 0;i<integerList.size();i++){
            arr[i] = integerList.get(i);
        }
        return arr;
    }
    public static double bubbleOrderTIN() {
        double startTime1 = System.nanoTime();
        new BubbleSort().bubbleSortTIN(arr);
        double endTime1 = System.nanoTime()-startTime1;
        return endTime1;
    }

    public static double mergeOrderTIN(){
        double startTime1 = System.nanoTime();
        new MergeSortAlgorithmIterative().mergeSort(arr,arr.length);
        double endTime1 = System.nanoTime()-startTime1;
        return endTime1;
    }
}
