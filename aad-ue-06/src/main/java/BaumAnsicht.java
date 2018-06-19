import java.awt.*;

public class BaumAnsicht {

    private Baum<Character> einBaum;

    public BaumAnsicht(Baum<Character> einBaum) {
        this.einBaum = einBaum;
    }

    public void ausgeben(int xLinks, int xRechts, int y, Graphics g) {
        g.setColor(new Color(240, 240, 240));
        g.fillRect(xLinks, 100, xRechts, 190);

        ausgebenTeilbaum(einBaum.getWurzel(), -1, -1, xLinks, xRechts, y, g);
    }

    public void ausgebenTeilbaum(Knoten teilbaum, int xParent, int yParent, int xLinks, int xRechts, int y, Graphics g) {
        if (teilbaum != null) {
            final int mitte = (xLinks + xRechts) / 2;

            g.setColor(Color.black);
            g.drawString(teilbaum.toString(), mitte - 2, y);

            if ((xParent != -1) && (yParent != -1)) {
                g.setColor(new Color(192, 192, 192));
                g.drawLine(xParent, yParent, mitte, y - 12);
            }

            ausgebenTeilbaum(teilbaum.getKnotenLinks(), mitte, y + 4, xLinks + 4, mitte - 4, y + 30, g);
            ausgebenTeilbaum(teilbaum.getKnotenRechts(), mitte, y + 4, mitte + 4, xRechts + 4, y + 30, g);
        }
    }
}