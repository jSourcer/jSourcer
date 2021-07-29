package de.jsourcer.parser.objects.javafile.header;

import org.jetbrains.annotations.NotNull;

public class Package {

    private final String path;

    public Package(@NotNull String path) {
        this.path = path;
    }

    @NotNull
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
