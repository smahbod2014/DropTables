package com.koda.droptables;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DropTableTest {
    @Test
    public void testNothingWeight() throws Exception {
        DropTable dropTable = new DropTable(70, 20, 10, 0);
        int seed = 50;
        assertThat(dropTable.generateDropType(seed)).isEqualTo(DropType.NOTHING);
    }

    @Test
    public void testNothingWeightEdgeBelow() throws Exception {
        DropTable dropTable = new DropTable(70, 20, 10, 0);
        int seed = 69;
        assertThat(dropTable.generateDropType(seed)).isEqualTo(DropType.NOTHING);
    }

    @Test
    public void testNothingWeightEdgeExactly() throws Exception {
        DropTable dropTable = new DropTable(70, 20, 10, 0);
        int seed = 70;
        assertThat(dropTable.generateDropType(seed)).isNotEqualTo(DropType.NOTHING);
    }

    @Test
    public void testNothingWeightEdgeAbove() throws Exception {
        DropTable dropTable = new DropTable(70, 20, 10, 0);
        int seed = 71;
        assertThat(dropTable.generateDropType(seed)).isNotEqualTo(DropType.NOTHING);
    }

    @Test
    public void testGoldWeight() throws Exception {
        DropTable dropTable = new DropTable(70, 20, 10, 0);
        int seed = 80;
        assertThat(dropTable.generateDropType(seed)).isEqualTo(DropType.GOLD);
    }

    @Test
    public void testEquipmentWeight() throws Exception {
        DropTable dropTable = new DropTable(70, 20, 10, 0);
        int seed = 99;
        assertThat(dropTable.generateDropType(seed)).isEqualTo(DropType.EQUIPMENT);
    }

    @Test
    public void testRuneWeight() throws Exception {
        DropTable dropTable = new DropTable(70, 19, 10, 1);
        int seed = 99;
        assertThat(dropTable.generateDropType(seed)).isEqualTo(DropType.RUNES);
    }
}