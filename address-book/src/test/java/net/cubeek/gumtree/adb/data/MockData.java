package net.cubeek.gumtree.adb.data;

import org.joda.time.LocalDate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Mock data for the {@link net.cubeek.gumtree.adb.dao.AdbDaoStreamImplTest}
 *
 * @author Jakub Stonavsky &lt;stonavsky&#64;cubeek.net&gt;
 */
public final class MockData {

    private static final String DATA;

    public static final String NAME_NA = "Unknown Person";


    public static final String NAME_M = "Bill McKnight";

    public static final LocalDate DOB_M = LocalDate.parse("1977-03-16");


    public static final String NAME_F = "Gemma Lane";

    public static final LocalDate DOB_F = LocalDate.parse("1991-11-20");

    static {
        final StringBuilder sb = new StringBuilder();
        sb.append(NAME_M).append(", Male, 16/03/77").append("\n");
        sb.append("Paul Robinson, Male, 15/01/85").append("\n");
        sb.append(NAME_F).append(", Female, 20/11/91").append("\n");
        sb.append("Sarah Stone, Female, 20/09/80").append("\n");
        sb.append("Wes Jackson, Male, 14/08/74").append("\n");
        DATA = sb.toString();
    }

    public static InputStream getMockDataStream() {
        return new ByteArrayInputStream(DATA.getBytes());
    }

}
