package com.koda.droptables;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Monster {
    private final String name;
    private final int qualityLevel;
    private final String area;

    @JsonCreator
    public Monster(@JsonProperty("name") String name,
                   @JsonProperty("qualityLevel") int qualityLevel,
                   @JsonProperty("area") String area) {
        this.name = name;
        this.qualityLevel = qualityLevel;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public int getQualityLevel() {
        return qualityLevel;
    }

    public String getArea() {
        return area;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Quality: %d, Area: %s", name, qualityLevel, area);
    }
}
