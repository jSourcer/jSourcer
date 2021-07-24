package de.jsourcer.parser.elements;

import de.jsourcer.parser.types.ClazzType;
import de.jsourcer.parser.types.ModifierType;

public class AbstractElement {
    protected final String value;

    public AbstractElement(String value) {
        this.value = value;
    }

    public static AbstractElement findElement(String value) {
        return switch (value) {
            case "package" -> new PackageElement(value);
            case "import" -> new ImportElement(value);
            case "public", "private", "protected", "static",
                    "final", "synchronized", "volatile", "transient",
                    "native", "abstract", "strictfp" -> new ModifierElement(value, ModifierType.getModifier(value));
            case "class", "interface", "enum", "record" -> new ClazzElement(value, ClazzType.getType(value));
            case "implements" -> new ImplementsElement(value);
            case "extends" -> new ExtendsElement(value);
            default -> NoneElement.parse(value);
        };
    }

    public String getValue() {
        return value;
    }
}
