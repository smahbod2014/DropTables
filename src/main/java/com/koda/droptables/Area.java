package com.koda.droptables;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Area {
    private final String name;
    private final int treasureClass;

    @JsonCreator
    public Area(@JsonProperty("name") String name, @JsonProperty("treasureClass") int treasureClass) {
        this.name = name;
        this.treasureClass = treasureClass;
    }

    public String getName() {
        return name;
    }

    public int getTreasureClass() {
        return treasureClass;
    }

    @Override
    public String toString() {
        return String.format("Area: {Name: %s, Treasure Class: %d}", name, treasureClass);
    }
}
