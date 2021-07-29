package de.jsourcer.parser.elements;

import de.jsourcer.parser.misc.FunctionalCharArray;
import de.jsourcer.parser.misc.counter.CharacterCounter;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class GenericsElement extends AbstractElement{

    private final String[] generics;

    public GenericsElement(@NotNull String value, String[] generics) {
        super(value);
        this.generics = generics;
    }

    //TODO: parse generics with '&'
    public static AbstractElement parse(@NotNull FunctionalCharArray charArray) {
        charArray.startStringBuilder();
        CharacterCounter.charArrayLoopCount(1, '<', '>', charArray, false);
        String value = charArray.stopAndGetBuilder();
        return new GenericsElement("<" + value + ">", Arrays
            .stream(value.split(","))
            .map(String::trim)
            .toArray(String[]::new));
    }

    @NotNull
    public String[] getGenerics() {
        return generics;
    }

}
