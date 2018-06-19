package de.felix_klauke.aad;

import de.felix_klauke.aad.lib.Stack;

public class StackTest {

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<Integer>(100);

        for (int a = 1; a <= 9; a++) {
            st.push(a);
        }

        while (!st.isEmpty()) {
            System.out.println(st.top());
            st.pop();
        }
    }
}