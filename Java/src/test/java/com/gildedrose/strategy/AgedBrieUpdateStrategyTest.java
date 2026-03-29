package com.gildedrose.strategy;

import static com.gildedrose.ItemNames.AGED_BRIE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AgedBrieUpdateStrategyTest {

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
    @MethodSource("agedBrieCases")
    void qualityAfterEachDayIs(Item[] backstagePassesItems, int expectedSellIn, int expectedQuality) {

        new GildedRose(backstagePassesItems).updateQuality();

        assertEquals(expectedSellIn, backstagePassesItems[0].sellIn);
        assertEquals(expectedQuality, backstagePassesItems[0].quality);
    }

    private static Stream<Arguments> agedBrieCases() {
        return Stream.of(
            Arguments.of(new Item[] { new Item(AGED_BRIE_NAME, 3, 20) }, 2, 21),
            Arguments.of(new Item[] { new Item(AGED_BRIE_NAME, 2, 21) }, 1, 22),
            Arguments.of(new Item[] { new Item(AGED_BRIE_NAME, 1, 22) }, 0, 23),
            Arguments.of(new Item[] { new Item(AGED_BRIE_NAME, 0, 23) }, -1, 25),
            Arguments.of(new Item[] { new Item(AGED_BRIE_NAME, -1, 25) }, -2, 27));
    }
}
