package de.jsourcer.parser.elements;

import org.jetbrains.annotations.NotNull;

public class NoneElement extends AbstractElement {

    public NoneElement(@NotNull String value) {
        super(value);
    }

    @NotNull
    public static NoneElement parse(@NotNull String text) {
        return new NoneElement(text.replace(",", "")); // TODO: optimize that
    }
}
