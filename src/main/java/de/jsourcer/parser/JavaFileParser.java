package de.jsourcer.parser;

import de.jsourcer.parser.elements.ImportElement;
import de.jsourcer.parser.elements.ModifierElement;
import de.jsourcer.parser.elements.NoneElement;
import de.jsourcer.parser.misc.Buffer;
import de.jsourcer.parser.misc.IndexedCharArray;
import de.jsourcer.parser.objects.clazz.AbstractClazz;
import de.jsourcer.parser.objects.javafile.JavaFile;
import de.jsourcer.parser.objects.javafile.header.FileHeader;

import java.util.Optional;
import de.jsourcer.parser.elements.*;
import de.jsourcer.parser.types.ModifierType;

public class JavaFileParser {
    private final IndexedCharArray charArray;

    public JavaFileParser(IndexedCharArray charArray) {
        this.charArray = charArray;
    }

    public void parse() {
        ElementReader reader = new ElementReader();
        JavaFile javaFile = null;

        boolean inHeader = true;
        while (!charArray.indexAtEnd()) {
            reader.readNextElement(charArray);
            if(inHeader) {
                if(inHeader = isHeader(reader)) {
                    if(javaFile == null) {
                        Optional<FileHeader> optional = headerParse(reader, null);
                        if(optional.isPresent()) javaFile = new JavaFile(optional.get());
                    }else {
                        headerParse(reader, javaFile.getHeader());
                    }
                    continue;
                }
            }
            if(reader.getBuffer().getLatest() instanceof ScopeElement) {
                AbstractClazz.parse(reader.getBuffer(), javaFile, Optional.empty()).ifPresent(abstractClazz -> {
                    System.out.println(abstractClazz);
                });
            }
        }
    }

    private boolean isHeader(ElementReader reader) {
        if(!reader.getBuffer().isValid(0)) return true;
        AbstractElement element = reader.getBuffer().getLatest();
        return element instanceof PackageElement
                || element instanceof NoneElement
                || element instanceof ImportElement
                || element instanceof SeparatorElement
                || (element instanceof ModifierElement modifierElement
                && modifierElement.getModifier() == ModifierType.STATIC);
    }

    private Optional<FileHeader> headerParse(ElementReader reader, FileHeader header) {
        Buffer<AbstractElement> buffer = reader.getBuffer();
        if(!(buffer.getLatest() instanceof SeparatorElement)) return Optional.empty();
        int index = 0;
        AbstractElement element = buffer.get(index);
        if(element instanceof PackageElement packageElement && buffer.get(++index) instanceof NoneElement noneElement) {
            buffer.clear();
            return Optional.of(new FileHeader(packageElement.toPackage(noneElement)));
        }
        if(element instanceof ImportElement importElement && header != null) {
            AbstractElement next = buffer.get(++index);
            if(next instanceof NoneElement noneElement) {
                header.getImports().add(importElement.toImport(noneElement, false));
                buffer.clear();
            }else if(buffer.isValid(2)) {
                if(next instanceof ModifierElement modifierElement
                        && modifierElement.getModifier() == ModifierType.STATIC
                        && buffer.get(++index) instanceof NoneElement noneElement) {
                    header.getImports().add(importElement.toImport(noneElement, true));
                    buffer.clear();
                }else {
                    throw new RuntimeException("not allowed stuff here"); //TODO: better exceptions
                }
            }
        }else {
            throw new RuntimeException("missing Package in java file"); //TODO: better exceptions
        }
        return Optional.of(header);
    }
}
