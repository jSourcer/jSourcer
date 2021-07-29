package de.jsourcer.parser.objects.clazz.types;

import de.jsourcer.parser.objects.clazz.AbstractClazz;
import de.jsourcer.parser.objects.clazz.GenericHolder;
import org.jetbrains.annotations.NotNull;

public class InterfaceClazz extends TypeClazz implements GenericHolder {

    public InterfaceClazz(
        @NotNull AbstractClazz clazz) {
        super(clazz);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
