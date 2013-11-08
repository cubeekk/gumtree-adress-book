package net.cubeek.gumtree.adb.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @NotNull
    public static Gender findByKey(@Nullable String key) {
        for (Gender gender : values()) {
            if (gender.key.equals(key))
                return gender;
        }
        throw new IllegalArgumentException("Unknown key: " + key);
    }

    @Nullable
    public static Gender findByArg(@Nullable String arg) {
        for (Gender gender : values()) {
            if (gender.arg.equals(arg))
                return gender;
        }
        return null;
    }

    @NotNull
    public String getKey() {
        return key;
    }
}
