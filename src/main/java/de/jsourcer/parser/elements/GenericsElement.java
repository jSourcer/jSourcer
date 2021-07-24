package de.jsourcer.parser.elements;

import de.jsourcer.parser.misc.Counter;
import de.jsourcer.parser.misc.IndexedCharArray;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericsElement extends AbstractElement{
    private final Set<String> generics = new HashSet<>();

    public GenericsElement(String value) {
        super(value);
    }

    public Set<String> getGenerics() {
        return generics;
    }

    public static GenericsElement parse(IndexedCharArray charArray) {
        charArray.modifyIndex(-1);
        StringBuilder builder = new StringBuilder();
        Counter counter = new Counter();
        charArray.indexLoop((character, integer) -> {
            if(character == '<') {
                counter.increase();
            }else if(character == '>') {
                counter.decrease();
            }
            builder.append(character);
            return counter.isZero();
        });
        return new GenericsElement(builder.toString()).parseGenerics();
    }

    public GenericsElement parseGenerics() {
        Set<String> generics = new HashSet<>();
        Matcher matcher = Pattern.compile("(?<=<).*(?=>)").matcher(value);
        if(matcher.find()) {
            for (String s : matcher.group().split(",")) {
                generics.add(s.trim());
            }
        }
        return this;
    }
}
