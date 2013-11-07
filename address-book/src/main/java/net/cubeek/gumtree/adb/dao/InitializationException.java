package net.cubeek.gumtree.adb.dao;

/**
 * DB initialization exception.
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public class InitializationException extends Exception {

    public InitializationException(String message) {
        super(message);
    }
}
