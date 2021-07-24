package de.jsourcer.parser.elements;

public class NoneElement extends AbstractElement{
    public NoneElement(String value) {
        super(value);
    }

    public static NoneElement parse(String text) {
        return new NoneElement(text.replace(",", ""));
    }
}
