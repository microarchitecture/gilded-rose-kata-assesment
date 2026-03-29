package com.gildedrose.strategy;

import static com.gildedrose.ItemNames.SULFURAS_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class SulfurasUpdateStrategyTest {

    @Test
    void sellInNeverDecreases() {
        Item[] items = new Item[] { new Item(SULFURAS_NAME, 15, 80) };

        new GildedRose(items).updateQuality();

        assertEquals(15, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    void qualityNeverDecreases() {
        Item[] items = new Item[] { new Item(SULFURAS_NAME, -1, 80) };

        new GildedRose(items).updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    void qualityConstantValueIsEighty() {
        Item[] items = new Item[] { new Item(SULFURAS_NAME, 0, 80) };

        new GildedRose(items).updateQuality();

        assertEquals(0, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }
}
