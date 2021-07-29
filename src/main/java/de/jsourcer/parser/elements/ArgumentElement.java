package de.jsourcer.parser.elements;

import de.jsourcer.parser.misc.FunctionalCharArray;
import de.jsourcer.parser.objects.Variable;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class ArgumentElement extends AbstractElement {

    private final Variable[] variables;

    public ArgumentElement(@NotNull String value, @NotNull Variable[] variables) {
        super(value);
        this.variables = variables;
    }

    @NotNull
    public static AbstractElement parse(@NotNull FunctionalCharArray charArray) {
        charArray.startStringBuilder();
        charArray.indexLoop((character, integer) -> character == ')');
        String value = charArray.stopAndGetBuilder();
        return new ArgumentElement(value, Arrays
            .stream(value.split(","))
            .map(Variable::fromString)
            .toArray(Variable[]::new));
    }

    @NotNull
    public Variable[] getVariables() {
        return variables;
    }
}
