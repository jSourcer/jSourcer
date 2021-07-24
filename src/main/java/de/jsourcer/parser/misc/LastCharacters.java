package de.jsourcer.parser.misc;

public class LastCharacters {

    private final char[] array;

    public LastCharacters(char[] array) {
        this.array = array;
    }

    public void addAndUpdate(char newEntry) {
        if (array.length - 1 >= 0) System.arraycopy(array, 0, array, 1, array.length - 1);
        array[0] = newEntry;
    }

    public char[] getArray() {
        return array;
    }

    public char get(int index) {
        return array[index];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char c : array) {
            builder.append(c);
        }
        return builder.toString();
    }
}
