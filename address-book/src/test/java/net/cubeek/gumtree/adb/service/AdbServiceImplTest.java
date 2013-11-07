package net.cubeek.gumtree.adb.service;

import net.cubeek.gumtree.adb.dao.AdbDaoStreamImpl;
import net.cubeek.gumtree.adb.entity.Gender;
import net.cubeek.gumtree.adb.entity.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import static net.cubeek.gumtree.adb.data.MockData.*;

/**
 * {@link AdbServiceImpl} test.
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public class AdbServiceImplTest {

    private AdbService service;

    @Before
    public void setUp() throws Exception {
        service = new AdbServiceImpl(getMockDataStream());
    }
    @Test
    public void testGetCountByGender() throws Exception {
        final int countMale = service.getCountByGender(Gender.MALE);
        assertEquals("Unexpected count for male", 3, countMale);

        final int countFemale = service.getCountByGender(Gender.FEMALE);
        assertEquals("Unexpected count for female", 2, countFemale);

        final int countAll = service.getCountByGender(null);
        assertEquals("Unexpected count for all", 5, countAll);
    }

    @Test
    public void testGetOldestPerson() throws Exception {
        final Person oldest = service.getOldestPerson();

        assertNotNull("Person shouldn't be null!", oldest);
        assertEquals("Name doesn't match", NAME_OLDEST, oldest.getName());
        assertEquals("Dob doesn't match", DOB_OLDEST, oldest.getDob());
    }

    @Test
    public void testGetDaysOlder_older() throws Exception {
        final int days = service.getDaysOlder(NAME_OLDEST, NAME_M);
        assertTrue("Days for older person should be higher than 0", 0 < days);
        assertEquals("Wrong number of days evaluated!", 945, days);
    }

    @Test
    public void testGetDaysOlder_younger() throws Exception {
        final int days = service.getDaysOlder(NAME_M, NAME_OLDEST);
        assertTrue("Days for younger person should be lesser than 0", 0 > days);
        assertEquals("Wrong number of days evaluated!", 945, days);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDaysOlder_invalid() {
        service.getDaysOlder(null, NAME_OLDEST);
    }
}
