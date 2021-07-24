package de.jsourcer.parser.misc.wrapped;

public class WrappedChar{
    private char character;

    public WrappedChar(char character) {
        this.character = character;
    }

    public WrappedChar() {
        this.character = '\u0000';
    }

    public void set(char character) {
        this.character = character;
    }

    public char get() {
        return character;
    }
}
