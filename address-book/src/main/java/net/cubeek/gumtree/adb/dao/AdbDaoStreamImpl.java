package net.cubeek.gumtree.adb.dao;

import net.cubeek.gumtree.adb.entity.Person;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Address book dao that reads data from a flat, comma separated file.
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public class AdbDaoStreamImpl implements AdbDao {

    /**
     * Create Dao and initialize the database
     *
     * @param is database {@link java.io.InputStream}
     */
    public AdbDaoStreamImpl(@NotNull InputStream is) {
    }

    @NotNull
    @Override
    public List<Person> findAll() {
        return new ArrayList<Person>();
    }

    @Nullable
    @Override
    public Person findByName(@Nullable String name) {
        return null;
    }

}
