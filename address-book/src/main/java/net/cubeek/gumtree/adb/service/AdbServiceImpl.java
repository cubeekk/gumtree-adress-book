package net.cubeek.gumtree.adb.service;

import net.cubeek.gumtree.adb.dao.AdbDao;
import net.cubeek.gumtree.adb.dao.AdbDaoStreamImpl;
import net.cubeek.gumtree.adb.dao.PersonNotFoundException;
import net.cubeek.gumtree.adb.entity.Gender;
import net.cubeek.gumtree.adb.entity.Person;
import org.jetbrains.annotations.Nullable;
import org.joda.time.Days;

import java.io.InputStream;

/**
 * Address book service implementation.
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public class AdbServiceImpl implements AdbService {

    private final AdbDao dao;

    public AdbServiceImpl(InputStream data) {
        dao = new AdbDaoStreamImpl(data);
    }

    @Override
    public int getCountByGender(@Nullable Gender gender) {
        if (gender == null)
            return dao.findAll().size();

        int count = 0;
        for (Person person : dao.findAll()) {
            if (gender.equals(person.getGender())) {
                count += 1;
            }
        }
        return count;
    }

    @Nullable
    @Override
    public Person getOldestPerson() {
        Person result = null;

        for (Person person : dao.findAll()) {
            if (result == null || person.getDob().isBefore(result.getDob())) {
                result = person;
            }
        }

        return result;
    }

    @Override
    public int getDaysOlder(String firstName, String secondName) throws PersonNotFoundException {
        final Person first = dao.findByName(firstName);
        final Person second = dao.findByName(secondName);

        final Days days = Days.daysBetween(first.getDob(), second.getDob());

        return days.getDays();
    }

}
