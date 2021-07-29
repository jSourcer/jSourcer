package de.jsourcer.parser.objects.clazz.name;

import org.jetbrains.annotations.NotNull;

public class ClazzName {
    final String name;

    public ClazzName(String name) {
        this.name = name;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ClazzName{" +
            "name='" + name + '\'' +
            '}';
    }
}
