package de.felix_klauke.aad;

import de.felix_klauke.aad.lib.StopUhr;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class AutoCloseableStopUhr extends StopUhr implements AutoCloseable {

    private final String funcName;

    public AutoCloseableStopUhr(String funcName) {
        this.funcName = funcName;
        start();
    }

    @Override
    public void close() {
        stop();

        System.out.println(funcName + " took " + getDuration());
    }
}
