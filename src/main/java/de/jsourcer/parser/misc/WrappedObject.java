package de.jsourcer.parser.misc;

public class WrappedObject<T> {
    private T object;

    public WrappedObject(T object) {
        this.object = object;
    }

    public WrappedObject() {
        this.object = null;
    }

    public boolean isPresent() {
        return object != null;
    }

    public void set(T t) {
        object = t;
    }

    public T get() {
        return object;
    }
}
