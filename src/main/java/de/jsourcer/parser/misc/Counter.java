package de.jsourcer.parser.misc;

public class Counter {
    private int counter;

    public Counter(int counter) {
        this.counter = counter;
    }

    public Counter(){
        this(0);
    }

    public void increase() {
        counter++;
    }

    public void decrease() {
        counter--;
    }

    public boolean isZero() {
        return counter == 0;
    }

    public int getCounter() {
        return counter;
    }
}
