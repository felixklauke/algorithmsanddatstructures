package de.felix_klauke.aad.lib;// Abstrakte Datenstruktur Stack, realisiert als Java-Interface

interface StackI<E> {

    void push(E element);

    void pop();

    E top();

    boolean isEmpty();
}
