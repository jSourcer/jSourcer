package de.jsourcer.parser.objects;

public class Variable {
    private final String type;
    private final String name;

    public Variable(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
