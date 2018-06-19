package de.felixklauke.aad;

import de.felix_klauke.aad.AutoCloseableStopUhr;

import java.util.stream.Stream;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class Fibonacci {

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.calculateFibonacciCool(7));

        try (AutoCloseableStopUhr stopUhr = new AutoCloseableStopUhr("iterative fibonacci")) {
            System.out.println(fibonacci.calculateFibonacciDumbIterative(40));
        }

        try (AutoCloseableStopUhr stopUhr = new AutoCloseableStopUhr("recursive fibonacci")) {
            System.out.println(fibonacci.calculateFibonacciDumbRecursive(40).getValue());
        }
    }

    public long calculateFibonacciCool(int number) {
        return Stream.iterate(new Pair<>(0, 1), current -> new Pair<>(current.getValue(), current.getKey() + current.getValue()))
                .skip(number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Can't calculate fibonacci element " + number)).getKey();
    }

    public long calculateFibonacciDumbIterative(int number) {
        int currentFibonacci = 0;

        int head = 1;
        int tail = 1;

        for (int i = 0; i < number; i++) {
            currentFibonacci = head;
            head = tail;
            tail = currentFibonacci + head;
        }

        return currentFibonacci;
    }

    public Pair<Long, Long> calculateFibonacciDumbRecursive(long number) {
        if (number <= 1) {
            return new Pair<>(0L, number);
        } else {
            Pair<Long, Long> current = calculateFibonacciDumbRecursive(number - 1);
            return new Pair<>(current.getValue(), current.getValue() + current.getKey());
        }
    }

    /*
        Complexity of n

        O(n - 1) + O(n - 2) [+ add them]
     */

    private class Pair<KeyType, ValueType> {

        private final KeyType key;
        private final ValueType value;

        private Pair(KeyType key, ValueType value) {
            this.key = key;
            this.value = value;
        }

        public KeyType getKey() {
            return key;
        }

        public ValueType getValue() {
            return value;
        }
    }
}
