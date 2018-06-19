public class MergeSort {

    public static int[] sortieren(int[] arrayA, final int laenge) {
        int langeArray = laenge;

        // Erzeuge einen Hilfsarray der gleichen L�nge
        int[] arrayB = new int[langeArray];

        // L�nge der zu sortierenden Teilarrays (zu Beginn 1, d.h. sortiertes Array besteht aus einem Element)
        int laengeTeilfolge = 1;

        // Ggf. k�rzere L�nge des ersten oder zweiten Arrays
        int laengeTeilfolge1 = 1;
        int laengeTeilfolge2 = 1;

        //Schleifendurchlaufz�hler
        int aktDurchlaufAussen = 1;
        int aktDurchlaufInnen = 1;

        // Anfang des ersten zu sortierenden Teilarrays
        int anfang = 0;

        // Richtung des Mischens (von A nach B oder umgekehrt)
        boolean vonAnachB = true;

        // Aufteilung eines Arrays der L�nge n in n Arrays der L�nge 1
        // Beim Initialisieren der Variablen bereits erfolgt!
        while (laengeTeilfolge1 <= langeArray) {
            System.out.println(aktDurchlaufAussen + ". Durchlauf der �u�eren Schleife");

            // Bei jedem vollst�ndigen Mischen des Arrays verdoppelt sich
            // die L�nge der sortierten Teilfolgen
            laengeTeilfolge *= 2;

            // Mischdurchlauf aller Teilarray des Arrays
            anfang = 0;

            while (anfang < langeArray) {
                System.out.println("   " + aktDurchlaufInnen + ".  Durchlauf der inneren Schleife: ");

                // Berechnung der beiden L�ngen
                // Sonderf�lle, wenn die Arrayl�nge keine Zweierpotenz ist!
                if ((anfang + laengeTeilfolge) > langeArray) {
                    if ((langeArray - anfang) > (laengeTeilfolge / 2)) {
                        // Es bleibt zweites Array
                        laengeTeilfolge1 = laengeTeilfolge / 2;
                        laengeTeilfolge2 = langeArray - anfang - laengeTeilfolge1;
                    } else {
                        // Es gibt kein 2. Array
                        laengeTeilfolge1 = langeArray - anfang;
                        laengeTeilfolge2 = 0;
                    }
                }

                // Mischen der sortierten Teilfolgen
                if (vonAnachB) {
                    // Von Array A nach Array B
                    merge(arrayA, arrayB, anfang, laengeTeilfolge1, laengeTeilfolge2);
                    ausgeben(arrayB, langeArray);
                } else {
                    // Von Array B nach Array A
                    merge(arrayB, arrayA, anfang, laengeTeilfolge1, laengeTeilfolge2);
                    ausgeben(arrayA, langeArray);
                }

                // Der Anfang der n�chsten Teilfolge liegt um die
                // aktuelle Teilfolgenl�nge hoeher
                anfang += laengeTeilfolge;
                aktDurchlaufInnen++;
            }

            // Nach einem vollst�ndigen Mischdurchlauf durch den array
            // ist entweder arrayA nach arrayB oder arrayB nach arrayA
            // gemischt worden, so dass im n�chsten Durchlauf Quelle und Ziel
            // vertauscht werden m�ssen. Die L�nge der Teilfolgen verdoppelt sich.
            vonAnachB = !vonAnachB;
            laengeTeilfolge1 = laengeTeilfolge2 = laengeTeilfolge;
            aktDurchlaufAussen++;
        }

        return vonAnachB ? arrayA : arrayB;
        // Effizinte Kurzform f�r:
        // if (vonAnachB) { return arrayA; } else { return arrayB; }
    }

    // Mischen der sortierten Teilarrays aus arrayLinksRechts nach arrayMitte
    // Das erste Teilarray beginnt in arrayLinksRechts bei anfang und besitzt die L�nge laengeLinks.
    // Das zweite Teilarray beginnt in arrayLinksRechts ab (anfang+laengeLinks) und besitzt die L�nge laengeRechts.
    // Vorbedingung: es gibt zwei bereits vorsortierte Arrays!
    private static void merge(int[] arrayLinksRechts, int[] arrayMitte, final int anfang, final int laengeLinks, final int laengeRechts) {
        int obenlinks = anfang;
        int obenmitte = anfang;
        int obenrechts = anfang + laengeLinks;

        // Linkes Array nicht leer und rechtes Array nicht leer
        while ((obenlinks < (anfang + laengeLinks)) && (obenrechts < (anfang + laengeLinks + laengeRechts))) {
            arrayMitte[obenmitte++] = (arrayLinksRechts[obenlinks] < arrayLinksRechts[obenrechts]) ? arrayLinksRechts[obenlinks++] : arrayLinksRechts[obenrechts++];
        }
        // Effiziente Kurzform f�r:
        // if (arrayLinksRechts[obenlinks] < arrayLinksRechts[obenrechts])
        // { arrayMitte[obenmitte++] = arrayLinksRechts[obenlinks++]; }
        // else
        // { arrayMitte[obenmitte++] = arrayLinksRechts[obenrechts++]; }

        // Kopiere das ganze verbleibende Restarray
        if (obenlinks < (anfang + laengeLinks)) {
            for (int a = obenlinks; a < (anfang + laengeLinks); a++) {
                arrayMitte[obenmitte++] = arrayLinksRechts[a];
            }
        } else {
            for (int a = obenrechts; a < (anfang + laengeLinks + laengeRechts); a++) {
                arrayMitte[obenmitte++] = arrayLinksRechts[a];
            }
        }
    }

    private static void ausgeben(int[] array, int laenge) {
        System.out.print("        ");

        for (int a = 0; a < laenge; a++) {
            System.out.print(array[a] + " ");
        }

        System.out.println();
    }
}