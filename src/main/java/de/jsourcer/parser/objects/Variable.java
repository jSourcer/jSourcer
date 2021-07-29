package de.jsourcer.parser.objects;

import org.jetbrains.annotations.NotNull;

public record Variable(String type, String name) {

    public Variable(@NotNull String type, @NotNull String name) {
        this.type = type;
        this.name = name;
    }

    public static Variable fromString(@NotNull String value) {
        String[] sides = value.trim().split("\\s+");
        if (sides.length != 2) {
            throw new RuntimeException("parsing from Arguments failed");
        }
        return new Variable(sides[0], sides[1]);
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
