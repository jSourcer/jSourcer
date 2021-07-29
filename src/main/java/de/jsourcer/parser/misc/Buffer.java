package de.jsourcer.parser.misc;

import de.jsourcer.parser.util.Checks;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Buffer<T> implements Iterable<T>{

    private final List<T> buffer = new ArrayList<>();

    public boolean isValid(int index) {
        return index >= 0 & index < buffer.size();
    }

    public int size() {
        return buffer.size();
    }

    @NotNull
    public T get(int index) {
        return buffer.get(index);
    }

    public void add(@NotNull T t) {
        Checks.notNull(t, "t");
        buffer.add(t);
    }

    public void clear() {
        buffer.clear();
    }

    @Nullable
    public T getLatest() {
        int index = buffer.size() - 1;
        if (!isValid(index)) {
            return null;
        }
        return buffer.get(index);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return buffer.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        buffer.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return buffer.spliterator();
    }

    @Override
    public String toString() {
        return "Buffer{" +
            "buffer=" + buffer +
            '}';
    }
}
