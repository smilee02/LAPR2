package app.controller;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BruteForceAlgorithmTest {
    List<Integer> a = new ArrayList<>();
    @Test
    public void bruteForceAlgorithm1() {
        a.add(8);
        a.add(-2);
        a.add(4);
        assertEquals(10,BruteForceAlgorithm.BruteForceAlgorithm1(a));
    }

    @Test
    public void bruteForceAlgorithm2() {
        a.add(8);
        a.add(-2);
        a.add(4);
        assertEquals(a,BruteForceAlgorithm.BruteForceAlgorithm2(a));
    }
}