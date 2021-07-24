package de.jsourcer.parser.objects.clazz;

import de.jsourcer.parser.objects.AccessModifier;
import de.jsourcer.parser.objects.javafile.JavaFile;
import de.jsourcer.parser.types.ModifierType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class StandardClazz extends GenericClazz{
    private final boolean abstractClazz;

    public StandardClazz(JavaFile rootFile, Optional<AbstractClazz> parent, AccessModifier accessModifier, String name, List<ModifierType> modifiers, Optional<String> extendsClazz, Set<String> implementsClazzes, Set<String> generics, boolean abstractClazz) {
        super(rootFile, parent, accessModifier, name, modifiers, extendsClazz, implementsClazzes, generics);
        this.abstractClazz = abstractClazz;
    }

    public boolean isAbstractClazz() {
        return abstractClazz;
    }

    @Override
    public String toString() {
        return "StandardClazz{" +
                "rootFile=" + rootFile +
                ", parent=" + parent +
                ", accessModifier=" + accessModifier +
                ", name='" + name + '\'' +
                ", modifiers=" + modifiers +
                ", extendsClazz=" + extendsClazz +
                ", implementsClazzes=" + implementsClazzes +
                ", generics=" + generics +
                ", abstractClazz=" + abstractClazz +
                '}';
    }
}
