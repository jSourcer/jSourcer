package de.jsourcer.parser.objects;

import org.jetbrains.annotations.NotNull;

public class Scope {
    private final String body;

    public Scope(@NotNull String body) {
        this.body = body;
    }

    @NotNull
    public String getBody() {
        return body;
    }
}
