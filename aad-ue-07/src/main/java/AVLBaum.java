import java.util.Stack;
import java.util.StringJoiner;

public class AVLBaum<T extends Comparable<T>> {

    private AVLKnoten<T> wurzel;
    private boolean hoeheGeaendert;

    // Wird nur f�r grafische Oberfl�che ben�tigt, ohne
    // diese Methode k�nnte die gesamte Implementierung
    // des Baumes geheim gehalten werden. Alle �ffentlichen
    // Methoden sind parameterlos oder besitzen als
    // einzigen Parameter einen Schl�sselwert
    public AVLKnoten<T> getWurzel() {
        return wurzel;
    }

    public boolean istLeer() {
        return (wurzel == null);
    }


// Methoden zum Suchen

    public boolean suchen(final T daten) {
        AVLKnoten<T> currentNode = wurzel;

        while (currentNode != null) {
            int compare = currentNode.getDaten().compareTo(daten);

            if (compare == 0) {
                return true;
            } else {
                currentNode = compare < 0 ? currentNode.getKnotenLinks() : currentNode.getKnotenRechts();
            }
        }

        return false;
    }


// Methoden zum Einf�gen

    public void einfuegen(final T daten) {
        // Setzen der Merker-Variable hoeheGeandert auf false
        // Das wird zwar nach einem Links- oder Rechts-Ausgleich gemacht,
        // aber diese finden nicht statt, wenn ein bereits existierender
        // Schl�ssel wiederholt eingef�gt wird!
        hoeheGeaendert = false;

        // Beim Einf�gen wird der Baum neu zusammengesetzt, um Rotationen
        // zu erm�glichen. Daher tritt hier kein Sonderfall auf, aber die
        // Wurzel muss neu zugewiesen werden.
        wurzel = einfuegenKnoten(daten, wurzel);
    }

    private AVLKnoten<T> einfuegenKnoten(final T daten, AVLKnoten<T> teilbaum) {
        if (teilbaum == null) {
            hoeheGeaendert = true;

            return new AVLKnoten<T>(daten, null, null);
        }

        // Vergleichs-Ergebnis zwischenspeichern, da compareTo()
        // aufw�ndig sein kann, und das Ergebnis mehrfach ben�tigt
        // wird
        final int cmp = daten.compareTo(teilbaum.getDaten());

        if (cmp < 0) {
            // Einzuf�gende Daten sind KLEINER als Daten im aktuellen Knoten
            // und m�ssen daher im LINKEN Teilbaum eingef�gt werden
            teilbaum.setKnotenLinks(einfuegenKnoten(daten, teilbaum.getKnotenLinks()));
            if (hoeheGeaendert) {
                teilbaum = linksAusgleich(teilbaum);
            }
        } else if (cmp > 0) {
            // Einzuf�gende Daten sind GROESSER als Daten im aktuellen Knoten
            // und m�ssen daher im RECHTEN Teilbaum eingef�gt werden
            teilbaum.setKnotenRechts(einfuegenKnoten(daten, teilbaum.getKnotenRechts()));
            if (hoeheGeaendert) {
                teilbaum = rechtsAusgleich(teilbaum);
            }
        }

        return teilbaum;
    }

    private AVLKnoten<T> linksAusgleich(AVLKnoten<T> w) {
        AVLKnoten<T> p;

        switch (w.getBalance()) {
            case +1: {
                w.setBalance(0);                // Der mit w beginnende Teilbaum ist jetzt balanciert
                hoeheGeaendert = false;
                break;
            }

            case 0:                                // Der mit w beginnende Teilbaum ist jetzt linkslastig
            {
                w.setBalance(-1);
                break;
            }

            case -1:                            // Ausgleich notwendig
            {
                p = w.getKnotenLinks();

                if (p.getBalance() == -1)        // Fall 1
                {
                    w = rechtsRotation(w, p);
                } else if (p.getBalance() == +1)    // F�lle 2a und 2b
                {
                    w = lrDoppelRotation(w, p);
                    w.setBalance(0);
                }

                w.setBalance(0);
                hoeheGeaendert = false;
            }
        }

        return w;
    }

