package com.koda.droptables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PerformanceMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        ItemGenerator itemGenerator = new ItemGenerator();
        List<String> itemsToFind = new ArrayList<>();
        itemsToFind.add("Short Sword");
        itemsToFind.add("Scimitar");
        itemsToFind.add("Short Bow");
        itemsToFind.add("Hunter's Bow");
        itemsToFind.add("Wand");
        itemsToFind.add("Hand Axe");
        itemsToFind.add("El Rune");
        itemsToFind.add("Tal Rune");
        itemsToFind.add("Sol Rune");
        itemsToFind.add("Lum Rune");

        int attempts = 1;
        while (!itemsToFind.isEmpty()) {
            Optional<Drop> dropOpt = itemGenerator.generateDrop(17);
            if (dropOpt.isPresent()) {
                Drop drop = dropOpt.get();
                if (itemsToFind.contains(drop.getName())) {
                    System.out.println("Found " + drop.getName() + " after " + attempts + " attempts");
                    itemsToFind.remove(drop.getName());
                }
            }
            attempts++;
            Thread.sleep(2);
        }
    }
}
