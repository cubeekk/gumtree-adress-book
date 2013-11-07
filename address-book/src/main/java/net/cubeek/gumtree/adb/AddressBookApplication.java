package net.cubeek.gumtree.adb;

import net.cubeek.gumtree.adb.dao.InitializationException;
import net.cubeek.gumtree.adb.dao.PersonNotFoundException;
import net.cubeek.gumtree.adb.entity.Gender;
import net.cubeek.gumtree.adb.entity.Person;
import net.cubeek.gumtree.adb.service.AdbService;
import net.cubeek.gumtree.adb.service.AdbServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Main Address Book application entry-point.
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public class AddressBookApplication {

    private AdbService service;

    public static void main(String[] args) {
        if (args.length != 1) {
            logError(USAGE);
        }

        new AddressBookApplication(args[0]);
    }

    public AddressBookApplication(@NotNull final String fileName) {
        try {
            service = new AdbServiceImpl(new FileInputStream(new File(fileName)));

            // Answer Q1
            printCountGender(Gender.MALE);
            // Answer Q2
            printOldestPerson();
            // Answer Q3
            printOlderThan("Bill McKnight", "Paul Robinson");
        } catch (FileNotFoundException e) {
            logError("Source DB file not found! " + fileName);
        } catch (InitializationException e) {
            logError("Error occurred while trying to initialize DB!");
        }
    }

    /**
     * Print gender count
     * @param gender gender
     */
    private void printCountGender(final Gender gender) {
        System.out.println(String.format("Count of males in DB:  %d", service.getCountByGender(gender)));
    }

    /**
     * Print name of the oldest person
     */
    private void printOldestPerson() {
        final Person oldestPerson = service.getOldestPerson();
        if (oldestPerson != null)
            System.out.println(String.format(" Oldest person in DB:  %s", oldestPerson.getName()));
    }

    /**
     * Print who's older (or younger or the same age) information
     *
     * @param firstPersonName first person's name
     * @param secondPersonName second person's name
     */
    private void printOlderThan(String firstPersonName, String secondPersonName) {
        try {
            final int daysOlder = service.getDaysOlder(firstPersonName, secondPersonName);
            if (daysOlder == 0) {
                System.out.println(String.format("\n%s and %s are the same age.", firstPersonName, secondPersonName));
            } else {
                System.out.println(String.format("\n%s is %d days %s than %s.",
                        firstPersonName,
                        Math.abs(daysOlder),
                        (daysOlder > 0) ? "older" : "younger",
                        secondPersonName));
            }
        } catch (PersonNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Log an error to std err and exit
     *
     * @param message error message
     */
    private static void logError(final String message) {
        System.err.println(message);
        System.exit(1);
    }

    private static final String USAGE = "Usage: java -jar address-book.jar <path to db file>";
}
