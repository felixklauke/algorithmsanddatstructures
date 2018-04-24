package de.felix_klauke.aad;

import java.util.Arrays;

public class BesterAlgorithmus {

    private static final double[] EXECUTION_COMPLEXITY = new double[6];

    // 1000n
    public static double g1(int n) {
        return 1000 * n;
    }

    // 100n * log2(n+1)
    public static double g2(int n) {
        return 100 * n * (Math.log10(n + 1) / Math.log10(2));
    }

    // 10 * n * n
    public static double g3(int n) {
        return 10 * n * n;
    }

    // n * n * n
    public static double g4(int n) {
        return n * n * n;
    }

    // 2 hoch n
    public static double g5(int n) {
        return Math.pow(2, n);
    }

    // n!
    public static double g6(int n) {
        double i = 1;

        for (int i1 = 1; i1 < n; i1++) {
            i *= i1;
        }

        return i;
    }

    // Bestimmt fuer alle 6 Funktionen den Wert von
    // gi(n) und gibt den Index der Funktion mit
    // dem minimalen Wert zurueck
    public static int gewinnerFuerN(int n) {
        EXECUTION_COMPLEXITY[0] = g1(n);
        EXECUTION_COMPLEXITY[1] = g2(n);
        EXECUTION_COMPLEXITY[2] = g3(n);
        EXECUTION_COMPLEXITY[3] = g4(n);
        EXECUTION_COMPLEXITY[4] = g5(n);
        EXECUTION_COMPLEXITY[5] = g6(n);

        //System.out.println("Measurment: " + n + " results: " + Arrays.toString(EXECUTION_COMPLEXITY));

        int current = 0;

        for (int i = 0; i < 5; i++) {
            if (EXECUTION_COMPLEXITY[i] < EXECUTION_COMPLEXITY[current]) {
                current = i;
            }
        }

        return current + 1;
    }

    // Gibt f�r jede Zahl n zwischen 1 und 2000 aus, welcher der
    // 6 Algorithmen (A1 .. A6) f�r das betrachtete n der Beste ist
    public static void main(String[] args) {
        int range = 2000;

        int[] results = new int[range - 1];

        for (int i = 1; i < range; i++) {
            int winner = gewinnerFuerN(i);

            results[i - 1] = winner;

            System.out.println("Best algo for " + i + " is g" + winner);
        }

        System.out.println(Arrays.toString(results));

        int lastStart = 0;
        int currentResult = 0;
        for (int i = 0; i < results.length; i++) {
            if (currentResult != results[i] && currentResult != 0 || i == results.length - 1) {
                System.out.println("From " + (lastStart + 1) + " to " + ((i == results.length - 1) ? "end" : (i + " " + currentResult)) + " seems to be the best.");
                lastStart = i;
            }

            currentResult = results[i];
        }
    }
}