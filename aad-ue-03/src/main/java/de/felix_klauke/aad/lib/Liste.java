package de.felix_klauke.aad.lib;

import java.util.NoSuchElementException;

public class Liste<T> {

    protected Link<T> anfang;
    protected Link<T> ende;

    // Vorg�nger von AktuellerZeiger
    protected Link<T> vorgaengerAktuellerZeiger;

    public Liste() {
        // Leere Liste: alle Zeiger sind null (Standardwerte)
    }

    public T naechstesElement() throws NoSuchElementException {
        if (vorgaengerAktuellerZeiger == ende) {
            return null;
        } else if (vorgaengerAktuellerZeiger == null) {
            vorgaengerAktuellerZeiger = anfang;
        } else {
            vorgaengerAktuellerZeiger = vorgaengerAktuellerZeiger.naechster;
        }

        return vorgaengerAktuellerZeiger.getDaten();
    }

    // Zur�cksetzen des aktuellen Zeigers auf den Listenanfang
    public void setzeAktuellerZeigerZurueck() {
        vorgaengerAktuellerZeiger = null;
    }

    // Einf�gen vor die aktuelle Zeigerposition
    public void einfuegenElement(T neuesElement) {
        // Wenn die Liste leer ist, entspricht dies einem Anf�gen
        if (istLeer()) {
            anfuegenElement(neuesElement);

            return;
        }

        Link<T> neu = new Link<T>(neuesElement, aktuellerZeiger());

        // Wenn die Liste nur ein Element hat, muss anfang gesetzt werden
        if (vorgaengerAktuellerZeiger == null) {
            anfang = vorgaengerAktuellerZeiger = neu;

            return;
        }

        // Vorg�nger-Element von neu zeigt jetzt auf neu
        vorgaengerAktuellerZeiger = vorgaengerAktuellerZeiger.naechster = neu;
    }

    // Anf�gen am Ende der Liste
    public void anfuegenElement(T neuesElement) {
        // Neuen Link anlegen
        Link<T> neu = new Link<T>(neuesElement, null);

        // Wenn die Liste leer ist, m�ssen anfang und ende gesetzt werden
        if (istLeer()) {
            // Neuen Link als anfang der Liste
            anfang = neu;
        } else {
            // Anf�gen des Elements an das bisherige Ende
            ende.naechster = neu;    // Ende-Element zeigt jetzt auf neu
        }

        // Neues Ende
        ende = neu;
    }

    public T entfernenElement() {
        // Liste leer?
        if (istLeer()) {
            return null;
        }

        Link<T> opfer = aktuellerZeiger();

        // Ist der aktuelle Zeiger �ber das Listenende hinausgewandert?
        if (opfer == null) {
            return null;
        }

        // Zeiger am Anfang der Liste
        if (vorgaengerAktuellerZeiger == null) {
            anfang = anfang.naechster;
        } else {
            vorgaengerAktuellerZeiger.naechster = opfer.naechster;
        }

        // Zeiger am Ende der Liste
        if (opfer == ende) {
            ende = vorgaengerAktuellerZeiger;
            vorgaengerAktuellerZeiger = null;
        }

        return opfer.getDaten();
    }

    // Liefert true, wenn Element in der Liste vorkommt, sonst false.
    // Wenn true zur�ckgegeben wurde, kann mit naechstesElement() auf Element
    // zugegriffen werden, wenn nicht steht aktuellerZeiger am Ende der Liste.
    public boolean suchenElement(T element) {
        Link<T> zeiger = anfang;
        Link<T> backupZeiger = null;

        // Zeiger auf Listenanfang setzen
        vorgaengerAktuellerZeiger = null;

        // Liste leer?
        if (zeiger == null) {
            return false;
        }

        // Abfrage auf Gleichheit ist m�glich mit equals (Operation von der Klasse Object)
        while ((zeiger != null) && !zeiger.getDaten().equals(element)) {
            backupZeiger = vorgaengerAktuellerZeiger;
            vorgaengerAktuellerZeiger = zeiger;

            zeiger = zeiger.naechster;
        }

        // Abfrage auf Ungleichheit
        if (zeiger == null) {
            // Zeiger eins zur�cksetzen
            vorgaengerAktuellerZeiger = backupZeiger;

            return false;
        }

        return true;
    }

    public ListeIterator<T> iterator() {
        return new ListeIterator<T>(anfang, ende);
    }

    // Aktuellen Zeiger aus dem gespeicherten vorgaengerAkuellerZeiger ableiten
    public Link<T> aktuellerZeiger() {
        return istLeer() ? null : (vorgaengerAktuellerZeiger == null) ? anfang : vorgaengerAktuellerZeiger.naechster;
        // Sonderfallbehandlung; effiziente Kurzform f�r:
        // if (istLeer()) return null;
        // if (vorgaengerAktuellerZeiger == null) return anfang;
        // return vorgaengerAktuellerZeiger.naechster;
    }

    // true, wenn AktuellerZeiger nicht am Ende der Liste ist
    public boolean weitereElemente() {
        return !istLeer() && aktuellerZeiger() != ende;
        // Effiziente Kurzform f�r:
        // if (istLeer()) { return false; } else { return aktuellerZeiger() != ende; }
    }

    public Link<T> getAnfang() {
        return anfang;
    }

    public Link<T> getEnde() {
        return ende;
    }

    // Pr�fen, ob Liste leer ist
    public boolean istLeer() {
        return anfang == null;
    }

    public void verketten(Liste<T> zweiteListe) {
        Liste<T> result = new Liste<>();
        result.anfang = anfang;
        ende = zweiteListe.anfang;
        result.ende = zweiteListe.ende;
    }

    public int loescheWerte(T victim) {
        int anzGeloeschte = 0;

        Link<T> vorgaenger = null;
        Link<T> naechster = anfang;
        while (naechster != null) {
            if (naechster.getDaten().equals(victim)) {
                if (naechster.equals(anfang)) {
                    anfang = naechster.naechster;
                    continue;
                }

                if (vorgaenger == null) {
                    throw new IllegalStateException();
                }

                vorgaenger.setNaechster(naechster.naechster);
            }

            vorgaenger = naechster;
            naechster = naechster.naechster;
        }

        return anzGeloeschte;
    }
}