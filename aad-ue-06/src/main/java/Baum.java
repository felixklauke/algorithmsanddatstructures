import java.util.Stack;
import java.util.StringJoiner;

public class Baum<T extends Comparable<T>> {

    private Knoten<T> wurzel;

    // Wird nur f�r grafische Oberfl�che ben�tigt, ohne
    // diese Methode k�nnte die gesamte Implementierung
    // des Baumes geheim gehalten werden. Alle �ffentlichen
    // Methoden sind parameterlos oder besitzen als
    // einzigen Parameter einen Schl�sselwert
    public Knoten<T> getWurzel() {
        return wurzel;
    }

    public boolean istLeer() {
        return (wurzel == null);
    }

    public void attach(Knoten<T> einKnoten) {
        wurzel = einKnoten;
    }


// Methoden zum Suchen

    public boolean suchen(final T daten) {
        return !istLeer() && suchenKnoten(daten, wurzel);
        // Effiziente Kurzform f�r:
        // if (istLeer()) { return false; } else { return suchenKnoten(daten, wurzel); }
    }

    private boolean suchenKnoten(final T daten, Knoten<T> teilbaum) {
        if (teilbaum == null) {
            return false;
        }

        // Vergleichs-Ergebnis zwischenspeichern, da compareTo()
        // aufw�ndig sein kann, und das Ergebnis mehrfach ben�tigt
        // wird
        final int cmp = daten.compareTo(teilbaum.getDaten());

        return (cmp == 0) || suchenKnoten(daten, (cmp < 0) ? teilbaum.getKnotenLinks() : teilbaum.getKnotenRechts());
        // Effiziente Kurzform f�r:
        // if (cmp == 0) { return true; }
        //   else if (cmp < 0) { return suchenKnoten(daten, teilbaum.getKnotenLinks()); }
        //     else { return suchenKnoten(daten, teilbaum.getKnotenRechts()); }
    }


// Methoden zum Einf�gen

    public void einfuegen(final T daten) {
        if (istLeer()) {
            // Sonderfall, analog zu verketteten Listen
            wurzel = new Knoten<T>(daten, null, null);
        } else {
            einfuegenKnoten(daten, wurzel);
        }
    }

    // Generiert einen neuen Knoten mit �bergebenen Daten und f�gt
    // ihn (die Suchbaumeigenschaft erhaltend) als Blatt in den
    // Baum ein, sofern die Daten noch nicht vorhanden sind.
    private void einfuegenKnoten(final T daten, Knoten<T> teilbaum) {
        // Vergleichs-Ergebnis zwischenspeichern, da compareTo()
        // aufw�ndig sein kann, und das Ergebnis mehrfach ben�tigt
        // wird
        final int cmp = daten.compareTo(teilbaum.getDaten());

        // Daten schon vorhanden?
        // Falls ja: alles erledigt!
        if (cmp == 0) {
            return;
        }

        if (cmp < 0) {
            // Einzuf�gende Daten sind KLEINER als Daten im aktuellen Knoten
            // und m�ssen daher im LINKEN Teilbaum eingef�gt werden
            if (teilbaum.getKnotenLinks() == null) {
                // Es gibt keinen linken Teilbaum -> neuen Knoten erzeugen
                teilbaum.setKnotenLinks(new Knoten<T>(daten, null, null));
            } else {
                // Es existiert ein linker Teilbaum -> rekursiv weiter
                einfuegenKnoten(daten, teilbaum.getKnotenLinks());
            }
        } else {
            // Einzuf�gende Daten sind GROESSER als Daten im aktuellen Knoten
            // und m�ssen daher im RECHTEN Teilbaum eingef�gt werden
            if (teilbaum.getKnotenRechts() == null) {
                // Es gibt keinen rechten Teilbaum -> neuen Knoten erzeugen
                teilbaum.setKnotenRechts(new Knoten<T>(daten, null, null));
            } else {
                // Es existiert ein rechter Teilbaum -> rekursiv weiter
                einfuegenKnoten(daten, teilbaum.getKnotenRechts());
            }
        }
    }


// Methoden zum Entfernen

