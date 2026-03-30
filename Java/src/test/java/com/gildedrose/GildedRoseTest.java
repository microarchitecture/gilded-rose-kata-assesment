package com.gildedrose;

import static com.gildedrose.ItemNames.AGED_BRIE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Test
    void updateQualityAppliesStrategies() {
        Item[] items = new Item[] {
            new Item("Unexisting Item", 10, 20),
            new Item(AGED_BRIE_NAME, 2, 0),
        };

        new GildedRose(items).updateQuality();

        Item defaultItem = items[0];
        assertEquals(9, defaultItem.sellIn);
        assertEquals(19, defaultItem.quality);

        Item agedBrieItem = items[1];
        assertEquals(1, agedBrieItem.sellIn);
        assertEquals(1, agedBrieItem.quality);
    }
}
