package de.jsourcer.parser;

import de.jsourcer.parser.elements.*;
import de.jsourcer.parser.misc.Buffer;
import de.jsourcer.parser.misc.IndexedCharArray;
import de.jsourcer.parser.util.BuilderUtil;
public class ElementReader {
    private final StringBuilder elementBuilder = new StringBuilder();
    private final Buffer<AbstractElement> buffer = new Buffer<>();

    public void readNextElement(IndexedCharArray charArray) {
        charArray.indexLoop((character, integer) -> {
            if(character == '(') {
                saveElement();
                buffer.add(ArgumentElement.parse(charArray));
                return true;
            }

            if(character == '<') {
                saveElement();
                buffer.add(GenericsElement.parse(charArray));
                return true;
            }

            if(character == '{') {
                saveElement();
                buffer.add(ScopeElement.resolve(charArray));
                return true;
            }

            if(character == ';') {
                saveElement();
                buffer.add(new SeparatorElement(character.toString()));
                return true;
            }

            if(Character.isWhitespace(character)) {
                saveElement();
                return true;
            }
            elementBuilder.append(character);
            return false;
        });
    }

    public Buffer<AbstractElement> getBuffer() {
        return buffer;
    }

    private void saveElement() {
        if(!elementBuilder.isEmpty()) {
            buffer.add(AbstractElement.findElement(BuilderUtil.getAndClear(elementBuilder)));
        }
    }
}
