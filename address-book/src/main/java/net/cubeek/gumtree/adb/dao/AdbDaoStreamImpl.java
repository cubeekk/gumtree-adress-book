package net.cubeek.gumtree.adb.dao;

import net.cubeek.gumtree.adb.entity.Gender;
import net.cubeek.gumtree.adb.entity.Person;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static net.cubeek.gumtree.adb.dao.AdbDaoStreamConstants.*;

/**
 * Address book dao that reads data from a flat, comma separated file.
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public class AdbDaoStreamImpl implements AdbDao {

    private final List<Person> entries;

    /**
     * Create Dao and initialize the database
     *
     * @param data database {@link InputStream}
     */
    public AdbDaoStreamImpl(@NotNull InputStream data) {
        Validate.notNull(data, "Address book InputStream cannot be null!");

        this.entries = new ArrayList<Person>();

        reload(data);
    }

    @NotNull
    @Override
    public List<Person> findAll() {
        return entries;
    }

    @Nullable
    @Override
    public Person findByName(@Nullable final String name) {
        if (name != null) {
            for (Person person : entries) {
                if (person.getName().equals(name))
                    return person;
            }
        }
        return null;
    }

    /**
     * Reload {@link AdbDaoStreamImpl#entries} from {@link InputStream}
     *
     * @param data the stream
     */
    private void reload(InputStream data) {
        entries.clear();

        final Scanner scanner = new Scanner(data);
        while (scanner.hasNextLine()) {
            entries.add(parseEntryLine(scanner.nextLine()));
        }
    }

    /**
     * Parse the {@link net.cubeek.gumtree.adb.entity.Person} object from a comma separated data line
     *
     * @param line data line
     * @return {@link net.cubeek.gumtree.adb.entity.Person} instance
     */
    private Person parseEntryLine(String line) {
        final String data[] = line.split(",");

        if (data == null || !(data.length == 3))
            throw new IllegalArgumentException("Incorrect data format found for line: " + line);

        final Person person = new Person();
        person.setName(data[NAME].trim());
        person.setGender(Gender.findByKey(data[GENDER].trim()));
        person.setDob(LocalDate.parse(data[DOB].trim(), DateTimeFormat.forPattern(PATTERN_DOB)));

        return person;
    }

}
