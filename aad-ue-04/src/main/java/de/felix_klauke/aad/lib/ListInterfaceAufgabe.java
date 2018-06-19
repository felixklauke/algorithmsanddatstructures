package de.felix_klauke.aad.lib;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class ListInterfaceAufgabe {

    // Elemente in Liste einf�gen
    static void fillList(List<String> list) {
        // Zahlen von 0 bis 20 als Zeichenketten (Strings) einf�gen
        for (int a = 0; a <= 20; a++) {
            list.add("" + a);
        }

        // Element an der Position 3 entfernen
        list.remove(3);

        // Erstes Element in der Liste entfernen, das gleich "6" ist
        list.remove("6");
    }

    // Liste vom Anfang bis zum Ende mit einer
    // foreach-Schleife iterieren und Elemente ausgeben
    static void printList(List<String> list) {
        for (String element: list) {
            System.out.println(element + ", ");
        }


        String formatted = list.stream().collect(Collectors.joining(", "));
        System.out.println(formatted);
    }

    // Alle Elemente aus der Liste entfernen, die durch 5 teilbar sind
    static void remove5List(List<String> list) {
        ListIterator<String> iterator = list.listIterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            int current = Integer.parseInt(next);

            if (current % 5 != 0) {
                continue;
            }

            iterator.remove();
        }
    }

    public static void main(String[] args) {
        // Erzeugen der LinkedList
        LinkedList<String> list1 = new LinkedList<String>();
        fillList(list1);
        System.out.println("\nAusgabe der ersten Liste(list1)");
        printList(list1);

        remove5List(list1);
        System.out.println("\nlist1 nach dem Entfernen der durch 5 teilbaren Zahlen");
        printList(list1);

        // Erzeugen der ArrayList
        ArrayList<String> list2 = new ArrayList<String>();
        fillList(list2);

        // Sublist from 7 to 13
        List<String> subList = list2.subList(7, 13);
        printList(subList);

        // Remove element "11"
        subList.remove("11");

        printList(subList);
        printList(list2);

        System.out.println("\nAusgabe der zweiten Liste(list2)");
        printList(list2);

        // TODO
    }
}