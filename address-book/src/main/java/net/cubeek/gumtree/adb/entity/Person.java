package net.cubeek.gumtree.adb.entity;

import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;

/**
 * Address book entry.
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public class Person {

    @NotNull
    private String name;

    @NotNull
    private Gender gender;

    @NotNull
    private LocalDate dob;

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        Validate.notNull(name, "Cannot assign null name to Person");
        this.name = name;
    }

    @NotNull
    public Gender getGender() {
        return gender;
    }

    public void setGender(@NotNull Gender gender) {
        Validate.notNull(gender, "Cannot assign null gender to Person");
        this.gender = gender;
    }

    @NotNull
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(@NotNull LocalDate dob) {
        Validate.notNull(dob, "Cannot assign null dob to Person");
        this.dob = dob;
    }

    @Override
    @NotNull
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!dob.equals(person.dob)) return false;
        if (gender != person.gender) return false;
        if (!name.equals(person.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + dob.hashCode();
        return result;
    }
}
