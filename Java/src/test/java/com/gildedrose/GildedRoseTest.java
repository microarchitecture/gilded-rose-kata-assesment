package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        void qualityDegradesTwoTimesFasterAfterSellByDate() {
            Item[] items = new Item[] { new Item(NORMAL_ITEM_NAME, 0, 10) };

            new GildedRose(items).updateQuality();

            assertEquals(-1, items[0].sellIn);
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

            assertEquals(49, items[0].quality);
        }
    }

    @Nested
    @DisplayName("Aged Brie suite")
    class AgedBrie {

        @Test
        void qualityIncreasesEachDayBeforeSellByDate() {
        }

        @Test
        void qualityIncreasesEachDayAfterSellByDate() {
        }

        @Test
        void qualityIsFiftyWhenStartingAtFortyNineAfterSellByDate() {
        }

        @Test
        void qualityMaximumValueIsFifty() {
        }
    }

    @Nested
    @DisplayName("Backstage passes")
    class BackstagePasses {

        @Test
        void qualityIncreasesWhenMoreThanTenDaysBeforeConcert() {
        }

        @Test
        void qualityIncreasesTwoTimesWhenTenDaysOrLessBeforeConcert() {
        }

        @Test
        void qualityIncreasesThreeTimesWhenFiveDaysOrLessBeforeConcert() {
        }

        @Test
        void qualityIsZeroAfterConcert() {
        }

        @Test
        void qualityMaximumValueIsFifty() {
        }
    }

    @Nested
    @DisplayName("Sulfuras suite")
    class Sulfuras {

        @Test
        void sellInNeverDecreases() {
        }

        @Test
        void qualityNeverDecreases() {
        }

        @Test
        void qualityMaximumValueIsEighty() {
        }
    }

    @Nested
    @DisplayName("Conjured item suite")
    class ConjuredItem {

        @Test
        void qualityDegradesTwoTimesFasterThanNormalBeforeSellBy() {
        }

        @Test
        void qualityDegradesTwoTimesFasterThanNormalAfterSellBy() {
        }

        @Test
        void qualityMinimumValueIsZero() {
        }

        @Test
        void qualityMaximumValueIsFifty() {
        }
    }
}
