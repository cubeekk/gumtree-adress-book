package net.cubeek.gumtree.adb.service;

import net.cubeek.gumtree.adb.dao.PersonNotFoundException;
import net.cubeek.gumtree.adb.entity.Gender;
import net.cubeek.gumtree.adb.entity.Person;
import org.jetbrains.annotations.Nullable;

/**
 * A service class for executing operations over the Address book DB.
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public interface AdbService {

    /**
     * Count person entities by gender
     *
     * @param gender gender filter, <tt>null</tt> can be used for all
     * @return number of people of the specified gender
     */
    int getCountByGender(@Nullable Gender gender);

    /**
     * Get the oldest person from the address book, <tt>null</tt> if DB is empty
     *
     * @return the oldest {@link net.cubeek.gumtree.adb.entity.Person}
     */
    @Nullable
    Person getOldestPerson();

    /**
     * Return how older p1 is than p2
     *
     * @param firstName name of the 1st person
     * @param secondName name of the 2nd person
     *
     * @return a number of days of which the first person is older over the other
     */
    int getDaysOlder(String firstName, String secondName) throws PersonNotFoundException;

}
