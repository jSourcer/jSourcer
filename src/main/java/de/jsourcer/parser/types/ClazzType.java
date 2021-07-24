package de.jsourcer.parser.types;

public enum ClazzType {
    STANDARD("class"),
    INTERFACE("interface"),
    ENUM("enum"),
    RECORD("record");

    private final String identifier;

    ClazzType(String identifier) {
        this.identifier = identifier;
    }

    public static ClazzType getType(String identifier) {
        for (ClazzType value : values()) {
            if (value.identifier.equals(identifier)) return value;
        }
        throw new EnumConstantNotPresentException(ClazzType.class, identifier);
    }
}
