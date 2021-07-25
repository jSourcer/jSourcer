package de.jsourcer.parser.objects.javafile;

import de.jsourcer.parser.objects.clazz.AbstractClazz;
import de.jsourcer.parser.objects.javafile.header.FileHeader;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class JavaFile {
    private final FileHeader header;
    private final List<AbstractClazz> clazzes = new ArrayList<>();

    public JavaFile(@NotNull FileHeader header) {
        this.header = header;
    }

    @NotNull
    public FileHeader getHeader() {
        return header;
    }

    @NotNull
    public List<AbstractClazz> getClazzes() {
        return clazzes;
    }
}
