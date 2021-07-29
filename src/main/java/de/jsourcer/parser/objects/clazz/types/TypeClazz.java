package de.jsourcer.parser.objects.clazz.types;

import de.jsourcer.parser.objects.clazz.AbstractClazz;
import org.jetbrains.annotations.NotNull;

public class TypeClazz extends AbstractClazz {

    public TypeClazz(@NotNull AbstractClazz clazz) {
        super(clazz.getParentFile(),
            clazz.getName(),
            clazz.getAccessModifier(),
            clazz.getOtherModifier(),
            clazz.getParentClazz().isPresent() ? clazz.getParentClazz().get() : null,
            clazz.getSuperClazz().isPresent() ? clazz.getSuperClazz().get() : null,
            clazz.getSuperInterfaces(),
            clazz.getScope(),
            clazz.getArguments().isPresent() ? clazz.getArguments().get() : null);
    }
}
