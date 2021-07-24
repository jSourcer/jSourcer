package de.jsourcer.parser.objects.javafile.header;

public class Package {
    private final String path;

    public Package(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Package{" +
                "path='" + path + '\'' +
                '}';
    }
}
