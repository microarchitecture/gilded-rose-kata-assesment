package com.gildedrose.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class NormalUpdateStrategyTest {

    private static final String NORMAL_ITEM_NAME = "Normal Item";

    @Test
    void qualityDegradesByOneBeforeSellByDate() {
        Item[] items = new Item[] { new Item(NORMAL_ITEM_NAME, 5, 10) };

        new GildedRose(items).updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(9, items[0].quality);
    }

    @Test
    void qualityDegradesTwoTimesFasterAtSellInDate() {
        Item[] items = new Item[] { new Item(NORMAL_ITEM_NAME, 0, 10) };

        new GildedRose(items).updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }

    @Test
    void qualityDegradesTwoTimesFasterAfterSellByDate() {
        Item[] items = new Item[] { new Item(NORMAL_ITEM_NAME, -1, 10) };

        new GildedRose(items).updateQuality();

        assertEquals(-2, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }

    @Test
    void qualityMinimumValueIsZero() {
        Item[] items = new Item[] { new Item(NORMAL_ITEM_NAME, 5, 0) };

        new GildedRose(items).updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void qualityMaximumValueIsFifty() {
        Item[] items = new Item[] { new Item(NORMAL_ITEM_NAME, 5, 50) };

        new GildedRose(items).updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(49, items[0].quality);
    }
}
