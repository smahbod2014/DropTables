package com.koda.droptables.utils;

import java.util.*;

public class RandomCollection<E> {
    private final NavigableMap<Integer, E> map = new TreeMap<>();
    private final SortedSet<WeightedEntry<E>> initializingItems = new TreeSet<>();
    private final Random random;
    private int total = 0;
    private boolean built = false;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public RandomCollection<E> submit(int weight, E item) {
        if (built) {
            throw new IllegalStateException("Can't submit items after collection is built: " + weight + ", " + item);
        }
        if (weight > 0) {
            initializingItems.add(new WeightedEntry<>(weight, item));
        }
        return this;
    }

    public RandomCollection<E> build() {
        for (WeightedEntry<E> entry : initializingItems) {
            total += entry.getWeight();
            map.put(total, entry.getItem());
        }
        built = true;
        return this;
    }

    public E get() {
        if (!built) {
            throw new IllegalStateException("RandomCollection hasn't been built yet");
        }
        // TODO: No items could be generated for this drop. Instead, rebalance weights to drop other items.
        if (map.isEmpty()) {
            return null;
        }
        int value = random.nextInt(total);
        return map.higherEntry(value).getValue();
    }
}