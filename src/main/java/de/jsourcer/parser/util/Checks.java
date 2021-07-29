package de.jsourcer.parser.util;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Checks {

    private Checks() {
    }

    public static <T> void notNull(@Nullable T object, @NotNull String name) {
        Objects.requireNonNull(object, name + " can not be null!");
    }

    public static <T> void noneNull(@NotNull Iterable<T> objects, @NotNull String collectionName) {
        notNull(objects, "objects");
        for (T object : objects) {
            notNull(object, "An element of " + collectionName);
        }
    }
}
