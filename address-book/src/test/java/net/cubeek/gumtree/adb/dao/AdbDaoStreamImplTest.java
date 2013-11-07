package net.cubeek.gumtree.adb.dao;

import net.cubeek.gumtree.adb.entity.Gender;
import net.cubeek.gumtree.adb.entity.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static net.cubeek.gumtree.adb.data.MockData.*;

/**
 * {@link AdbDaoStreamImpl} test.
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public class AdbDaoStreamImplTest {

    private AdbDao dao;

    @Before
    public void setUp() throws Exception {
        dao = new AdbDaoStreamImpl(getMockDataStream());
    }

    @Test
    public void testFindAll() throws Exception {
        final List<Person> entries = dao.findAll();
        assertNotNull("Retrieved entries cannot be null!", entries);
        assertEquals("Retrieved entries should contain 5 records!", 5, entries.size());
    }

    @Test
    public void testFindByName_correctName() throws Exception {
        final Person personMale = dao.findByName(NAME_M);

        assertNotNull("Retrieved entry was null!", personMale);
        assertEquals("Retrieved entry name does not match!", NAME_M, personMale.getName());
        assertEquals("Retrieved entry gender does not match!", Gender.MALE, personMale.getGender());
        assertEquals("Retrieved entry dob does not match!", DOB_M, personMale.getDob());

        final Person personFemale = dao.findByName(NAME_F);
        assertNotNull("Retrieved entry was null!", personFemale);
        assertEquals("Retrieved entry name does not match!", NAME_F, personFemale.getName());
        assertEquals("Retrieved entry gender does not match!", Gender.FEMALE, personFemale.getGender());
        assertEquals("Retrieved entry dob does not match!", DOB_F, personFemale.getDob());
    }

    @Test(expected = PersonNotFoundException.class)
    public void testFindByName_wrongName() throws Exception {
        dao.findByName(NAME_NA);
    }

    @Test(expected = PersonNotFoundException.class)
    public void testFindByName_null() throws Exception {
        dao.findByName(null);
    }

}
