package de.jsourcer.parser.objects.clazz;


import de.jsourcer.parser.elements.*;
import de.jsourcer.parser.misc.Buffer;
import de.jsourcer.parser.objects.Variable;
import de.jsourcer.parser.objects.javafile.JavaFile;
import de.jsourcer.parser.objects.AccessModifier;
import de.jsourcer.parser.types.ClazzType;
import de.jsourcer.parser.types.ModifierType;

import java.util.*;

public class AbstractClazz {
    protected final JavaFile rootFile;
    protected final Optional<AbstractClazz> parent;
    protected final AccessModifier accessModifier;
    protected final String name;
    protected final List<ModifierType> modifiers;
    protected final Optional<String> extendsClazz;
    protected final Set<String> implementsClazzes;

    public AbstractClazz(JavaFile rootFile, Optional<AbstractClazz> parent, AccessModifier accessModifier, String name, List<ModifierType> modifiers, Optional<String> extendsClazz, Set<String> implementsClazzes) {
        this.rootFile = rootFile;
        this.parent = parent;
        this.accessModifier = accessModifier;
        this.name = name;
        this.modifiers = modifiers;
        this.extendsClazz = extendsClazz;
        this.implementsClazzes = implementsClazzes;
    }

    public String getName() {
        return name;
    }

    public AccessModifier getAccessModifier() {
        return accessModifier;
    }

    public JavaFile getRootFile() {
        return rootFile;
    }

    public List<ModifierType> getModifiers() {
        return modifiers;
    }

    public Optional<AbstractClazz> getParent() {
        return parent;
    }

    public Optional<String> getExtendsClazz() {
        return extendsClazz;
    }

    public Set<String> getImplementsClazzes() {
        return implementsClazzes;
    }

    public static Optional<AbstractClazz> parse(Buffer<AbstractElement> buffer, JavaFile rootFile, Optional<AbstractClazz> parent) {
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
            if(element instanceof ClazzElement clazzElement) {
                type = clazzElement.getType();
                continue;
            }
            if(element instanceof NoneElement noneElement) {
                name = noneElement.getValue();
                continue;
            }
            if(element instanceof ExtendsElement) {
                extendsClazz = buffer.get(++i).getValue();
                continue;
            }
            if(element instanceof ImplementsElement) {
                while (buffer.get(i+1) instanceof NoneElement noneElement) {
                    implementsClazzes.add(noneElement.getValue());
                    i++;
                }
                continue;
            }
            if(element instanceof GenericsElement genericsElement) {
                generics = genericsElement.getGenerics();
                continue;
            }
            if(element instanceof ArgumentElement argumentElement) {
                arguments = argumentElement.getVariables();
                continue;
            }
            if(element instanceof ModifierElement modifierElement) {
                ModifierType modifierType = modifierElement.getModifier();
                if(modifierType.isAccessModifier()){
                    accessModifier = AccessModifier.valueOf(modifierType.name());
                    continue;
                }
                modifiers.add(modifierType);
            }
        }

        System.out.println(arguments);
        if(name == null || type == null) throw new RuntimeException("class parse failed");

        return switch (type) {
            case STANDARD -> Optional.of(new StandardClazz(rootFile, parent, accessModifier, name, modifiers, Optional.ofNullable(extendsClazz), implementsClazzes, generics, modifiers.contains(ModifierType.ABSTRACT)));
            default -> Optional.empty();
        };
    }
}
