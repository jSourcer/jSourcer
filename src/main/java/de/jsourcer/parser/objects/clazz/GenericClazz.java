package de.jsourcer.parser.objects.clazz;

import de.jsourcer.parser.objects.AccessModifier;
import de.jsourcer.parser.objects.javafile.JavaFile;
import de.jsourcer.parser.types.ModifierType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class GenericClazz extends AbstractClazz {
    protected final Set<String> generics;

    public GenericClazz(JavaFile rootFile, Optional<AbstractClazz> parent, AccessModifier accessModifier, String name, List<ModifierType> modifiers, Optional<String> extendsClazz, Set<String> implementsClazzes, Set<String> generics) {
        super(rootFile, parent, accessModifier, name, modifiers, extendsClazz, implementsClazzes);
        this.generics = generics;
    }

    public Set<String> getGenerics() {
        return generics;
    }
}
