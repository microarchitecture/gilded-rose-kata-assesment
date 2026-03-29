package com.gildedrose;

import static com.gildedrose.GildedRoseTest.AgedBrie.AGED_BRIE_NAME;
import static com.gildedrose.GildedRoseTest.BackstagePasses.BACKSTAGE_PASSES_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
    @DisplayName("Aged Brie suite")
    class AgedBrie {

        protected static final String AGED_BRIE_NAME = "Aged Brie";

        @Test
        void qualityIncreasesEachDayBeforeSellByDate() {
            Item[] items = new Item[] { new Item(AGED_BRIE_NAME, 5, 10) };

            new GildedRose(items).updateQuality();

            assertEquals(4, items[0].sellIn);
            assertEquals(11, items[0].quality);
        }

        @Test
        void qualityIncreasesTwoTimesFasterAfterSellByDate() {
            Item[] items = new Item[] { new Item(AGED_BRIE_NAME, 0, 30) };

            new GildedRose(items).updateQuality();

            assertEquals(-1, items[0].sellIn);
            assertEquals(32, items[0].quality);
        }

        @Test
        void qualityIsFiftyWhenStartingAtFortyNineAfterSellByDate() {
            Item[] items = new Item[] { new Item(AGED_BRIE_NAME, -1, 49) };

            new GildedRose(items).updateQuality();

            assertEquals(-2, items[0].sellIn);
            assertEquals(50, items[0].quality);
        }

        @Test
        void qualityMaximumValueIsFifty() {
            Item[] items = new Item[] { new Item(AGED_BRIE_NAME, 10, 50) };

            new GildedRose(items).updateQuality();

            assertEquals(9, items[0].sellIn);
            assertEquals(50, items[0].quality);
        }

        @ParameterizedTest(name = "At the end of the day, expected sellIn is {1}, and quality is {2}")
        @MethodSource("com.gildedrose.GildedRoseTest#agedBrieCases")
        void qualityAfterEachDayIs(Item[] backstagePassesItems, int expectedSellIn, int expectedQuality) {

            new GildedRose(backstagePassesItems).updateQuality();

            assertEquals(expectedSellIn, backstagePassesItems[0].sellIn);
            assertEquals(expectedQuality, backstagePassesItems[0].quality);
        }

    }

    private static Stream<Arguments> agedBrieCases() {
        return Stream.of(
            Arguments.of(new Item[] { new Item(AGED_BRIE_NAME, 3, 20) }, 2, 21),
            Arguments.of(new Item[] { new Item(AGED_BRIE_NAME, 2, 21) }, 1, 22),
            Arguments.of(new Item[] { new Item(AGED_BRIE_NAME, 1, 22) }, 0, 23),
            Arguments.of(new Item[] { new Item(AGED_BRIE_NAME, 0, 23) }, -1, 25),
            Arguments.of(new Item[] { new Item(AGED_BRIE_NAME, -1, 25) }, -2, 27));
    }

    @Nested
    @DisplayName("Backstage passes suite")
    class BackstagePasses {

        protected static final String BACKSTAGE_PASSES_NAME = "Backstage passes to a TAFKAL80ETC concert";

        @Test
        void qualityIncreasesWhenMoreThanTenDaysBeforeConcert() {
            Item[] items = new Item[] { new Item(BACKSTAGE_PASSES_NAME, 15, 10) };

            new GildedRose(items).updateQuality();

            assertEquals(14, items[0].sellIn);
            assertEquals(11, items[0].quality);
        }

        @Test
        void qualityIncreasesTwoTimesFasterWhenTenDaysOrLessBeforeConcert() {
            Item[] items = new Item[] { new Item(BACKSTAGE_PASSES_NAME, 10, 30) };

            new GildedRose(items).updateQuality();

            assertEquals(9, items[0].sellIn);
            assertEquals(32, items[0].quality);
        }

        @Test
        void qualityIncreasesThreeTimesFasterWhenFiveDaysOrLessBeforeConcert() {
            Item[] items = new Item[] { new Item(BACKSTAGE_PASSES_NAME, 5, 40) };

            new GildedRose(items).updateQuality();

            assertEquals(4, items[0].sellIn);
            assertEquals(43, items[0].quality);
        }

        @Test
        void qualityIsZeroAtTheConcertDay() {
            Item[] items = new Item[] { new Item(BACKSTAGE_PASSES_NAME, 0, 50) };

            new GildedRose(items).updateQuality();

            assertEquals(-1, items[0].sellIn);
            assertEquals(0, items[0].quality);
        }

        @Test
        void qualityIsZeroAfterTheConcert() {
            Item[] items = new Item[] { new Item(BACKSTAGE_PASSES_NAME, -1, 50) };

            new GildedRose(items).updateQuality();

            assertEquals(-2, items[0].sellIn);
            assertEquals(0, items[0].quality);
        }

        @Test
        void qualityMaximumValueIsFifty() {
            Item[] items = new Item[] { new Item(BACKSTAGE_PASSES_NAME, 4, 50) };

            new GildedRose(items).updateQuality();

            assertEquals(3, items[0].sellIn);
            assertEquals(50, items[0].quality);
        }

        @ParameterizedTest(name = "At the end of the day, expected sellIn is {1}, and quality is {2}")
        @MethodSource("com.gildedrose.GildedRoseTest#backstagePassesCases")
        void qualityAfterEachDayIs(Item[] backstagePassesItems, int expectedSellIn, int expectedQuality) {

            new GildedRose(backstagePassesItems).updateQuality();

            assertEquals(expectedSellIn, backstagePassesItems[0].sellIn);
            assertEquals(expectedQuality, backstagePassesItems[0].quality);
        }
    }

    private static Stream<Arguments> backstagePassesCases() {
        return Stream.of(
            Arguments.of(new Item[] { new Item(BACKSTAGE_PASSES_NAME, 11, 24) }, 10, 25),
            Arguments.of(new Item[] { new Item(BACKSTAGE_PASSES_NAME, 10, 25) }, 9, 27),
            Arguments.of(new Item[] { new Item(BACKSTAGE_PASSES_NAME, 9, 27) }, 8, 29),
            Arguments.of(new Item[] { new Item(BACKSTAGE_PASSES_NAME, 8, 29) }, 7, 31),
            Arguments.of(new Item[] { new Item(BACKSTAGE_PASSES_NAME, 7, 31) }, 6, 33),
            Arguments.of(new Item[] { new Item(BACKSTAGE_PASSES_NAME, 6, 33) }, 5, 35),
            Arguments.of(new Item[] { new Item(BACKSTAGE_PASSES_NAME, 5, 35) }, 4, 38),
            Arguments.of(new Item[] { new Item(BACKSTAGE_PASSES_NAME, 4, 38) }, 3, 41),
            Arguments.of(new Item[] { new Item(BACKSTAGE_PASSES_NAME, 3, 41) }, 2, 44),
            Arguments.of(new Item[] { new Item(BACKSTAGE_PASSES_NAME, 2, 44) }, 1, 47),
            Arguments.of(new Item[] { new Item(BACKSTAGE_PASSES_NAME, 1, 47) }, 0, 50),
            Arguments.of(new Item[] { new Item(BACKSTAGE_PASSES_NAME, 0, 50) }, -1, 0),
            Arguments.of(new Item[] { new Item(BACKSTAGE_PASSES_NAME, -1, 0) }, -2, 0));
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
