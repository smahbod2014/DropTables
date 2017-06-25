package com.koda.droptables;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.*;
import com.koda.droptables.utils.RandomCollection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemGenerator {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private ListMultimap<Integer, Equipment> itemsByQualityLevel;
    private ListMultimap<Integer, Rune> runesByQualityLevel;
    private DropTable dropTable;

    public ItemGenerator() throws IOException {
        loadEquipmentFile();
        loadDropTable();
        loadRunesFile();
        System.out.println("Loaded Drop Table: " + dropTable);
    }

    private void loadDropTable() throws IOException {
        dropTable = MAPPER.readValue(new File("drop-table.json"), DropTable.class);
    }

    private void loadEquipmentFile() throws IOException {
        List<Equipment> equipment = MAPPER.readValue(new File("equipment.json"), new TypeReference<List<Equipment>>() {});
        itemsByQualityLevel = Multimaps.index(equipment, Equipment::getQualityLevel);
    }

    private void loadRunesFile() throws IOException {
        List<Rune> equipment = MAPPER.readValue(new File("runes.json"), new TypeReference<List<Rune>>() {});
        runesByQualityLevel = Multimaps.index(equipment, Rune::getQualityLevel);
    }

    public Optional<Drop> generateDrop(int qualityLevel) {
        DropType dropType = dropTable.generateDropType();
        switch (dropType) {
            case NOTHING:
                return Optional.empty();
            case GOLD:
                return Optional.of(new Gold((int) (500 + Math.random() * 500)));
            case EQUIPMENT:
                return Optional.of(getItem(qualityLevel));
            case RUNES:
                Rune rune = getRune(qualityLevel);
                if (rune == null) {
                    System.out.println("TODO! Drop rolled as rune, but there were no eligible runes to drop");
                }
                return Optional.ofNullable(rune);
            default:
                return Optional.empty();
        }
    }

    public Equipment getItem(int qualityLevel) {
        List<Equipment> equipment = new ArrayList<>();
        for (int i = 1; i <= qualityLevel; i++) {
            equipment.addAll(itemsByQualityLevel.get(i));
        }
        return equipment.get((int) (Math.random() * equipment.size()));
    }

    public Rune getRune(int qualityLevel) {
        RandomCollection<Rune> runes = new RandomCollection<>();
        for (int i = 1; i <= qualityLevel; i++) {
            runesByQualityLevel.get(i).forEach(rune -> runes.submit(rune.getWeight(), rune));
        }
        runes.build();
        return runes.get();
    }
}
