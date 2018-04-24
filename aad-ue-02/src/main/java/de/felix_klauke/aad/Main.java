package de.felix_klauke.aad;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class Main {

    private static final Zeitmessung ZEITMESSUNG = new Zeitmessung();
    private static final StopUhr STOP_UHR = new StopUhr();
    private static int[] measurements = {1, 10, 100, 200, 1000, 10000, 100000};

    public static void main(String[] args) {

        for (int measurement : measurements) {

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
        }
    }
}
