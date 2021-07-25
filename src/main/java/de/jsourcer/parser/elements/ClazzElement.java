package de.jsourcer.parser.elements;

import de.jsourcer.parser.types.ClazzType;
import org.jetbrains.annotations.NotNull;

public class ClazzElement extends AbstractElement {
    private final ClazzType type;

    public ClazzElement(@NotNull String value, @NotNull ClazzType type) {
        super(value);
        this.type = type;
    }

    @NotNull
    public ClazzType getType() {
        return type;
    }
}
