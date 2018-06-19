public class Main {

    public static final int INSERTIONSORT = 0;
    public static final int SELECTIONSORT = 1;
    public static final int BUBBLESORT = 2;
    public static final int QUICKSORT = 3;
    public static final int HEAPSORT = 4;

    public static final int ANZAHLALGORITHMEN = 5;
    public static final int N = 10000;

    public static final String[] sortNames =
            {
                    "Insertion Sort",
                    "Selection Sort",
                    "Bubblesort",
                    "Quicksort",
                    "Heapsort"
            };

    // Gibt ein Array mit n Elementen und zuf�lligen Zahlen zur�ck
    public static int[] getRandomArray(final int n) {
        int[] array = new int[n];

        for (int a = 0; a < n; a++) {
            array[a] = (int) (Math.random() * 10 * n);
        }

        return array;
    }

    // Pr�ft, ob ein Array korrekt sortiert ist
    public static boolean checkArray(int[] array) {
        for (int a = 0; a < array.length - 1; a++) {
            if (array[a] > array[a + 1]) {
                return false;
            }
        }

        return true;
    }

    // Pr�ft einen Algorithmus, und gibt die Laufzeiten aus
    public static void checkSortAlgorithmus(final int algorithmus) {
        StopUhr stopUhr = new StopUhr();

        boolean isCorrect = true;
        long minTime = 0;
        long maxTime = 0;
        long sumTime = 0;

        // Wir testen mit 100 zuf�llig erzeugten Feldern
        for (int a = 0; a < 100; a++) {
            int[] array = getRandomArray(N);

            // Zeitmessung beginnen
            stopUhr.start();

            // Array sortieren
            switch (algorithmus) {
                case INSERTIONSORT:
                    BasicSort.insertionsort(array, 0, array.length - 1);
                    break;

                case SELECTIONSORT:
                    BasicSort.selectionsort(array, 0, array.length - 1);
                    break;

                case BUBBLESORT:
                    BasicSort.bubblesort(array, 0, array.length - 1);
                    break;

                case QUICKSORT:
                    QuickSort.quicksort(array, 0, array.length - 1);
                    break;

                case HEAPSORT:
                    HeapSort.heapsort(array, 0, array.length - 1);
                    break;
            }

            // Zeitmessung stoppen
            stopUhr.stop();

            // Ergebnis auswerten
            if (!(isCorrect &= checkArray(array))) {
                break;
            }

            if ((a == 0) || (stopUhr.getDuration() < minTime)) {
                minTime = stopUhr.getDuration();
            }

            if (stopUhr.getDuration() > maxTime) {
                maxTime = stopUhr.getDuration();
            }

            sumTime += stopUhr.getDuration();
        }

        // Ausgabe
        if (isCorrect) {
            System.out.format("%16s %10.4f       %10.4f       %10.4f\n",
                    sortNames[algorithmus], minTime / 1000000.0, sumTime / 100000000.0, maxTime / 1000000.0);
        } else {
            System.out.format("%16s         %10s\n", sortNames[algorithmus], "-");
        }
    }

    public static void main(String[] args) {
        System.out.println("Arraygr��e n = " + N + "\n");
        System.out.println("    Algorithmus                     Laufzeit (ms)");
        System.out.println("                     minimal      durchschnittlich    maximal");

        for (int algorithmus = 0; algorithmus < ANZAHLALGORITHMEN; algorithmus++) {
            checkSortAlgorithmus(algorithmus);
        }
    }
}