package de.jsourcer.parser.util;

import de.jsourcer.parser.misc.FunctionalCharArray;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;

public final class CharArrayUtil {

    private CharArrayUtil() {
    }

    public static boolean startWith(@NotNull FunctionalCharArray array, @NotNull String with) {
        AtomicBoolean startWith = new AtomicBoolean(true);
        int startIndex = array.getIndex();
        array.decoupledArray().modifiedIndexLoop(-1, (character, integer) -> {
            int index = integer - startIndex;
            if (index >= with.length()) {
                return true;
            }
            if (!character.equals(with.charAt(index))) {
                startWith.set(false);
            }
            return !startWith.get();
        });
        return startWith.get();
    }
}
