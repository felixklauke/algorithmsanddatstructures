package de.felix_klauke.aad.lib;

import java.util.Iterator;

public class ListeIterator<T> implements Iterator {

    private Link<T> aktuellerLink;
    private Link<T> ende;

    ListeIterator(Link<T> start, Link<T> ende) {
        aktuellerLink = start;
        this.ende = ende;
    }

    public boolean hasNext() {
        return aktuellerLink != null;
    }

    public T next() {
        T daten = aktuellerLink.getDaten();
        aktuellerLink = aktuellerLink.naechster;

        return daten;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}