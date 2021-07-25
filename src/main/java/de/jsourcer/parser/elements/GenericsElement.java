package de.jsourcer.parser.elements;

import de.jsourcer.parser.misc.Counter;
import de.jsourcer.parser.misc.IndexedCharArray;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericsElement extends AbstractElement {
    private final Set<String> generics = new HashSet<>();

    public GenericsElement(@NotNull String value) {
        super(value);
    }

    @NotNull
    public Set<String> getGenerics() {
        return generics;
    }

    @NotNull
    public static GenericsElement parse(@NotNull IndexedCharArray charArray) {
        charArray.modifyIndex(-1);
        StringBuilder builder = new StringBuilder();
        Counter counter = new Counter();
        charArray.indexLoop((character, integer) -> {
            if (character == '<') {
                counter.increase();
            } else if (character == '>') {
                counter.decrease();
            }
            builder.append(character);
            return counter.isZero();
        });
        return new GenericsElement(builder.toString()).parseGenerics();
    }

    @NotNull
    public GenericsElement parseGenerics() {
        Set<String> generics = new HashSet<>();
        Matcher matcher = Pattern.compile("(?<=<).*(?=>)").matcher(value);
        if (matcher.find()) {
            for (String s : matcher.group().split(",")) {
                generics.add(s.trim());
            }
        }
        return this;
    }
}
