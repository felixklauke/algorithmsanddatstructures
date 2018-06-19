public class Knoten<T> {

    private T daten;
    private Knoten<T> teilbaumLinks;
    private Knoten<T> teilbaumRechts;

    public Knoten(T daten, Knoten<T> teilbaumLinks, Knoten<T> teilbaumRechts) {
        this.daten = daten;
        this.teilbaumLinks = teilbaumLinks;
        this.teilbaumRechts = teilbaumRechts;
    }

    public T getDaten() {
        return daten;
    }

    public void setDaten(T daten) {
        this.daten = daten;
    }

    public Knoten<T> getKnotenLinks() {
        return teilbaumLinks;
    }

    public void setKnotenLinks(Knoten<T> teilbaumLinks) {
        this.teilbaumLinks = teilbaumLinks;
    }

    public Knoten<T> getKnotenRechts() {
        return teilbaumRechts;
    }

    public void setKnotenRechts(Knoten<T> teilbaumRechts) {
        this.teilbaumRechts = teilbaumRechts;
    }

    public String toString() {
        return this.daten.toString();
    }
}