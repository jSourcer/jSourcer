package de.jsourcer.parser.elements;

import de.jsourcer.parser.misc.Counter;
import de.jsourcer.parser.misc.IndexedCharArray;
import de.jsourcer.parser.util.CharSequenceSkipper;
import org.jetbrains.annotations.NotNull;

public class ScopeElement extends AbstractElement {
    private final IndexedCharArray bodyArray;

    public ScopeElement(@NotNull String value, @NotNull IndexedCharArray bodyArray) {
        super(value);
        this.bodyArray = bodyArray;
    }

    @NotNull
    public static ScopeElement resolve(@NotNull IndexedCharArray array) {
        Counter counter = new Counter(1);
        int startIndex = array.getIndex();
        array.indexLoop((character, integer) -> {
            if (character == '"') CharSequenceSkipper.skipString(array);
            if (character == '\'') CharSequenceSkipper.skipCharacter(array);

            if (character == '{') {
                counter.increase();
            } else if (character == '}') {
                counter.decrease();
            }
            return counter.isZero();
        });
        IndexedCharArray bodyArray = new IndexedCharArray(array.getChars(), startIndex, array.getIndex());
        return new ScopeElement(String.valueOf(array.getChars(), startIndex, array.expectedNextIndex() - startIndex), bodyArray);
    }

    @NotNull
    public IndexedCharArray getBodyArray() {
        return bodyArray;
    }

    @Override
    public String toString() {
        return "ScopeElement{" +
                "value='" + value + '\'' +
                ", bodyArray=" + bodyArray +
                '}';
    }
}
