package de.jsourcer.parser.objects.clazz;

import de.jsourcer.parser.elements.AbstractElement;
import de.jsourcer.parser.elements.ClazzElement;
import de.jsourcer.parser.elements.ExtendsElement;
import de.jsourcer.parser.elements.GenericsElement;
import de.jsourcer.parser.elements.ImplementsElement;
import de.jsourcer.parser.elements.ModifierElement;
import de.jsourcer.parser.elements.NoneElement;
import de.jsourcer.parser.elements.ScopeElement;
import de.jsourcer.parser.misc.Buffer;
import de.jsourcer.parser.objects.AccessModifier;
import de.jsourcer.parser.objects.clazz.name.ClazzName;
import de.jsourcer.parser.objects.clazz.name.GenericClazzName;
import de.jsourcer.parser.objects.Scope;
import de.jsourcer.parser.objects.clazz.types.EnumClazz;
import de.jsourcer.parser.objects.clazz.types.InterfaceClazz;
import de.jsourcer.parser.objects.clazz.types.RecordClazz;
import de.jsourcer.parser.objects.clazz.types.StandardClazz;
import de.jsourcer.parser.objects.javafile.JavaFile;
import de.jsourcer.parser.types.ClazzType;
import de.jsourcer.parser.types.ModifierType;
import de.jsourcer.parser.util.Checks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AbstractClazz {
    protected final JavaFile parentFile;
    protected final ClazzName name;
    protected final AccessModifier accessModifier;
    protected final ModifierType[] otherModifier;
    protected final AbstractClazz parentClazz;
    protected final ClazzName superClazz;
    protected final ClazzName[] superInterfaces;
    protected final Scope scope;


    public AbstractClazz(@NotNull JavaFile parentFile, @NotNull ClazzName name,
        @NotNull AccessModifier accessModifier, @NotNull ModifierType[] otherModifier,
        @Nullable AbstractClazz parentClazz,
        ClazzName superClazz, ClazzName[] superInterfaces,
        @NotNull Scope scope) {
        this.parentFile = parentFile;
        this.name = name;
        this.accessModifier = accessModifier;
        this.otherModifier = otherModifier;
        this.parentClazz = parentClazz;
        this.superClazz = superClazz;
        this.superInterfaces = superInterfaces;
        this.scope = scope;
    }

    @NotNull
    public Optional<AbstractClazz> getParentClazz() {
        return Optional.ofNullable(parentClazz);
    }

    @NotNull
    public ClazzName getName() {
        return name;
    }

    @NotNull
    public AccessModifier getAccessModifier() {
        return accessModifier;
    }

    @NotNull
    public JavaFile getParentFile() {
        return parentFile;
    }

    @NotNull
    public ModifierType[] getOtherModifier() {
        return otherModifier;
    }

    @NotNull
    public Scope getScope() {
        return scope;
    }

    @NotNull
    public Optional<ClazzName> getSuperClazz() {
        return Optional.ofNullable(superClazz);
    }

    @NotNull
    public ClazzName[] getSuperInterfaces() {
        return superInterfaces;
    }

    @Override
    public String toString() {
        return "AbstractClazz{" +
            "parentFile=" + parentFile +
            ", name=" + name +
            ", accessModifier=" + accessModifier +
            ", otherModifier=" + Arrays.toString(otherModifier) +
            ", parentClazz=" + parentClazz +
            ", superClazz=" + superClazz +
            ", superInterfaces=" + Arrays.toString(superInterfaces) +
            ", scope=" + scope +
            '}';
    }

    public static AbstractClazz parse(@NotNull Buffer<AbstractElement> buffer, @Nullable AbstractClazz parent, @NotNull JavaFile javaFile) {
        ClazzName name = null;
        AccessModifier accessModifier = AccessModifier.PACKAGE_PRIVATE;
        List<ModifierType> otherModifiers = new ArrayList<>();
        ClazzName superClazz = null;
        List<ClazzName> superInterfaces = new ArrayList<>();
        Scope scope = null;
        ClazzType type = null;

        for (int i = 0; i < buffer.size(); i++) {
            AbstractElement element = buffer.get(i);
            if(element instanceof ModifierElement modifierElement) {
                if(modifierElement.getModifier().isAccessModifier()) {
                    accessModifier = AccessModifier.valueOf(modifierElement.getModifier().name());
                }else {
                    otherModifiers.add(modifierElement.getModifier());
                }
                continue;
            }

            if(element instanceof ClazzElement clazzElement) {
                type = clazzElement.getType();
                continue;
            }

            if(element instanceof NoneElement) {
                ParseReturn parseReturn = parseClazzName(buffer, --i);
                i = parseReturn.index;
                name = parseReturn.name;
                continue;
            }

            if(element instanceof ExtendsElement) {
                ParseReturn parseReturn = parseClazzName(buffer, i);
                i = parseReturn.index;
                superClazz = parseReturn.name;
            }

            if(element instanceof ImplementsElement) {
                do {
                    ParseReturn parseReturn = parseClazzName(buffer, i);
                    i = parseReturn.index;
                    superInterfaces.add(parseReturn.name);
                } while (buffer.get(i+1) instanceof NoneElement);
            }

            if(element instanceof ScopeElement scopeElement) {
                scope = new Scope(scopeElement.getValue());
                //TODO: parse class recursive
            }
        }

        Checks.notNull(type, "Classtype");
        if(name == null || scope == null) throw new RuntimeException("name or scope cannot be null"); //TODO: replace with better exception
        AbstractClazz abstractClazz = new AbstractClazz(javaFile,
            name,
            accessModifier,
            otherModifiers.toArray(ModifierType[]::new),
            parent, superClazz,
            superInterfaces.toArray(ClazzName[]::new),
            scope);

        return switch (type) {
            case STANDARD -> new StandardClazz(abstractClazz, otherModifiers.contains(ModifierType.ABSTRACT));
            case ENUM -> new EnumClazz(abstractClazz);
            case RECORD -> new RecordClazz(abstractClazz);
            case INTERFACE -> new InterfaceClazz(abstractClazz);
        };
    }

    private static ParseReturn parseClazzName(@NotNull Buffer<AbstractElement> buffer, int index) {
        ClazzName name;
        if(buffer.get(++index) instanceof NoneElement noneElement) {
            if(buffer.get(index+1) instanceof GenericsElement genericsElement) {
                name = new GenericClazzName(noneElement.getValue(), genericsElement.getGenerics());
                ++index;
            }else {
                name = new ClazzName(noneElement.getValue());
            }
        }else {
            --index;
            throw new RuntimeException(
                "cannot find classname"); //TODO: replace with better exception
        }
        return new ParseReturn(name, index);
    }

    private record ParseReturn(ClazzName name, int index) {}
}
