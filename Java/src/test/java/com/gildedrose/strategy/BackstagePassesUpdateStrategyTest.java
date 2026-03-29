package com.gildedrose.strategy;

import static com.gildedrose.ItemNames.BACKSTAGE_PASSES_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BackstagePassesUpdateStrategyTest {

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
    @MethodSource("backstagePassesCases")
    void qualityAfterEachDayIs(Item[] backstagePassesItems, int expectedSellIn, int expectedQuality) {

        new GildedRose(backstagePassesItems).updateQuality();

        assertEquals(expectedSellIn, backstagePassesItems[0].sellIn);
        assertEquals(expectedQuality, backstagePassesItems[0].quality);
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
}
