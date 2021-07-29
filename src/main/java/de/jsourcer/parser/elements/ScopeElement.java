package de.jsourcer.parser.elements;

import de.jsourcer.parser.misc.FunctionalCharArray;
import de.jsourcer.parser.misc.counter.CharacterCounter;
import org.jetbrains.annotations.NotNull;

public class ScopeElement extends AbstractElement {

    private final FunctionalCharArray bodyArray;

    public ScopeElement(@NotNull String value, @NotNull FunctionalCharArray bodyArray) {
        super(value);
        this.bodyArray = bodyArray;
    }

    @NotNull
    public static ScopeElement resolve(@NotNull FunctionalCharArray array) {
        int startIndex = array.getIndex();
        CharacterCounter.charArrayLoopCount(1, '{', '}', array, true);
        return new ScopeElement(String.valueOf(array.getChars(), startIndex, array.getIndex()-startIndex+1),
            new FunctionalCharArray(array.getChars(), startIndex - 1, array.getIndex()));
    }

    @NotNull
    public FunctionalCharArray getBodyArray() {
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
