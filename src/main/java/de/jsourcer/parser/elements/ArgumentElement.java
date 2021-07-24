package de.jsourcer.parser.elements;

import de.jsourcer.parser.misc.IndexedCharArray;
import de.jsourcer.parser.objects.Variable;

import java.util.ArrayList;
import java.util.List;

public class ArgumentElement extends AbstractElement{
    private final List<Variable> variables;

    public ArgumentElement(String value, List<Variable> variables) {
        super(value);
        this.variables = variables;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public static AbstractElement parse(IndexedCharArray charArray) {
        List<Variable> variables = new ArrayList<>();
        charArray.modifyIndex(-1);
        StringBuilder builder = new StringBuilder();
        charArray.indexLoop((character, integer) -> {
            builder.append(character);
            return character == ')';
        });
        String value = builder.toString();
        for (String c : value.split(",")) {
            String[] sides = c.trim().split("  *");
            if(sides.length != 2) throw new RuntimeException("parsing from Arguments failed");
            variables.add(new Variable(sides[0], sides[1]));
        }
        return new ArgumentElement(value, variables);
    }
}
