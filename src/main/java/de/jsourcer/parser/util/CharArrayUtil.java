package de.jsourcer.parser.util;

import de.jsourcer.parser.misc.Counter;
import de.jsourcer.parser.misc.IndexedCharArray;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

public final class CharArrayUtil {

    private CharArrayUtil() {
    }

    public static boolean startWith(@NotNull IndexedCharArray array, @NotNull String with) {
        AtomicBoolean startWith = new AtomicBoolean(true);
        Counter counter = new Counter(0);
        array.decoupledIndexLoop(-1, (character, integer) -> {
            int index = counter.getCounter();
            if (index >= with.length()) return true;
            if (!character.equals(with.charAt(index))) startWith.set(false);
            counter.increase();
            return !startWith.get();
        });
        return startWith.get();
    }
}
