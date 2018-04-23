package de.felix_klauke.aad.lib;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListeGUI extends Frame {

    //Attribute
    Label elementFuehrungstext = new Label();
    Button einfuegenDruckknopf = new Button();
    Button anhaengenDruckknopf = new Button();
    Button entfernenDruckknopf = new Button();
    Button alleEntfernenDruckknopf = new Button();
    Label zeigerFuehrungstext = new Label();
    Button ruecksetzenDruckknopf = new Button();
    Button plus1Druckknopf = new Button();
    TextField meldungsTextbereich = new TextField();
    Button traversierenDruckknopf = new Button();
    TextArea ausgabeTextbereich = new TextArea();
    TextField elementTextfeld = new TextField();
    Button suchenDruckknopf = new Button();

    //Leere Liste anlegen
    private Liste<Integer> eineListe = new Liste<Integer>();
    private ListeCanvas eineZeichenflaeche; //Zeichenfl�che f�r die Animation

    //Operationen
    public ListeGUI() {
        setLayout(null);
        setSize(600, 350);
        elementFuehrungstext.setText("Element");
        add(elementFuehrungstext);
        elementFuehrungstext.setBounds(36, 132, 48, 23);
        einfuegenDruckknopf.setLabel("Einf�gen");
        add(einfuegenDruckknopf);
        einfuegenDruckknopf.setBackground(Color.lightGray);
        einfuegenDruckknopf.setBounds(144, 132, 72, 23);
        anhaengenDruckknopf.setLabel("Anh�ngen");
        add(anhaengenDruckknopf);
        anhaengenDruckknopf.setBackground(Color.lightGray);
        anhaengenDruckknopf.setBounds(228, 132, 60, 23);
        entfernenDruckknopf.setLabel("Entfernen");
        add(entfernenDruckknopf);
        entfernenDruckknopf.setBackground(Color.lightGray);
        entfernenDruckknopf.setBounds(300, 132, 60, 23);
        suchenDruckknopf.setLabel("Suchen");
        add(suchenDruckknopf);
        suchenDruckknopf.setBackground(Color.lightGray);
        suchenDruckknopf.setBounds(372, 132, 60, 23);
        alleEntfernenDruckknopf.setLabel("Alle entfernen");
        add(alleEntfernenDruckknopf);
        alleEntfernenDruckknopf.setBackground(Color.lightGray);
        alleEntfernenDruckknopf.setBounds(444, 132, 100, 23);
        zeigerFuehrungstext.setText("Aktueller Zeiger");
        zeigerFuehrungstext.setAlignment(Label.RIGHT);
        add(zeigerFuehrungstext);
        zeigerFuehrungstext.setBounds(30, 168, 96, 23);
        ruecksetzenDruckknopf.setLabel("R�cksetzen");
        add(ruecksetzenDruckknopf);
        ruecksetzenDruckknopf.setBackground(Color.lightGray);
        ruecksetzenDruckknopf.setBounds(144, 168, 96, 23);
        plus1Druckknopf.setLabel("+1");
        add(plus1Druckknopf);
        plus1Druckknopf.setBackground(Color.lightGray);
        plus1Druckknopf.setBounds(252, 168, 84, 23);
        add(meldungsTextbereich);
        meldungsTextbereich.setBackground(Color.lightGray);
        meldungsTextbereich.setBounds(36, 288, 396, 27);
        traversierenDruckknopf.setLabel("Traversieren");
        add(traversierenDruckknopf);
        traversierenDruckknopf.setBackground(Color.lightGray);
        traversierenDruckknopf.setBounds(348, 204, 84, 23);
        add(ausgabeTextbereich);
        ausgabeTextbereich.setBounds(36, 204, 302, 66);
        add(elementTextfeld);
        elementTextfeld.setBounds(90, 132, 45, 24);
        eineZeichenflaeche = new ListeCanvas();
        eineZeichenflaeche.setBounds(30, 30, 450, 100);
        add(eineZeichenflaeche);
        eineZeichenflaeche.zeichneNeu(eineListe);

        //Registrieren der Ereignisabh�rer
        AktionsAbhoerer einAktionsAbhoerer = new AktionsAbhoerer();
        einfuegenDruckknopf.addActionListener(einAktionsAbhoerer);
        anhaengenDruckknopf.addActionListener(einAktionsAbhoerer);
        entfernenDruckknopf.addActionListener(einAktionsAbhoerer);
        suchenDruckknopf.addActionListener(einAktionsAbhoerer);
        alleEntfernenDruckknopf.addActionListener(einAktionsAbhoerer);
        ruecksetzenDruckknopf.addActionListener(einAktionsAbhoerer);
        plus1Druckknopf.addActionListener(einAktionsAbhoerer);
        traversierenDruckknopf.addActionListener(einAktionsAbhoerer);

        // Test f�r Verkettung zweier Listen
        Liste<Integer> zweiteListe = new Liste<Integer>(); //Leere Liste mit 2 Leerelementen anlegen
        eineListe.anfuegenElement(new Integer(1));
        eineListe.anfuegenElement(new Integer(2));
        zweiteListe.anfuegenElement(new Integer(3));
        zweiteListe.anfuegenElement(new Integer(4));
        eineListe.verketten(zweiteListe);

        eineZeichenflaeche.zeichneNeu(eineListe);

        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
    }

    //Innere Klasse f�r die Ereignisabh�rer
    class AktionsAbhoerer implements ActionListener {

        private void finish() {
            elementTextfeld.setText("");
            eineZeichenflaeche.zeichneNeu(eineListe);
        }

        public void actionPerformed(ActionEvent event) {
            Object o = event.getSource();
            meldungsTextbereich.setText("");

            if (o == einfuegenDruckknopf) {
                try {
                    int m = (Integer.valueOf(elementTextfeld.getText()).intValue());
                    eineListe.einfuegenElement(new Integer(m));
                } catch (NumberFormatException e) {
                    meldungsTextbereich.setText(e.getMessage());
                }

                finish();

                return;
            }

            if (o == anhaengenDruckknopf) {
                try {
                    int m = (Integer.valueOf(elementTextfeld.getText()).intValue());
                    eineListe.anfuegenElement(new Integer(m));
                } catch (NumberFormatException e) {
                    meldungsTextbereich.setText(e.getMessage());
                }

                finish();

                return;
            }

            if (o == entfernenDruckknopf) {
                if (eineListe.entfernenElement() == null) {
                    meldungsTextbereich.setText("Es wurde kein Opfer gefunden!");
                }

                finish();

                return;
            }

            if (o == suchenDruckknopf) {
                try {
                    int m = (Integer.valueOf(elementTextfeld.getText()).intValue());

                    if (!eineListe.suchenElement(Integer.valueOf(elementTextfeld.getText()))) {
                        meldungsTextbereich.setText("Es wurde kein passendes Element gefunden!");
                    }
                } catch (NumberFormatException e) {
                    meldungsTextbereich.setText(e.getMessage());
                }

                finish();

                return;
            }

            if (o == alleEntfernenDruckknopf) {
                try {
                    int m = (Integer.valueOf(elementTextfeld.getText()).intValue());
                    ausgabeTextbereich.setText("" + eineListe.loescheWerte(m));
                } catch (NumberFormatException e) {
                    meldungsTextbereich.setText(e.getMessage());
                }

                finish();

                return;
            }

            if (o == ruecksetzenDruckknopf) {
                eineListe.setzeAktuellerZeigerZurueck();

                finish();

                return;
            }

            if (o == plus1Druckknopf) {
                if (eineListe.naechstesElement() == null) {
                    meldungsTextbereich.setText("Es ist kein weiteres Element vorhanden!");
                }

                finish();

                return;
            }

            if (o == traversierenDruckknopf) {
                ausgabeTextbereich.setText("");

                ListeIterator<Integer> e = eineListe.iterator();
                while (e.hasNext()) {
                    ausgabeTextbereich.append(e.next() + " ");
                }

                finish();

                return;
            }

            finish();
        }
    }
}