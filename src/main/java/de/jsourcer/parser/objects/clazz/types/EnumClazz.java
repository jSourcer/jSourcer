package de.jsourcer.parser.objects.clazz.types;

import de.jsourcer.parser.objects.clazz.AbstractClazz;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class EnumClazz extends TypeClazz {

    public EnumClazz(
        @NotNull AbstractClazz clazz) {
        super(clazz);
    }

    public String toString() {
        return "Enum{" +
            "parentFile=" + parentFile +
            ", name=" + name +
            ", accessModifier=" + accessModifier +
            ", otherModifier=" + Arrays.toString(otherModifier) +
            ", parentClazz=" + parentClazz +
            ", superClazz=" + superClazz +
            ", superInterfaces=" + Arrays.toString(superInterfaces) +
            ", scope=" + scope +
            ", arguments=" + Arrays.toString(arguments) +
            '}';
    }
}
