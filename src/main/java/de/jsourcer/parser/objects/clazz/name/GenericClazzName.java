package de.jsourcer.parser.objects.clazz.name;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class GenericClazzName extends ClazzName{

    private final String[] generics;

    public GenericClazzName(String name, String[] generics) {
        super(name);
        this.generics = generics;
    }

    @Override
    public String toString() {
        return "GenericClazzName{" +
            "name='" + name + '\'' +
            ", generics=" + Arrays.toString(generics) +
            '}';
    }

    @NotNull
    public String[] getGenerics() {
        return generics;
    }
}