package net.cubeek.gumtree.adb.service;

import net.cubeek.gumtree.adb.dao.AdbDaoStreamImpl;
import net.cubeek.gumtree.adb.entity.Gender;
import net.cubeek.gumtree.adb.entity.Person;
import org.jetbrains.annotations.Nullable;

/**
 * Address book service implementation.
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public class AdbServiceImpl implements AdbService {

    public AdbServiceImpl(String dbFilePath) {
    }

    public AdbServiceImpl(AdbDaoStreamImpl adbDaoStream) {
    }

    @Override
    public int getCountByGender(@Nullable Gender gender) {
        return 0;
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
