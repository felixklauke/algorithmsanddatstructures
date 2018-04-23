package de.felix_klauke.aad;

import de.felix_klauke.aad.lib.Liste;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class ListBootstrap {

    public static void main(String[] args) {

        // null -> null
        Liste<String> eineListe = new Liste<>();

        // "Pascal" -> null
        eineListe.anfuegenElement("Pascal");

        // "Pascal" -> "Java" -> null
        eineListe.einfuegenElement("Java");

        // "Pascal" -> null
        String s = eineListe.entfernenElement();
    }
}
