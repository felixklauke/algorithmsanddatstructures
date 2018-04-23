package de.felix_klauke.aad;

import de.felix_klauke.aad.lib.Link;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class LinkBootstrap {

    public static void main(String[] args) {
        Link<String> aktuellesElement = new Link<String>("Test", null);

        aktuellesElement.naechster = new Link<String>("Letzter", null);

        Link<String> anfangLink = new Link<>("Anfang", aktuellesElement);
    }
}
