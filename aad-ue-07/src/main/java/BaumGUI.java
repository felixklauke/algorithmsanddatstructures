import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class BaumGUI extends Frame {

    private AVLBaum<Character> einBaum;
    private BaumAnsicht eineBaumAnsicht;

    private Label zeichenFuehrungstext = new Label();
    private TextField zeichenTextfeld = new TextField();
    private Button einfuegenDruckknopf = new Button();
    private Button suchenDruckknopf = new Button();
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
        einfuegenDruckknopf.setLabel("Einf�gen");
        add(einfuegenDruckknopf);
        einfuegenDruckknopf.setBackground(Color.lightGray);
        einfuegenDruckknopf.setBounds(228, 32, 84, 24);
        suchenDruckknopf.setLabel("Suchen");
        add(suchenDruckknopf);
        suchenDruckknopf.setBackground(Color.lightGray);
        suchenDruckknopf.setBounds(132, 32, 84, 24);
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
        einfuegenDruckknopf.addActionListener(einAktionsAbhoerer);
        suchenDruckknopf.addActionListener(einAktionsAbhoerer);
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

        einBaum = new AVLBaum<Character>();
        eineBaumAnsicht = new BaumAnsicht(einBaum);
    }

    public void paint(Graphics g) {
        eineBaumAnsicht.ausgeben(15, 500, 120, g);
    }

    // Innere Klassen
    class AktionsAbhoerer implements java.awt.event.ActionListener {

        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if ((object == einfuegenDruckknopf) || (object == suchenDruckknopf)) {
                String text = zeichenTextfeld.getText();
                if ((text != null) && (text.length() > 0)) {
                    char zeichen = text.charAt(0);

                    if (object == einfuegenDruckknopf) {
                        einBaum.einfuegen(zeichen);
                        zeichenTextfeld.setText("");
                        repaint();
                    } else if (object == suchenDruckknopf) {
                        enthaltenKontrollkaestchen.setState(einBaum.suchen(zeichen));
                    }
                }
            } else if (object == traversierenDruckknopf) {
                ausgabeTextfeld.setText("Pre-Order:\n" + einBaum.traversierePreOrder());
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

