package de.jsourcer.parser.elements;

import de.jsourcer.parser.objects.javafile.header.Package;

public class PackageElement extends AbstractElement{
    public PackageElement(String value) {
        super(value);
    }

    public Package toPackage(NoneElement element) {
        return new Package(element.getValue());
    }
}
