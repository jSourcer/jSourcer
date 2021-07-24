package de.jsourcer.parser.elements;

import de.jsourcer.parser.types.ModifierType;

public class ModifierElement extends AbstractElement{
    private final ModifierType modifierType;

    public ModifierElement(String value, ModifierType modifierType) {
        super(value);
        this.modifierType = modifierType;
    }

    public ModifierType getModifier() {
        return modifierType;
    }
}
