package app.domain.shared;

import app.domain.model.Client;
import app.domain.store.ClientStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MergeSortAlgorithmIterative implements SortingAlgorithm {

    /**
     * MergeSort Iterative Sorting algorithm that sorts from arr[0..n-1]
     * @param arr array to be sorted
     * @param size size of the array to be sorted
     */
    static void mergeSort(double[] arr, int size) {

        // For current size of subarrays to
        // be merged the current_size_of_array varies from
        // 1 to n/2
        int current_size_of_array;

        // For picking starting index of
        // left subarray to be merged
        int left_start_index;


         /*
         Merge subarrays from bottom to up.
         First merges subarrays
         of size 1 to create sorted
         subarrays of size 2, then merge
         subarrays of size 2 to create
         sorted subarrays of size 4, and
         so on.
         */

        for (current_size_of_array = 1; current_size_of_array <= size - 1;
             current_size_of_array = 2 * current_size_of_array) {

            // Pick starting point of different
            // subarrays of current size
            for (left_start_index = 0; left_start_index < size - 1;
                 left_start_index += 2 * current_size_of_array) {
                // Find ending point of left
                // subarray (mid_point)
                // mid_point + 1 is the starting
                // point of right subarray
                int mid_point = Math.min(left_start_index + current_size_of_array - 1, size - 1);

                int right_index_of_array = Math.min(left_start_index
                        + 2 * current_size_of_array - 1, size - 1);

                // Merge subarrays arr[left_start...mid]
                // & arr[mid+1...right_end] (left and right array)
                merge(arr, left_start_index, mid_point, right_index_of_array);
            }
        }
    }

    /**
     * Merges the two subarray from left_start_index to mid_point and from mid_point+1 to right_index_of_array
     * @param arr
     * @param left_start_index starting index of the left subarray to be merged
     * @param mid_point ending index of the left subarray
     * @param right_index_of_array ending index of the right subarray to be merged
     */
    static void merge(double[] arr, int left_start_index, int mid_point, int right_index_of_array) {
        int i, j, k;
        int aux1 = mid_point - left_start_index + 1;
        int aux2 = right_index_of_array - mid_point;

        /*
          Creates auxiliary arrays
         */
        double[] subarrayLeft = new double[aux1];
        double[] subarrayRight = new double[aux2];

        /*
          Fills the arrays with their respective content
          The subarrayLeft is filled with the numbers from left_start_index to mid_point
          The subarrayRight is filled with the numbers from mid_point+1 to right_index_of_array
         */
        for (i = 0; i < aux1; i++)
            subarrayLeft[i] = arr[left_start_index + i];
        for (j = 0; j < aux2; j++)
            subarrayRight[j] = arr[mid_point + 1 + j];

        /* Merge the temp arrays back into
        arr[left_start_index...right_index_of_array]*/
        i = 0;
        j = 0;
        k = left_start_index;
        while (i < aux1 && j < aux2) {
            if (subarrayLeft[i] <= subarrayRight[j]) {
                arr[k] = subarrayLeft[i];
                i++;
            } else {
                arr[k] = subarrayRight[j];
                j++;
            }
            k++;
        }

        /* Copy the remaining elements of
        subarrayLeft[], if there are any */
        while (i < aux1) {
            arr[k] = subarrayLeft[i];
            i++;
            k++;
        }

        /* Copy the remaining elements of
        subarrayRight[], if there are any */
        while (j < aux2) {
            arr[k] = subarrayRight[j];
            j++;
            k++;
        }
    }

    /**
     * MergeSort Iterative Sorting algorithm that sorts from arr[0..n-1]
     * @param arr array to be sorted
     * @param size size of the array to be sorted
     */
    static void mergeSortName(String[] arr, int size) {

        // For current size of subarrays to
        // be merged the current_size_of_array varies from
        // 1 to n/2
        int current_size_of_array;

        // For picking starting index of
        // left subarray to be merged
        int left_start_index;


         /*
         Merge subarrays from bottom to up.
         First merges subarrays
         of size 1 to create sorted
         subarrays of size 2, then merge
         subarrays of size 2 to create
         sorted subarrays of size 4, and
         so on.
         */

        for (current_size_of_array = 1; current_size_of_array <= size - 1;
             current_size_of_array = 2 * current_size_of_array) {

            // Pick starting point of different
            // subarrays of current size
            for (left_start_index = 0; left_start_index < size - 1;
                 left_start_index += 2 * current_size_of_array) {
                // Find ending point of left
                // subarray (mid_point)
                // mid_point + 1 is the starting
                // point of right subarray
                int mid_point = Math.min(left_start_index + current_size_of_array - 1, size - 1);

                int right_index_of_array = Math.min(left_start_index
                        + 2 * current_size_of_array - 1, size - 1);

                // Merge subarrays arr[left_start...mid]
                // & arr[mid+1...right_end] (left and right array)
                mergeName(arr, left_start_index, mid_point, right_index_of_array);
            }
        }
    }

    /**
     * Merges the two subarray from left_start_index to mid_point and from mid_point+1 to right_index_of_array
     * @param arr
     * @param left_start_index starting index of the left subarray to be merged
     * @param mid_point ending index of the left subarray
     * @param right_index_of_array ending index of the right subarray to be merged
     */
    static void mergeName(String[] arr, int left_start_index, int mid_point, int right_index_of_array) {
        int i, j, k;
        int aux1 = mid_point - left_start_index + 1;
        int aux2 = right_index_of_array - mid_point;

        /*
          Creates auxiliary arrays
         */
        String [] subarrayLeft = new String[aux1];
        String[] subarrayRight = new String[aux2];

        /*
          Fills the arrays with their respective content
          The subarrayLeft is filled with the numbers from left_start_index to mid_point
          The subarrayRight is filled with the numbers from mid_point+1 to right_index_of_array
         */
        for (i = 0; i < aux1; i++)
            subarrayLeft[i] = arr[left_start_index + i];
        for (j = 0; j < aux2; j++)
            subarrayRight[j] = arr[mid_point + 1 + j];

        /* Merge the temp arrays back into
        arr[left_start_index...right_index_of_array]*/
        i = 0;
        j = 0;
        k = left_start_index;
        while (i < aux1 && j < aux2) {
            if (subarrayLeft[i].compareTo(subarrayRight[j]) <=0) {
                arr[k] = subarrayLeft[i];
                i++;
            } else {
                arr[k] = subarrayRight[j];
                j++;
            }
            k++;
        }

        /* Copy the remaining elements of
        subarrayLeft[], if there are any */
        while (i < aux1) {
            arr[k] = subarrayLeft[i];
            i++;
            k++;
        }

        /* Copy the remaining elements of
        subarrayRight[], if there are any */
        while (j < aux2) {
            arr[k] = subarrayRight[j];
            j++;
            k++;
        }
    }

    public List<Client> orderClients(List<Client> clients, boolean tin) {
        List<Client> clientList;
        clientList = ClientStore.getClients();
        if (tin){
            double[] array = new double[clientList.size()];
            for (int j = 0; j< array.length ;j++){
                array[j] = clientList.get(j).getTin();
            }
            List<Client> clientsOrdered = new ArrayList<>();
            mergeSort(array,array.length);
            for (int i=0;i<array.length;i++){
                for (Client x : clientList){
                    if (x.getTin() == (array[i])){
                        clientsOrdered.add(x);
                    }
                }
            }
            return clientsOrdered;
        }else{
            String[] array = new String[clientList.size()];
            for (int i = 0; i< array.length ;i++){
                array[i] = clientList.get(i).getName();
            }
            List<Client> orderedClients = new ArrayList<>();
            mergeSortName(array,array.length);
            for (int i=0;i<array.length;i++){
                for (Client x : clientList){
                    if (x.getName().equals(array[i])){
                        orderedClients.add(x);
                    }
                }
            }
            return orderedClients;
        }


    }

}
