import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.StringTokenizer;

public class SortierenGUI extends Frame {

    Label ustapelFuehrungstext;
    TextField ustapelTextfeld;
    Button sortierenDruckknopf;
    Label sstapelFuehrungstext;
    TextField sstapelTextfeld;

    public SortierenGUI() {
        super("Mergesort");

        setLayout(null);
        setSize(470, 190);
        ustapelFuehrungstext = new Label();
        ustapelTextfeld = new TextField();
        sortierenDruckknopf = new Button();
        sstapelFuehrungstext = new Label();
        sstapelTextfeld = new TextField();

        ustapelFuehrungstext.setText("Unsortierter Stapel (Zahlen durch Kommata oder Zwischenraum trennen)");

        add(ustapelFuehrungstext);
        ustapelFuehrungstext.setBounds(24, 42, 432, 24);
        add(ustapelTextfeld);
        ustapelTextfeld.setBounds(24, 66, 420, 24);
        sortierenDruckknopf.setLabel("Sortieren");
        add(sortierenDruckknopf);
        sortierenDruckknopf.setBackground(Color.lightGray);
        sortierenDruckknopf.setBounds(360, 102, 84, 24);
        sstapelFuehrungstext.setText("Sortierter Stapel");
        add(sstapelFuehrungstext);
        sstapelFuehrungstext.setBounds(24, 114, 96, 24);
        sstapelTextfeld.setEditable(false);
        add(sstapelTextfeld);
        sstapelTextfeld.setBounds(24, 138, 420, 24);

        AktionsAbhoerer einAktionsAbhoerer = new AktionsAbhoerer();
        sortierenDruckknopf.addActionListener(einAktionsAbhoerer);

        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent event) {
                                  setVisible(false);
                                  dispose();
                                  System.exit(0);
                              }
                          }
        );
    }

    class AktionsAbhoerer implements java.awt.event.ActionListener {

        public void actionPerformed(java.awt.event.ActionEvent event) {
            String tmpStr = ustapelTextfeld.getText();
            int Laenge = 0;

            StringTokenizer st = new StringTokenizer(tmpStr, " ,;\t");
            int[] StartStapel = new int[1000];
            while (st.hasMoreTokens()) {
                StartStapel[Laenge++] = Integer.valueOf(st.nextToken()).intValue();
            }

            int[] Zielstapel = MergeSort.sortieren(StartStapel, Laenge);
            tmpStr = "";
            for (int a = 0; a < Laenge; a++) {
                if (!tmpStr.isEmpty()) {
                    tmpStr += ", ";
                }

                tmpStr += Zielstapel[a];
            }

            sstapelTextfeld.setText(tmpStr);
        }
    }
}