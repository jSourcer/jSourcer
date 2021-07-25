package de.jsourcer.parser.objects.clazz;


import de.jsourcer.parser.elements.AbstractElement;
import de.jsourcer.parser.elements.ArgumentElement;
import de.jsourcer.parser.elements.ClazzElement;
import de.jsourcer.parser.elements.ExtendsElement;
import de.jsourcer.parser.elements.GenericsElement;
import de.jsourcer.parser.elements.ImplementsElement;
import de.jsourcer.parser.elements.ModifierElement;
import de.jsourcer.parser.elements.NoneElement;
import de.jsourcer.parser.misc.Buffer;
import de.jsourcer.parser.objects.AccessModifier;
import de.jsourcer.parser.objects.Variable;
import de.jsourcer.parser.objects.javafile.JavaFile;
import de.jsourcer.parser.types.ClazzType;
import de.jsourcer.parser.types.ModifierType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AbstractClazz {
    protected final JavaFile rootFile;
    protected final AbstractClazz parent;
    protected final AccessModifier accessModifier;
    protected final String name;
    protected final List<ModifierType> modifiers;
    protected final String extendsClazz;
    protected final Set<String> implementsClazzes;

    public AbstractClazz(@NotNull JavaFile rootFile, @Nullable AbstractClazz parent, @NotNull AccessModifier accessModifier,
                         @NotNull String name, @NotNull List<ModifierType> modifiers, @Nullable String extendsClazz,
                         @NotNull Set<String> implementsClazzes) {
        this.rootFile = rootFile;
        this.parent = parent;
        this.accessModifier = accessModifier;
        this.name = name;
        this.modifiers = modifiers;
        this.extendsClazz = extendsClazz;
        this.implementsClazzes = implementsClazzes;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public AccessModifier getAccessModifier() {
        return accessModifier;
    }

    @NotNull
    public JavaFile getRootFile() {
        return rootFile;
    }

    @NotNull
    public List<ModifierType> getModifiers() {
        return modifiers;
    }

    @NotNull
    public Optional<AbstractClazz> getParent() {
        return Optional.ofNullable(parent);
    }

    @NotNull
    public Optional<String> getExtendsClazz() {
        return Optional.ofNullable(extendsClazz);
    }

    @NotNull
    public Set<String> getImplementsClazzes() {
        return implementsClazzes;
    }

    @NotNull
    public static Optional<AbstractClazz> parse(@NotNull Buffer<AbstractElement> buffer, @NotNull JavaFile rootFile, @Nullable AbstractClazz parent) {
        ClazzType type = null;
        AccessModifier accessModifier = AccessModifier.DEFAULT;
        List<ModifierType> modifiers = new ArrayList<>();
        String name = null;
        String extendsClazz = null;
        Set<String> implementsClazzes = new HashSet<>();
        Set<String> generics = new HashSet<>();
        List<Variable> arguments = new ArrayList<>();

        for (int i = 0; i < buffer.size(); i++) {
            AbstractElement element = buffer.get(i);
            if (element instanceof ClazzElement clazzElement) {
                type = clazzElement.getType();
                continue;
            }
            if (element instanceof NoneElement noneElement) {
                name = noneElement.getValue();
                continue;
            }
            if (element instanceof ExtendsElement) {
                extendsClazz = buffer.get(++i).getValue();
                continue;
            }
            if (element instanceof ImplementsElement) {
                while (buffer.get(i + 1) instanceof NoneElement noneElement) {
                    implementsClazzes.add(noneElement.getValue());
                    i++;
                }
                continue;
            }
            if (element instanceof GenericsElement genericsElement) {
                generics = genericsElement.getGenerics();
                continue;
            }
            if (element instanceof ArgumentElement argumentElement) {
                arguments = argumentElement.getVariables();
                continue;
            }
            if (element instanceof ModifierElement modifierElement) {
                ModifierType modifierType = modifierElement.getModifier();
                if (modifierType.isAccessModifier()) {
                    accessModifier = AccessModifier.valueOf(modifierType.name());
                    continue;
                }
                modifiers.add(modifierType);
            }
        }

        if (name == null || type == null) throw new RuntimeException("class parse failed");

        return switch (type) {
            case STANDARD -> Optional.of(new StandardClazz(rootFile, parent, accessModifier, name, modifiers, extendsClazz, implementsClazzes, generics, modifiers.contains(ModifierType.ABSTRACT)));
            default -> Optional.empty();
        };
    }
}
