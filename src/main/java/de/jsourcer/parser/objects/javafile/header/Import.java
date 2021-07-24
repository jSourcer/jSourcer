package de.jsourcer.parser.objects.javafile.header;

public class Import {
    private final String path;
    private final boolean staticImport;

    public Import(String path, boolean staticImport) {
        this.path = path;
        this.staticImport = staticImport;
    }

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
