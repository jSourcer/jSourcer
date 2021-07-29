package de.jsourcer.parser.objects.clazz.types;

import de.jsourcer.parser.objects.clazz.AbstractClazz;
import de.jsourcer.parser.objects.clazz.GenericHolder;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class InterfaceClazz extends TypeClazz implements GenericHolder {

    public InterfaceClazz(
        @NotNull AbstractClazz clazz) {
        super(clazz);
    }

    public String toString() {
        return "Interface{" +
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
