package org.example;

public class Space {
    private final String name;

    public Space(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public String toString() { return name; }
}
