package net.cubeek.gumtree.adb;

import net.cubeek.gumtree.adb.dao.InitializationException;
import net.cubeek.gumtree.adb.dao.PersonNotFoundException;
import net.cubeek.gumtree.adb.entity.Gender;
import net.cubeek.gumtree.adb.entity.Person;
import net.cubeek.gumtree.adb.service.AdbService;
import net.cubeek.gumtree.adb.service.AdbServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    public AddressBookApplication(@NotNull final File dataFile) {
        init(dataFile);
        printOldestPerson();
    }

    public AddressBookApplication(@NotNull final File dataFile, @Nullable final Gender gender) {
        init(dataFile);
        printCountGender(gender);
    }

    public AddressBookApplication(@NotNull final File dataFile, @Nullable final String name1, @Nullable final String name2) {
        init(dataFile);
        printOlderThan(name1, name2);
    }

    public static void main(String[] args) {
        if (args.length < 2) logError(USAGE);

        final File dataFile = new File(args[0]);
        if (!dataFile.exists()) {
            logError(USAGE);
        }

        int option = -1;
        try {
            option = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            // nop
        }

        switch (option) {
            case 1:
                if (args.length != 3) logError(USAGE);
                new AddressBookApplication(dataFile, Gender.findByArg(args[2]));
                break;
            case 2:
                new AddressBookApplication(dataFile);
                break;
            case 3:
                if (args.length != 4) logError(USAGE);
                new AddressBookApplication(dataFile, args[2], args[3]);
                break;
            default:
                logError(USAGE);
        }
    }

    /**
     * Initialize service
     *
     * @param dataFile db data file
     */
    private void init(@NotNull final File dataFile) {
        try {
            service = new AdbServiceImpl(new FileInputStream(dataFile));
        } catch (FileNotFoundException e) {
            logError("Source DB file not found! " + dataFile.getAbsolutePath());
        } catch (InitializationException e) {
            logError("Error occurred while trying to initialize DB!");
        }
    }

    /**
     * Print gender count
     *
     * @param gender gender
     */
    private void printCountGender(final Gender gender) {
        System.out.println(String.format("Count of %s in DB:  %d", gender.getKey(), service.getCountByGender(gender)));
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

    private static final String USAGE = "Usage: java -jar address-book.jar <path to db file> <action> [aditional parameters for actions]\n\n" +
            "actions: 1 - count by gender                 parameters: <gender - M or F>\n" +
            "         2 - show oldest person              parameters: none\n" +
            "         3 - compare age for 2 people        parameters: <first person name> <second person name>";
}
