package de.jsourcer.parser.misc.counter;

import de.jsourcer.parser.misc.FunctionalCharArray;
import de.jsourcer.parser.util.CharSequenceSkipper;

public class CharacterCounter {

    private final Counter counter;
    private final char increase;
    private final char decrease;

    public CharacterCounter(int start, char increase, char decrease) {
        this.counter = new Counter(start);
        this.increase = increase;
        this.decrease = decrease;
    }

    public static void charArrayLoopCount(int startcount, char increase, char decrease,
        FunctionalCharArray charArray, boolean skippingChecks) {
        CharacterCounter counter = new CharacterCounter(startcount, increase, decrease);
        if (skippingChecks) {
            charArray.indexLoop((character, integer) -> counter.countAndCheckZero(charArray));
        } else {
            charArray.indexLoop((character, integer) -> counter.countAndCheckZero(character));
        }
    }

    public boolean countAndCheckZero(FunctionalCharArray array) {
        char character = array.getCurrentChar();
        if (character == '"') {
            CharSequenceSkipper.skipString(array);
        }
        if (character == '\'') {
            CharSequenceSkipper.skipCharacter(array);
        }
        return countAndCheckZero(array.getCurrentChar());
    }

    public boolean countAndCheckZero(char character) {
        if (character == increase) {
            counter.increase();
        } else if (character == decrease) {
            counter.decrease();
        }
        return counter.isZero();
    }
}
