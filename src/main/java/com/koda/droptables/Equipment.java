package com.koda.droptables;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Equipment implements Drop {
    private final String name;
    private final int qualityLevel;

    @JsonCreator
    public Equipment(@JsonProperty("name") String name, @JsonProperty("qualityLevel") int qualityLevel) {
        this.name = name;
        this.qualityLevel = qualityLevel;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getQualityLevel() {
        return qualityLevel;
    }

    @Override
    public String toString() {
        return String.format("Equipment: {Name: %s, Quality: %d}", name, qualityLevel);
    }

    @Override
    public DropType getType() {
        return DropType.EQUIPMENT;
    }
}
