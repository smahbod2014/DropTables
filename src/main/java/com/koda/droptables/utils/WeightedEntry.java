package com.koda.droptables.utils;

public class WeightedEntry<E> implements Comparable<WeightedEntry<E>> {
    private final int weight;
    private final E item;

    public WeightedEntry(int weight, E item) {
        this.weight = weight;
        this.item = item;
    }

    public int getWeight() {
        return weight;
    }

    public E getItem() {
        return item;
    }

    @Override
    public int compareTo(WeightedEntry<E> o) {
        return o.weight - weight;
    }
}
