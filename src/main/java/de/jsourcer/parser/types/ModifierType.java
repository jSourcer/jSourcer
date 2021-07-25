package de.jsourcer.parser.types;

import org.jetbrains.annotations.NotNull;

public enum ModifierType {
    PUBLIC,
    PRIVATE,
    PROTECTED,
    STATIC,
    FINAL,
    SYNCHRONIZED,
    VOLATILE,
    TRANSIENT,
    NATIVE,
    ABSTRACT,
    STRICTFP;

    @NotNull
    public static ModifierType getModifier(@NotNull String word) {
        return ModifierType.valueOf(word.toUpperCase());
    }

    public boolean isAccessModifier() {
        return switch (this) {
            case PUBLIC, PRIVATE, PROTECTED -> true;
            default -> false;
        };
    }
}
