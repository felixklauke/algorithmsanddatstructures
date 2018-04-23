package de.felix_klauke.aad.lib;

import java.awt.*;

public class ListeCanvas extends Canvas {

    private Liste eineListe;

    public void zeichneNeu(Liste eineListe) {
        this.eineListe = eineListe;

        repaint();
    }

    public void paint(Graphics g) {
        Link einElement = eineListe.getAnfang();
        Link ende = eineListe.getEnde();
        int x = 0;
        int y = 30;
        int xAktuellerZeiger = -1;

        while (einElement != null) {
            g.setColor(Color.blue);
            g.drawRect(x, y, 30, 20);

            if (einElement == eineListe.aktuellerZeiger()) {
                xAktuellerZeiger = x;
            }

            g.drawString(einElement.toString(), x + 1, y + 19);

            if (einElement.naechster != null) {
                g.setColor(Color.black);
                g.drawLine(x + 30, y + 10, x + 45, y + 10);
                g.drawLine(x + 45, y + 10, x + 40, y + 5);
                g.drawLine(x + 45, y + 10, x + 40, y + 15);
            }

            x = x + 45;
            einElement = einElement.naechster;
        }

        if (!eineListe.weitereElemente()) {
            xAktuellerZeiger = x - 45;
        }

        if (!eineListe.istLeer() && (xAktuellerZeiger != -1)) {
            g.setColor(Color.red);
            g.drawLine(xAktuellerZeiger + 15, y + 25, xAktuellerZeiger + 15, y + 40);
            g.drawLine(xAktuellerZeiger + 15, y + 25, xAktuellerZeiger + 10, y + 30);
            g.drawLine(xAktuellerZeiger + 15, y + 25, xAktuellerZeiger + 20, y + 30);
        }
    }
}