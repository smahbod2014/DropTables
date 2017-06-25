package com.koda.droptables;

public class Gold implements Drop {
    private final int amount;

    public Gold(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public DropType getType() {
        return DropType.GOLD;
    }

    @Override
    public String getName() {
        return "Gold";
    }

    @Override
    public String toString() {
        return "Gold: " + amount;
    }
}
