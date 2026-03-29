package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Nested
    @DisplayName("Normal Item suite")
    class NormalItem {

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

    @Nested
    @DisplayName("Sulfuras suite")
    class Sulfuras {

        private static final String SULFURAS_NAME = "Sulfuras, Hand of Ragnaros";

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