    private AVLKnoten<T> rechtsAusgleich(AVLKnoten<T> w) {
        AVLKnoten<T> p;

        switch (w.getBalance()) {
            case -1: {
                w.setBalance(0);                // Der mit w beginnende Teilbaum ist jetzt balanciert
                hoeheGeaendert = false;
                break;
            }

            case 0:                                // Der mit w beginnende Teilbaum ist jetzt rechtslastig
            {
                w.setBalance(+1);
                break;
            }

            case +1:                            // Ausgleich notwendig
            {
                p = w.getKnotenRechts();

                if (p.getBalance() == +1)        // Fall 1
                {
                    w = linksRotation(w, p);
                } else if (p.getBalance() == -1)    // F�lle 2a und 2b
                {
                    w = rlDoppelRotation(w, p);
                }

                w.setBalance(0);
                hoeheGeaendert = false;
            }
        }

        return w;
    }

    private AVLKnoten<T> rechtsRotation(AVLKnoten<T> w, AVLKnoten<T> p) {
        w.setKnotenLinks(p.getKnotenRechts());
        p.setKnotenRechts(w);
        w.setBalance(0);

        return p;
    }

    private AVLKnoten<T> lrDoppelRotation(AVLKnoten<T> w, AVLKnoten<T> p) {
        AVLKnoten<T> q = p.getKnotenRechts();
        p.setKnotenRechts(q.getKnotenLinks());
        q.setKnotenLinks(p);
        w.setKnotenLinks(q.getKnotenRechts());
        q.setKnotenRechts(w);

        switch (q.getBalance()) {
            case -1: {
                w.setBalance(+1);
                p.setBalance(0);
                break;
            }
            case +1:                            // Fall 2b
            {
                w.setBalance(0);
                p.setBalance(-1);
                break;
            }
            case 0:                                // Fall q wird eingef�gt
            {
                w.setBalance(0);
                p.setBalance(0);
            }
        }

        return q;
    }

    private AVLKnoten<T> linksRotation(AVLKnoten<T> w, AVLKnoten<T> p) {
        w.setKnotenRechts(p.getKnotenLinks());
        p.setKnotenLinks(w);
        w.setBalance(0);

        return p;
    }

    private AVLKnoten<T> rlDoppelRotation(AVLKnoten<T> w, AVLKnoten<T> p) {
        AVLKnoten<T> q = p.getKnotenLinks();
        p.setKnotenLinks(q.getKnotenRechts());
        q.setKnotenRechts(p);
        w.setKnotenRechts(q.getKnotenLinks());
        q.setKnotenLinks(w);

        switch (q.getBalance()) {
            case +1: {
                w.setBalance(-1);
                p.setBalance(0);
                break;
            }
            case -1:                            // Fall 2b
            {
                w.setBalance(0);
                p.setBalance(+1);
                break;
            }
            case 0:                                // Fall q wird eingef�gt
            {
                w.setBalance(0);
                p.setBalance(0);
            }
        }

        return q;
    }

    // Pre-Order
    public String traversierePreOrder() {
        AVLKnoten currentNode = wurzel;

        StringJoiner stringJoiner = new StringJoiner(", ");

        Stack<AVLKnoten> nodes = new Stack<>();
        nodes.push(currentNode);

        while (!nodes.empty()) {
            currentNode = nodes.pop();
            stringJoiner.add(currentNode.getDaten().toString());

            AVLKnoten right = currentNode.getKnotenRechts();
            if (right != null) {
                nodes.push(right);
            }

            AVLKnoten left = currentNode.getKnotenLinks();
            if (left != null) {
                nodes.push(left);
            }
        }

        return stringJoiner.toString();
    }
}