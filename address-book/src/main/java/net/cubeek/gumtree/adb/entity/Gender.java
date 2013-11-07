package net.cubeek.gumtree.adb.entity;

/**
 * Address book entity gender.
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public enum Gender {

    MALE("Male", "M"),
    FEMALE("Female", "F");

    private final String key;

    private final String arg;

    private Gender(String key, String arg) {
        this.key = key;
        this.arg = arg;
    }

    public static Gender findByKey(String key) {
        for (Gender gender : values()) {
            if (gender.key.equals(key))
                return gender;
        }
        throw new IllegalArgumentException("Unknown key: " + key);
    }

    public static Gender findByArg(String arg) {
        for (Gender gender : values()) {
            if (gender.arg.equals(arg))
                return gender;
        }
        return null;
    }
}