    public void entfernen(final T daten) {
        // Leerer Baum?
        // Falls ja, gibt es nicht zu entfernen!
        if (istLeer()) {
            return;
        }

        // Vergleichs-Ergebnis zwischenspeichern, da compareTo()
        // aufw�ndig sein kann, und das Ergebnis mehrfach ben�tigt
        // wird
        final int cmp = daten.compareTo(wurzel.getDaten());

        if (cmp == 0) {
            // Der Wurzel-Knoten muss entfernt werden!
            // Sonderfall, analog zu verketteten Listen
            entfernenWurzel();
        } else if (cmp < 0) {
            // Zu l�schende Daten kleiner als Daten in Wurzel;
            // im linken Teilbaum weitersuchen falls existent
            if (wurzel.getKnotenLinks() != null) {
                entfernenKnoten(daten, wurzel, wurzel.getKnotenLinks(), true);
            }
        } else {
            // Zu l�schende Daten gr��er als Daten in Wurzel;
            // im rechten Teilbaum weitersuchen falls existent
            if (wurzel.getKnotenRechts() != null) {
                entfernenKnoten(daten, wurzel, wurzel.getKnotenRechts(), false);
            }
        }
    }

    private void entfernenWurzel() {
        if (wurzel.getKnotenLinks() == null) {
            // Wurzel hat h�chstens einen rechten Nachfolger.
            // Der wird zur neuen Wurzel!
            wurzel = wurzel.getKnotenRechts();
        } else if (wurzel.getKnotenRechts() == null) {
            // Wurzel hat h�chstens einen linken Nachfolger.
            // Der wird zur neuen Wurzel!
            wurzel = wurzel.getKnotenLinks();
        } else {
            // Rechter und linker Teilbaum nicht leer; zwei Nachfolger.
            // Wurzel durch gr��ten Knoten im linken Teilbaum ersetzen!
            ersetzeKnoten(wurzel);
        }
    }

    // Sofern �bergebene Daten im Teilbaum vorhanden sind, werden sie gel�scht.
    // Elternknoten wird ben�tigt, da dessen rechter bzw. linker Nachfolger ggf. auf
    // den rechten bzw. linken Nachfolger des zu l�schenden Knotens umgesetzt werden muss
    // Ist linkerTeilbaum == true, wurde der linke Nachfolger des Elternknotens �bergeben,
    // sonst der rechte. Wird ben�tigt, um zu entscheiden, ob der linke oder rechte
    // Nachfolger des Elternknotens ge�ndert werden muss.
    private void entfernenKnoten(final T daten, Knoten<T> elternknoten, Knoten<T> teilbaum, final boolean linkerTeilbaum) {
        // Vergleichs-Ergebnis zwischenspeichern, da compareTo()
        // aufw�ndig sein kann, und das Ergebnis mehrfach ben�tigt
        // wird
        final int cmp = daten.compareTo(teilbaum.getDaten());

        if (cmp == 0) {
            // Der Knoten mit den zu l�schenden Daten wurde gefunden
            if (teilbaum.getKnotenLinks() == null) {
                // Zu l�schender Knoten hat h�chstens einen rechten Nachfolger.
                // Auf diesen vom Elternknoten aus verweisen!
                if (linkerTeilbaum) {
                    elternknoten.setKnotenLinks(teilbaum.getKnotenRechts());
                } else {
                    elternknoten.setKnotenRechts(teilbaum.getKnotenRechts());
                }
            } else if (teilbaum.getKnotenRechts() == null) {
                // Zu l�schender Knoten hat h�chstens einen linken Nachfolger.
                // Auf diesen vom Elternknoten aus verweisen!
                if (linkerTeilbaum) {
                    elternknoten.setKnotenLinks(teilbaum.getKnotenLinks());
                } else {
                    elternknoten.setKnotenRechts(teilbaum.getKnotenLinks());
                }
            } else {
                // Rechter und linker Teilbaum nicht leer; zwei Nachfolger!
                // Zu l�schenden Knoten durch gr��ten Knoten im linken Teilbaum ersetzten
                ersetzeKnoten(teilbaum);
            }
        } else if (cmp < 0) {
            // Zu l�schende Daten kleiner als Daten im aktuellen Knoten;
            // im linken Teilbaum weitersuchen falls existent
            if (teilbaum.getKnotenLinks() != null) {
                entfernenKnoten(daten, teilbaum, teilbaum.getKnotenLinks(), true);
            }
        } else {
            // Zu l�schende Daten gr��er als Daten im aktuellen Knoten;
            // im rechten Teilbaum weitersuchen falls existent
            if (teilbaum.getKnotenRechts() != null) {
                entfernenKnoten(daten, teilbaum, teilbaum.getKnotenRechts(), false);
            }
        }
    }

