package de.jsourcer.parser.elements;

import de.jsourcer.parser.objects.javafile.header.Import;

public class ImportElement extends AbstractElement{
    public ImportElement(String value) {
        super(value);
    }

    public Import toImport(NoneElement element, boolean staticImport) {
        return new Import(element.getValue(), staticImport);
    }
}
