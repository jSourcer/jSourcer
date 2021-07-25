package de.jsourcer.parser.objects.clazz;

import de.jsourcer.parser.objects.AccessModifier;
import de.jsourcer.parser.objects.javafile.JavaFile;
import de.jsourcer.parser.types.ModifierType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class GenericClazz extends AbstractClazz {
    protected final Set<String> generics;

    public GenericClazz(@NotNull JavaFile rootFile, @Nullable AbstractClazz parent, @NotNull AccessModifier accessModifier,
                        @NotNull String name, @NotNull List<ModifierType> modifiers, @Nullable String extendsClazz,
                        @NotNull Set<String> implementsClazzes, @NotNull Set<String> generics) {
        super(rootFile, parent, accessModifier, name, modifiers, extendsClazz, implementsClazzes);
        this.generics = generics;
    }

    @NotNull
    public Set<String> getGenerics() {
        return generics;
    }
}
