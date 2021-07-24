package de.jsourcer.parser.objects.javafile.header;

import java.util.HashSet;
import java.util.Set;

public class FileHeader {
    private final Package clazzPackage;
    private final Set<Import> imports = new HashSet<>();

    public FileHeader(Package clazzPackage) {
        this.clazzPackage = clazzPackage;
    }

    public Package getClazzPackage() {
        return clazzPackage;
    }

    public Set<Import> getImports() {
        return imports;
    }

    @Override
    public String toString() {
        return "ClazzHeader{" +
                "clazzPackage=" + clazzPackage +
                ", imports=" + imports +
                '}';
    }
}

