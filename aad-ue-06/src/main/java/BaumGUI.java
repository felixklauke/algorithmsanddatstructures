import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class BaumGUI extends Frame {

    private Baum<Character> einBaum;
    private BaumAnsicht eineBaumAnsicht;

    private Label zeichenFuehrungstext = new Label();
    private TextField zeichenTextfeld = new TextField();
    private Button suchenDruckknopf = new Button();
    private Button einfuegenDruckknopf = new Button();
    private Button entfernenDruckknopf = new Button();
    private Button demoDruckknopf = new Button();
    private Checkbox enthaltenKontrollkaestchen = new Checkbox();
    private TextArea ausgabeTextfeld = new TextArea("", 0, 0, TextArea.SCROLLBARS_NONE);
    private Button traversierenDruckknopf = new Button();

    public BaumGUI() {
        setLayout(null);
        setSize(530, 485);
        zeichenFuehrungstext.setText("Zeichen:");
        zeichenFuehrungstext.setAlignment(Label.RIGHT);
        add(zeichenFuehrungstext);
        zeichenFuehrungstext.setBounds(12, 32, 48, 24);
        add(zeichenTextfeld);
        zeichenTextfeld.setBounds(72, 32, 48, 24);
        suchenDruckknopf.setLabel("Suchen");
        add(suchenDruckknopf);
        suchenDruckknopf.setBackground(Color.lightGray);
        suchenDruckknopf.setBounds(132, 32, 84, 24);
        einfuegenDruckknopf.setLabel("Einf�gen");
        add(einfuegenDruckknopf);
        einfuegenDruckknopf.setBackground(Color.lightGray);
        einfuegenDruckknopf.setBounds(228, 32, 84, 24);
        entfernenDruckknopf.setLabel("Entfernen");
        add(entfernenDruckknopf);
        entfernenDruckknopf.setBackground(Color.lightGray);
        entfernenDruckknopf.setBounds(324, 32, 84, 24);
        demoDruckknopf.setLabel("Demo-Baum");
        add(demoDruckknopf);
        demoDruckknopf.setBackground(Color.lightGray);
        demoDruckknopf.setBounds(418, 32, 84, 24);
        enthaltenKontrollkaestchen.setLabel("Im Baum enthalten");
        enthaltenKontrollkaestchen.setEnabled(false);
        add(enthaltenKontrollkaestchen);
        enthaltenKontrollkaestchen.setBounds(132, 68, 132, 24);
        add(ausgabeTextfeld);
        ausgabeTextfeld.setBounds(14, 330, 500, 144);
        traversierenDruckknopf.setLabel("Traversieren");
        add(traversierenDruckknopf);
        traversierenDruckknopf.setBackground(Color.lightGray);
        traversierenDruckknopf.setBounds(14, 300, 127, 26);

        AktionsAbhoerer einAktionsAbhoerer = new AktionsAbhoerer();
        suchenDruckknopf.addActionListener(einAktionsAbhoerer);
        einfuegenDruckknopf.addActionListener(einAktionsAbhoerer);
        entfernenDruckknopf.addActionListener(einAktionsAbhoerer);
        demoDruckknopf.addActionListener(einAktionsAbhoerer);
        TastaturAbhoerer einTastaturAbhoerer = new TastaturAbhoerer();
        zeichenTextfeld.addKeyListener(einTastaturAbhoerer);
        traversierenDruckknopf.addActionListener(einAktionsAbhoerer);

        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent event) {
                        setVisible(false);
                        dispose();
                        System.exit(0);
                    }
                }
        );

        einBaum = new Baum<Character>();
        eineBaumAnsicht = new BaumAnsicht(einBaum);
    }

    public void paint(Graphics g) {
        eineBaumAnsicht.ausgeben(15, 500, 120, g);
    }

    // Innere Klassen
    class AktionsAbhoerer implements java.awt.event.ActionListener {

        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if ((object == einfuegenDruckknopf) || (object == entfernenDruckknopf) || (object == suchenDruckknopf)) {
                String text = zeichenTextfeld.getText();
                if ((text != null) && (text.length() > 0)) {
                    char zeichen = text.charAt(0);

                    if (object == einfuegenDruckknopf) {
                        einBaum.einfuegen(zeichen);
                        zeichenTextfeld.setText("");
                        repaint();
                    } else if (object == entfernenDruckknopf) {
                        einBaum.entfernen(zeichen);
                        repaint();
                    } else if (object == suchenDruckknopf) {
                        enthaltenKontrollkaestchen.setState(einBaum.suchen(zeichen));
                    }
                }
            } else if (object == traversierenDruckknopf) {
                ausgabeTextfeld.setText("Pre-Order:\n");
                ausgabeTextfeld.append(einBaum.traversierePreOrder());
                ausgabeTextfeld.append("\n\nIn-Order:\n");
                ausgabeTextfeld.append(einBaum.traversiereInOrder());
                ausgabeTextfeld.append("\n\nPost-Order:\n");
                ausgabeTextfeld.append(einBaum.traversierePostOrder());
            } else if (object == demoDruckknopf) {
                System.out.println("XXX");
                einBaum.attach(
                        new Knoten<Character>('E',
                                new Knoten<Character>('D',
                                        new Knoten<Character>('A',
                                                null,
                                                null),
                                        null),
                                new Knoten<Character>('S',
                                        new Knoten<Character>('O',
                                                null,
                                                new Knoten<Character>('P',
                                                        null,
                                                        new Knoten<Character>('R',
                                                                new Knoten<Character>('Q',
                                                                        null,
                                                                        null),
                                                                null))),
                                        new Knoten<Character>('Z',
                                                null,
                                                null))));

                repaint();
            }
        }
    }

    class TastaturAbhoerer extends java.awt.event.KeyAdapter {

        public void keyReleased(java.awt.event.KeyEvent event) {
            if (event.getSource() == zeichenTextfeld) {
                String text = zeichenTextfeld.getText();
                if ((text != null) && (text.length() > 0)) {
                    char zeichen = text.charAt(0);

                    if (event.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                        einBaum.einfuegen(zeichen);
                        zeichenTextfeld.setText("");
                        repaint();
                    }
                }
            }
        }
    }
}

