package de.jsourcer.parser.objects.clazz.types;

import de.jsourcer.parser.objects.clazz.AbstractClazz;
import de.jsourcer.parser.objects.clazz.GenericHolder;
import org.jetbrains.annotations.NotNull;

public class StandardClazz extends TypeClazz implements GenericHolder {

    private final boolean abstractClazz;

    public StandardClazz(
        @NotNull AbstractClazz clazz, boolean abstractClazz) {
        super(clazz);
        this.abstractClazz = abstractClazz;
    }

    public boolean isAbstractClazz() {
        return abstractClazz;
    }
}
