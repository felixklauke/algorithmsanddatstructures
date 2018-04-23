package de.felix_klauke.aad.lib;

public class Link<T> {

    Link<T> naechster;
    private T daten;

    public Link(T daten, Link<T> naechster) {
        this.daten = daten;
        this.naechster = naechster;
    }

    public T getDaten() {
        return daten;
    }

    public void setDaten(T daten) {
        this.daten = daten;
    }

    public String toString() {
        return daten.toString();
    }
}