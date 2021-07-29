package de.jsourcer.parser.misc;

import java.util.function.BiFunction;
import org.jetbrains.annotations.NotNull;

public class FunctionalCharArray {

    private final char[] chars;
    private final int lastIndex;
    private int index;
    private int builderStartIndex;
    private int builderStopIndex;
    private boolean builderStarted;

    public FunctionalCharArray(char[] chars, int lastIndex) {
        this(chars, -1, lastIndex);
    }

    public FunctionalCharArray(char[] chars) {
        this(chars, chars.length);
    }

    public FunctionalCharArray(char[] chars, int index, int lastIndex) {
        this.chars = chars;
        this.index = index;
        this.lastIndex = lastIndex;
    }

    @NotNull
    public static FunctionalCharArray ofString(@NotNull String text) {
        return new FunctionalCharArray(text.toCharArray());
    }

    public void indexLoop(@NotNull BiFunction<Character, Integer, Boolean> task) {
        if (builderStarted) {
            builderStartIndex = expectedNextIndex();
        }
        while (expectedNextIndex() < lastIndex) {
            nextIndex();
            if (task.apply(chars[index], index)) {
                builderStopIndex = index;
                return;
            }
        }
    }

    public void modifiedIndexLoop(int modifier,
        @NotNull BiFunction<Character, Integer, Boolean> task) {
        index = modifier + index;
        indexLoop(task);
    }

    public FunctionalCharArray decoupledArray() {
        return new FunctionalCharArray(chars, index, lastIndex);
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

    public int size() {
        return lastIndex - index;
    }

    public void startStringBuilder() {
        builderStarted = true;
    }

    public char getCurrentChar() {
        return chars[index];
    }

    public String stopAndGetBuilder() {
        builderStarted = false;
        return String.valueOf(chars, builderStartIndex, builderStopIndex-builderStartIndex);
    }
}
