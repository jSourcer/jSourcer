package de.jsourcer.parser.elements;

import de.jsourcer.parser.types.ClazzType;
import de.jsourcer.parser.types.ModifierType;
import org.jetbrains.annotations.NotNull;

public class AbstractElement {

    protected final String value;

    public AbstractElement(@NotNull String value) {
        this.value = value;
    }

    @NotNull
    public static AbstractElement findElement(@NotNull String value) {
        return switch (value) {
            case "package" -> new PackageElement(value);
            case "import" -> new ImportElement(value);
            case "public", "private", "protected", "static",
                "final", "synchronized", "volatile", "transient",
                "native", "abstract", "strictfp" -> new ModifierElement(value,
                ModifierType.getModifier(value));
            case "class", "interface", "enum", "record" -> new ClazzElement(value,
                ClazzType.getType(value));
            case "implements" -> new ImplementsElement(value);
            case "extends" -> new ExtendsElement(value);
            default -> new NoneElement(value);
        };
    }

    @NotNull
    public String getValue() {
        return value;
    }
}
