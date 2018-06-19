package de.felixklauke.aad;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(ulam(20));
    }

    public static long ulam(int n) {
        System.out.println("ULAM(" + n + ")");

        if (n == 1) {
            return n;
        }

        if (n % 2 == 0) {
            return ulam(n / 2);
        }

        return ulam(3 * n + 1);
    }
}