    // Ersetzt zu l�schenden Knoten durch gr��ten Knoten im linken Teilbaum,
    // indem Daten des gr��ten Knotens in zu l�schenden Knoten kopiert werden.
    // Vom Elternknoten des gr��ten Knotens aus muss auf den linken Teilbaum
    // des gr��ten Knotens verwiesen werden. Der rechten Teilbaum des gr��ten
    // Knotens ist immer leer (Def. gr��ter Knoten)
    private void ersetzeKnoten(Knoten<T> zuLoeschenderKnoten) {
        // Gr��ten Knoten suchen; dessen rechter Nachfolger ist null.
        // Daher kann rechter Nachfolger des zu l�schenden Knotens �bernommen werden.
        Knoten<T> elternknoten = zuLoeschenderKnoten;
        Knoten<T> teilbaum = zuLoeschenderKnoten.getKnotenLinks();
        Knoten<T> groessterKnoten = teilbaum;

        while (teilbaum.getKnotenRechts() != null) {
            elternknoten = teilbaum;
            teilbaum = teilbaum.getKnotenRechts();
            groessterKnoten = teilbaum;
        }

        // Daten des gr��ten Knotens werden in zu l�schenden Knoten kopiert
        zuLoeschenderKnoten.setDaten(groessterKnoten.getDaten());

        if (elternknoten == zuLoeschenderKnoten) // Gr��ter Knoten ist Wurzel des linken Teilbaums des zu L�schenden
        {
            // Zu l�schender Knoten ist gleichzeitig Elternknoten des gr��ten Knotens
            // Rechter Teilbaum des zu l�schenden Knotens muss erhalten bleiben
            // Linken Teilbaum des zu loeschenden Knotens auf linken Teilbaum des gr��ten Knotens setzen
            // Das sind die einzigen Nachfolger des gr��ten Knotens, da dessen rechter Teilbaum ja
            // immer leer ist.
            zuLoeschenderKnoten.setKnotenLinks(groessterKnoten.getKnotenLinks());
        } else {
            // Rechten, freiwerdenden Teilbaum des Elternknotens des gr��ten Knotens
            // auf linken Teilbaum des gr��ten Knotens setzen
            // Das sind die einzigen Nachfolger des gr��ten Knotens, da dessen rechter Teilbaum ja
            // immer leer ist.
            elternknoten.setKnotenRechts(groessterKnoten.getKnotenLinks());
        }
    }


    public String traversierePreOrder() {
        return (wurzel != null) ? traversierePreOrder(wurzel) : "Der Baum ist leer.";
    }

    private String traversierePreOrder(final Knoten<T> einKnoten) {
        Knoten currentNode = einKnoten;

        StringJoiner stringJoiner = new StringJoiner(", ");

        Stack<Knoten> nodes = new Stack<>();
        nodes.push(currentNode);

        while (!nodes.empty()) {
            currentNode = nodes.pop();
            stringJoiner.add(currentNode.getDaten().toString());

            Knoten right = currentNode.getKnotenRechts();
            Knoten left = currentNode.getKnotenLinks();

            if (right != null) {
                nodes.push(right);
            }

            if (left != null) {
                nodes.push(left);
            }
        }

        return stringJoiner.toString();
    }


    // In-Order
    public String traversiereInOrder() {
        return (wurzel != null) ? traversiereInOrder(wurzel) : "Der Baum ist leer.";
    }

    private String traversiereInOrder(final Knoten<T> einKnoten) {
        Knoten currentNode = einKnoten;

        StringJoiner stringJoiner = new StringJoiner(", ");

        Stack<Knoten> nodes = new Stack<>();
        nodes.push(currentNode);

        while (!nodes.empty()) {
            Knoten right = currentNode.getKnotenRechts();
            Knoten left = currentNode.getKnotenLinks();

            if (right != null) {
                nodes.push(right);
            }

            currentNode = nodes.pop();
            stringJoiner.add(currentNode.getDaten().toString());
            if (left != null) {
                nodes.push(left);
            }
        }

        return stringJoiner.toString();
    }


    // Post-Order
    public String traversierePostOrder() {
        return (wurzel != null) ? traversierePostOrder(wurzel) : "Der Baum ist leer.";
    }

    private String traversierePostOrder(final Knoten<T> einKnoten) {
        Knoten currentNode = einKnoten;

        StringJoiner stringJoiner = new StringJoiner(", ");

        Stack<Knoten> nodes = new Stack<>();
        nodes.push(currentNode);

        while (!nodes.empty()) {
            Knoten right = currentNode.getKnotenRechts();
            if (right != null) {
                nodes.push(right);
            }

            Knoten left = currentNode.getKnotenLinks();
            if (left != null) {
                nodes.push(left);
            }

            currentNode = nodes.pop();
            stringJoiner.add(currentNode.getDaten().toString());
        }

        return stringJoiner.toString();
    }
}