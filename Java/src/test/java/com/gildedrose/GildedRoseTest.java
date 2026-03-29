package com.gildedrose;

import static com.gildedrose.ItemNames.AGED_BRIE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

    @Nested
    @DisplayName("Conjured item suite")
    class ConjuredItem {

        private static final String CONJURED_NAME = "Conjured Gouda";

        @Test
        @Disabled("should be enabled as soon as business rules are added")
        void qualityDegradesTwoTimesFasterThanNormalBeforeSellBy() {
            Item[] items = new Item[] { new Item(CONJURED_NAME, 10, 30) };

            new GildedRose(items).updateQuality();

            assertEquals(9, items[0].sellIn);
            assertEquals(28, items[0].quality);
        }

        @Test
        @Disabled("should be enabled as soon as business rules are added")
        void qualityDegradesTwoTimesFasterThanNormalAfterSellBy() {
            Item[] items = new Item[] { new Item(CONJURED_NAME, -1, 10) };

            new GildedRose(items).updateQuality();

            assertEquals(-2, items[0].sellIn);
            assertEquals(6, items[0].quality);
        }

        @Test
        void qualityMinimumValueIsZero() {
            Item[] items = new Item[] { new Item(CONJURED_NAME, 0, 0) };

            new GildedRose(items).updateQuality();

            assertEquals(-1, items[0].sellIn);
            assertEquals(0, items[0].quality);
        }

        @Test
        @Disabled("should be enabled as soon as business rules are added")
        void qualityMaximumValueIsFifty() {
            Item[] items = new Item[] { new Item(CONJURED_NAME, 10, 50) };

            new GildedRose(items).updateQuality();

            assertEquals(9, items[0].sellIn);
            assertEquals(48, items[0].quality);
        }
    }
}
