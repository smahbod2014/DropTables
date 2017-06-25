package com.koda.droptables;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MonsterGenerator {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final ItemGenerator itemGenerator;
    private List<Monster> monsters;
    private List<Area> areas;

    public MonsterGenerator(ItemGenerator itemGenerator) throws IOException {
        loadMonsters();
        loadAreas();
        this.itemGenerator = itemGenerator;
    }

    private void loadMonsters() throws IOException {
        monsters = MAPPER.readValue(new File("monsters.json"), new TypeReference<List<Monster>>() {});
    }

    private void loadAreas() throws IOException {
        areas = MAPPER.readValue(new File("areas.json"), new TypeReference<List<Area>>() {});
    }

    public Optional<Drop> killMonster(String monsterName, String areaName) {
        Optional<Monster> monsterOpt = monsters.stream()
                .filter(m -> m.getName().equals(monsterName))
                .filter(m -> m.getArea().equals(areaName))
                .findAny();
        if (!monsterOpt.isPresent()) {
            System.out.println(String.format("Monster %s doesn't exist in area %s", monsterName, areaName));
            return Optional.empty();
        }
        return itemGenerator.generateDrop(monsterOpt.get().getQualityLevel());
    }
}
