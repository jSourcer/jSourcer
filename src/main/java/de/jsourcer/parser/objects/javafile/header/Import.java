package de.jsourcer.parser.objects.javafile.header;

import org.jetbrains.annotations.NotNull;

public class Import {
    private final String path;
    private final boolean staticImport;

    public Import(@NotNull String path, boolean staticImport) {
        this.path = path;
        this.staticImport = staticImport;
    }

    @NotNull
    public String getPath() {
        return path;
    }

    public boolean isStaticImport() {
        return staticImport;
    }

    @Override
    public String toString() {
        return "Import{" +
                "path='" + path + '\'' +
                ", staticImport=" + staticImport +
                '}';
    }
}
