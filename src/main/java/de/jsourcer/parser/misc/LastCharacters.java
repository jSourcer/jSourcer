package de.jsourcer.parser.misc;

public record LastCharacters(char[] array) {

    public void addAndUpdate(char newEntry) {
        if (array.length - 1 >= 0) {
            System.arraycopy(array, 0, array, 1, array.length - 1);
        }
        array[0] = newEntry;
    }

    public char get(int index) {
        return array[index];
    }

    @Override
    public String toString() {
        return "LastCharacters{" +
            "array=" + String.valueOf(array) +
            '}';
    }
}
