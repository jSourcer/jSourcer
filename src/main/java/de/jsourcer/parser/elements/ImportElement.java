package de.jsourcer.parser.elements;

import de.jsourcer.parser.objects.javafile.header.Import;
import org.jetbrains.annotations.NotNull;

public class ImportElement extends AbstractElement {

    public ImportElement(@NotNull String value) {
        super(value);
    }

    @NotNull
    public Import toImport(@NotNull NoneElement element, boolean staticImport) {
        return new Import(element.getValue(), staticImport);
    }
}
