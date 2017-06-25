package com.koda.droptables;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Rune implements Drop {
    private final String name;
    private final int qualityLevel;
    private final int weight;

    @JsonCreator
    public Rune(@JsonProperty("name") String name,
                @JsonProperty("qualityLevel") int qualityLevel,
                @JsonProperty("weight") int weight) {
        this.name = name;
        this.qualityLevel = qualityLevel;
        this.weight = weight;
    }

    public int getQualityLevel() {
        return qualityLevel;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public DropType getType() {
        return DropType.RUNES;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Rune: {Name: %s}", name);
    }
}
