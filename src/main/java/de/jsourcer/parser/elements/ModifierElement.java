package de.jsourcer.parser.elements;

import de.jsourcer.parser.types.ModifierType;
import org.jetbrains.annotations.NotNull;

public class ModifierElement extends AbstractElement {

    private final ModifierType modifierType;

    public ModifierElement(@NotNull String value, @NotNull ModifierType modifierType) {
        super(value);
        this.modifierType = modifierType;
    }

    @NotNull
    public ModifierType getModifier() {
        return modifierType;
    }
}
