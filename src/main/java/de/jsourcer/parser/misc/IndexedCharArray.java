package de.jsourcer.parser.misc;

import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class IndexedCharArray {
    private final char[] chars;
    private int index;
    private final int lastIndex;

    public IndexedCharArray(char[] chars, int lastIndex) {
        this(chars, -1, lastIndex);
    }

    public IndexedCharArray(char[] chars) {
        this(chars, chars.length);
    }

    public IndexedCharArray(char[] chars, int index, int lastIndex) {
        this.chars = chars;
        this.index = index;
        this.lastIndex = lastIndex;
    }

    @NotNull
    public static IndexedCharArray ofString(@NotNull String text) {
        return new IndexedCharArray(text.toCharArray());
    }

    public void indexLoop(@NotNull BiFunction<Character, Integer, Boolean> task) {
        while (expectedNextIndex() < lastIndex) {
            nextIndex();
            if (task.apply(chars[index], index)) return;
        }
    }

    public void decoupledIndexLoop(@NotNull BiFunction<Character, Integer, Boolean> task) {
        decoupledIndexLoop(0, task);
    }

    public void decoupledIndexLoop(int indexModifier, @NotNull BiFunction<Character, Integer, Boolean> task) {
        new IndexedCharArray(chars, index + indexModifier, lastIndex).indexLoop(task);
    }

    public int getIndex() {
        return index;
    }

    public boolean indexAtEnd() {
        return expectedNextIndex() == chars.length;
    }

    public int expectedNextIndex() {
        return index + 1;
    }

    public void nextIndex() {
        index++;
    }

    public char[] getChars() {
        return chars;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public int size() {
        return lastIndex - index;
    }

    public char currentChar() {
        return chars[index];
    }

    public void modifyIndex(int modifier) {
        index = index + modifier;
    }
}
