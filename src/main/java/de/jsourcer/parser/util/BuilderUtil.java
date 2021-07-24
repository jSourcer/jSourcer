package de.jsourcer.parser.util;

public class BuilderUtil {
    private BuilderUtil() {
    }

    public static String getAndClear(StringBuilder builder) {
        String value = builder.toString();
        builder.setLength(0);
        return value;
    }
}
