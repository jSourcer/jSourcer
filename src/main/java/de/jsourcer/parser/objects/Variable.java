package de.jsourcer.parser.objects;

import org.jetbrains.annotations.NotNull;

public class Variable {
    private final String type;
    private final String name;

    public Variable(@NotNull String type, @NotNull String name) {
        this.type = type;
        this.name = name;
    }

    @NotNull
    public String getType() {
        return type;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
