package net.cubeek.gumtree.adb.entity;

/**
 * Address book entity gender.
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public enum Gender {

    MALE("Male"),
    FEMALE("Female");

    private final String key;

    private Gender(String key) {
        this.key = key;
    }

    public static Gender findByKey(String key) {
        for (Gender gender : values()) {
            if (gender.key.equals(key))
                return gender;
        }
        throw new IllegalArgumentException("Unknown key: " + key);
    }

}
