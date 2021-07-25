package de.jsourcer.parser.misc;

import org.jetbrains.annotations.Nullable;

public class WrappedObject<T> {
    private T object;

    public WrappedObject(@Nullable T object) {
        this.object = object;
    }

    public WrappedObject() {
        this.object = null;
    }

    public boolean isPresent() {
        return object != null;
    }

    public void set(@Nullable T t) {
        object = t;
    }

    @Nullable
    public T get() {
        return object;
    }
}
