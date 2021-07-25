package de.jsourcer.parser.util;

import org.jetbrains.annotations.NotNull;

public final class BuilderUtil {

    private BuilderUtil() {
    }

    public static String getAndClear(@NotNull StringBuilder builder) {
        String value = builder.toString();
        builder.setLength(0);
        return value;
    }
}
