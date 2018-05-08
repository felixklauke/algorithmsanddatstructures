package de.felix_klauke.aad;

import de.felix_klauke.aad.lib.Zeitmessung;

import java.util.Arrays;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class Main {

    private static final Zeitmessung ZEITMESSUNG = new Zeitmessung();
    private static int[] measurements = {1, 10, 100, 200, 1000, 10000, 100000};

    public static void main(String[] args) {
        Arrays.stream(measurements).forEach(measurement -> {
            System.out.println("Starting measurements for " + measurement);

            try (AutoCloseableStopUhr ignored = new AutoCloseableStopUhr("func1")) {
                ZEITMESSUNG.func1(measurement);
            }
            try (AutoCloseableStopUhr ignored = new AutoCloseableStopUhr("func2")) {
                ZEITMESSUNG.func2(measurement);
            }
            try (AutoCloseableStopUhr ignored = new AutoCloseableStopUhr("func6")) {
                ZEITMESSUNG.func6(measurement);
            }
        });
    }
}
