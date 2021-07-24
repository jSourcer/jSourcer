package de.jsourcer.parser.types;

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

    public static ModifierType getModifier(String word) {
        return ModifierType.valueOf(word.toUpperCase());
    }

    public boolean isAccessModifier() {
        return switch (this) {
            case PUBLIC, PRIVATE, PROTECTED -> true;
            default -> false;
        };
    }
}
