package de.jsourcer.parser.types;

import org.jetbrains.annotations.NotNull;

public enum ClazzType {
    STANDARD("class"),
    INTERFACE("interface"),
    ENUM("enum"),
    RECORD("record");

    private final String identifier;

    ClazzType(String identifier) {
        this.identifier = identifier;
    }

    @NotNull
    public static ClazzType getType(@NotNull String identifier) {
        for (ClazzType value : values()) {
            if (value.identifier.equals(identifier)) return value;
        }
        throw new EnumConstantNotPresentException(ClazzType.class, identifier);
    }
}
