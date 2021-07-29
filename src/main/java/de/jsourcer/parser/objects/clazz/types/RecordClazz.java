package de.jsourcer.parser.objects.clazz.types;

import de.jsourcer.parser.objects.clazz.AbstractClazz;
import de.jsourcer.parser.objects.clazz.GenericHolder;
import org.jetbrains.annotations.NotNull;

public class RecordClazz extends TypeClazz implements GenericHolder {

    public RecordClazz(
        @NotNull AbstractClazz clazz) {
        super(clazz);
    }
}
