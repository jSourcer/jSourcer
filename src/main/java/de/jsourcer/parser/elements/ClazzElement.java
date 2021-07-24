package de.jsourcer.parser.elements;

import de.jsourcer.parser.types.ClazzType;

public class ClazzElement extends AbstractElement{
    private final ClazzType type;
    public ClazzElement(String value, ClazzType type) {
        super(value);
        this.type = type;
    }

    public ClazzType getType() {
        return type;
    }
}
