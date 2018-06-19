public class AVLKnoten<T> {

    private T daten;
    private AVLKnoten<T> teilbaumLinks;
    private AVLKnoten<T> teilbaumRechts;
    private int balance;    // -1 linkslastig; 0 ausgeglichen; +1 rechtslastig

    public AVLKnoten(T daten, AVLKnoten<T> teilbaumLinks, AVLKnoten<T> teilbaumRechts) {
        this.daten = daten;
        this.teilbaumLinks = teilbaumLinks;
        this.teilbaumRechts = teilbaumRechts;
        this.balance = 0;
    }

    public T getDaten() {
        return daten;
    }

    public void setDaten(T daten) {
        this.daten = daten;
    }

    public AVLKnoten<T> getKnotenLinks() {
        return teilbaumLinks;
    }

    public void setKnotenLinks(AVLKnoten<T> teilbaumLinks) {
        this.teilbaumLinks = teilbaumLinks;
    }

    public AVLKnoten<T> getKnotenRechts() {
        return teilbaumRechts;
    }

    public void setKnotenRechts(AVLKnoten<T> teilbaumRechts) {
        this.teilbaumRechts = teilbaumRechts;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String toString() {
        return this.daten.toString();
    }
}