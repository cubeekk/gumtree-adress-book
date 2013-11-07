package net.cubeek.gumtree.adb.dao;

import net.cubeek.gumtree.adb.entity.Person;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Data access interface for Address book application.
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public interface AdbDao {

    /**
     * Find all entities available in db
     *
     * @return a list of entities
     */
    @NotNull
    List<Person> findAll();

    /**
     * Find an entry from the db by a given name
     *
     * @param name name for query
     * @return the entry found or <tt>null</tt> if not available
     */
    @NotNull
    Person findByName(@Nullable final String name) throws PersonNotFoundException;

}
