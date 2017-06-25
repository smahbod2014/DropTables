package com.koda.droptables;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableSortedSet;
import com.koda.droptables.utils.WeightedEntry;

import java.util.*;

public class DropTable {
    private static final Random random = new Random();
    private final int nothingWeight;
    private final int goldWeight;
    private final int equipmentWeight;
    private final int runeWeight;
    private final NavigableMap<Integer, DropType> table = new TreeMap<>();
    private final SortedSet<DropEntry> set;
    private int totalWeight = 0;

    @JsonCreator
    public DropTable(@JsonProperty("nothing") int nothingWeight,
                     @JsonProperty("gold") int goldWeight,
                     @JsonProperty("equipment") int equipmentWeight,
                     @JsonProperty("runes") int runeWeight) {
        this.nothingWeight = nothingWeight;
        this.goldWeight = goldWeight;
        this.equipmentWeight = equipmentWeight;
        this.runeWeight = runeWeight;
        set = ImmutableSortedSet.of(new DropEntry(DropType.NOTHING, nothingWeight),
                                                         new DropEntry(DropType.GOLD, goldWeight),
                                                         new DropEntry(DropType.EQUIPMENT, equipmentWeight),
                                                         new DropEntry(DropType.RUNES, runeWeight));
        for (DropEntry entry : set) {
            if (entry.weight > 0) {
                registerDropType(entry.dropType, entry.weight);
            }
        }
    }

    private void registerDropType(DropType dropType, int weight) {
        totalWeight += weight;
        table.put(totalWeight, dropType);
    }

    DropType generateDropType(int r) {
        System.out.println("Roll: " + r);
        return table.higherEntry(r).getValue();
    }

    public DropType generateDropType() {
        return generateDropType(random.nextInt(totalWeight));
    }

    private double getProbability(DropType dropType) {
        Optional<DropEntry> drop = set.stream().filter(d -> d.dropType == dropType).findAny();
        if (!drop.isPresent()) {
            throw new RuntimeException(dropType + " not found");
        }
        return (double) drop.get().weight / totalWeight;
    }

    @Override
    public String toString() {
        return String.format("DropTable: {" +
                "Nothing: %d (%.2f%%), " +
                "Gold: %d (%.2f%%), " +
                "Equipment: %d (%.2f%%), " +
                "Runes: %d (%.2f%%)" +
                "}",
                             nothingWeight, getProbability(DropType.NOTHING) * 100,
                             goldWeight, getProbability(DropType.GOLD) * 100,
                             equipmentWeight, getProbability(DropType.EQUIPMENT) * 100,
                             runeWeight, getProbability(DropType.RUNES) * 100);
    }

    private static final class DropEntry implements Comparable<DropEntry> {
        DropType dropType;
        int weight;

        DropEntry(DropType dropType, int weight) {
            this.dropType = dropType;
            this.weight = weight;
        }

        @Override
        public int compareTo(DropEntry o) {
            return o.weight - weight;
        }
    }
}
