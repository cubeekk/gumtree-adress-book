package net.cubeek.gumtree.adb.dao;

/**
 * Person was not found
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public class PersonNotFoundException extends Exception {

    /**
     * Person was not found, while looking up by name
     *
     * @param name person name
     */
    public PersonNotFoundException(String name) {
        super("Person not found: " + name);
    }
}
