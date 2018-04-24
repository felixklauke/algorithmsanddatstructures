package de.felix_klauke.aad;

public class Zeitmessung {

    private double tuwas() {
        return Math.random();
    }

    // Linear
    public double func1(int n) {
        double summe = 0;

        for (int a = 0; a < n; a++) {
            summe += tuwas();
        }

        return summe;
    }

    // Quadratisch
    public double func2(int n) {
        double summe = 0;

        for (int a = 0; a < n; a++) {
            for (int b = 0; b < n; b++) {
                summe += tuwas();
            }
        }

        return summe;
    }

    // log 2(n)
    public double func6(int n) {
        double summe = 0;

        while (n > 0) {
            summe += tuwas();
            n /= 2;
        }

        return summe;
    }
}
