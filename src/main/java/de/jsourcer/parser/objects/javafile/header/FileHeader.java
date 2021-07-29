package de.jsourcer.parser.objects.javafile.header;

import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FileHeader {

    private final Package clazzPackage;
    private final Set<Import> imports = new HashSet<>();

    public FileHeader(@Nullable Package clazzPackage) {
        this.clazzPackage = clazzPackage;
    }

    @Nullable
    public Package getClazzPackage() {
        return clazzPackage;
    }

    @NotNull
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

