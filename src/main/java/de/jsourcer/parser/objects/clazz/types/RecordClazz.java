package de.jsourcer.parser.objects.clazz.types;

import de.jsourcer.parser.objects.clazz.AbstractClazz;
import de.jsourcer.parser.objects.clazz.GenericHolder;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class RecordClazz extends TypeClazz implements GenericHolder {

    public RecordClazz(
        @NotNull AbstractClazz clazz) {
        super(clazz);
    }

    public String toString() {
        return "Record{" +
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
