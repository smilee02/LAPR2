package app.domain.shared;

import app.domain.model.Client;
import app.domain.store.ClientStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BubbleSort implements SortingAlgorithm {



    void bubbleSortTIN(double[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[j]
                    double temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    void bubbleSortName(String[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j].compareTo(arr[j+1])>0)
                {
                    // swap arr[j+1] and arr[j]
                    String temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    @Override
    public List<Client> orderClients(List<Client> clients, boolean tin) {
        List<Client> clientList;
        clientList = ClientStore.getClients();
        if (tin){
            double[] array = new double[clientList.size()];
            for (int j = 0; j< array.length ;j++){
                array[j] = clientList.get(j).getTin();
            }
            List<Client> clientsOrdered = new ArrayList<>();
            bubbleSortTIN(array);
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
        bubbleSortName(array);
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
