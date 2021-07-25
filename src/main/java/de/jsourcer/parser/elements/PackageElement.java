package de.jsourcer.parser.elements;

import de.jsourcer.parser.objects.javafile.header.Package;
import org.jetbrains.annotations.NotNull;

public class PackageElement extends AbstractElement {

    public PackageElement(@NotNull String value) {
        super(value);
    }

    @NotNull
    public Package toPackage(@NotNull NoneElement element) {
        return new Package(element.getValue());
    }
}
