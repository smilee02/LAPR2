package app.controller;

import java.util.ArrayList;
import java.util.List;

public class BruteForceAlgorithm {
    public static int BruteForceAlgorithm1(List<Integer> a) {
        int somamaxima = Integer.MIN_VALUE, valoresdoarray = 0;
        for (int i = 0; i < a.size(); i++) {
            valoresdoarray = valoresdoarray + a.get(i);
            if (somamaxima < valoresdoarray) {
                somamaxima = valoresdoarray;
            }
            if (valoresdoarray < 0) {
                valoresdoarray = 0;
            }
        }
        return somamaxima;
    }

    public static List<Integer> BruteForceAlgorithm2(List<Integer> a) {
        int somamaxima = Integer.MIN_VALUE, valoresdoarray = 0, valormaximo = Integer.MIN_VALUE, start = 0, end = 0, s = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > valormaximo) {
                valormaximo = a.get(i);
            }
        }
        List<Integer> valoresdasoma = new ArrayList<>(a.size());
        for (int i = 0; i < a.size(); i++) {
            valoresdoarray = valoresdoarray + a.get(i);
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

                valoresdasoma.add(a.get(j));
            }
        }

        return valoresdasoma;
    }


}