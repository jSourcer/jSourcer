package de.jsourcer.parser.util;

import de.jsourcer.parser.misc.IndexedCharArray;
import de.jsourcer.parser.misc.LastCharacters;
import de.jsourcer.parser.misc.wrapped.WrappedChar;

public final class CharSequenceSkipper {

    private CharSequenceSkipper() {
    }

    public static void skipString(IndexedCharArray charArray) {
        boolean isTextBlock = (charArray.size() > 6) && CharArrayUtil.startWith(charArray, "\"\"\"");
        LastCharacters last = new LastCharacters(new char[3]);
        charArray.indexLoop((c, integer) -> {
            if (last.get(0) != '\u0000') {
                if (last.get(0) != '\\' && c == '"' && !isTextBlock) {
                    return true;
                }
                if (last.get(1) != '\u0000' && last.get(2) != '\u0000') {
                    if (last.get(0) == '"' && last.get(1) == '"' && last.get(2) != '\\' && c == '"') {
                        return true;
                    }
                }
            }
            last.addAndUpdate(c);
            return false;
        });
    }

    public static void skipCharacter(IndexedCharArray charArray) {
        WrappedChar last = new WrappedChar();
        charArray.indexLoop((c, integer) -> {
            if (last.get() != '\u0000') {
                return last.get() != '\\' && c == '\'';
            }
            last.set(c);
            return false;
        });
    }
}
