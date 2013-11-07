package net.cubeek.gumtree.adb.service;

import net.cubeek.gumtree.adb.dao.AdbDao;
import net.cubeek.gumtree.adb.dao.AdbDaoStreamImpl;
import net.cubeek.gumtree.adb.entity.Gender;
import net.cubeek.gumtree.adb.entity.Person;
import org.jetbrains.annotations.Nullable;

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
        return null;
    }

    @Override
    public int getDaysOlder(String firstName, String secondName) {
        return 0;
    }

}
