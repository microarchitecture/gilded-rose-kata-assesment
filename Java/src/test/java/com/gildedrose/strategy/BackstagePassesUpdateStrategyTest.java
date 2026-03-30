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

    private final BackstagePassesUpdateStrategy updateStrategy = new BackstagePassesUpdateStrategy(
        new ItemUpdateRules());

    @Test
    void qualityIncreasesWhenMoreThanTenDaysBeforeConcert() {
        Item item = new Item(BACKSTAGE_PASSES_NAME, 15, 10);

        updateStrategy.update(item);

        assertEquals(14, item.sellIn);
        assertEquals(11, item.quality);
    }

    @Test
    void qualityIncreasesTwoTimesFasterWhenTenDaysOrLessBeforeConcert() {
        Item item = new Item(BACKSTAGE_PASSES_NAME, 10, 30);

        updateStrategy.update(item);

        assertEquals(9, item.sellIn);
        assertEquals(32, item.quality);
    }

    @Test
    void qualityIncreasesThreeTimesFasterWhenFiveDaysOrLessBeforeConcert() {
        Item item = new Item(BACKSTAGE_PASSES_NAME, 5, 40);

        updateStrategy.update(item);

        assertEquals(4, item.sellIn);
        assertEquals(43, item.quality);
    }

    @Test
    void qualityIsZeroAtTheConcertDay() {
        Item item = new Item(BACKSTAGE_PASSES_NAME, 0, 50);

        updateStrategy.update(item);

        assertEquals(-1, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void qualityIsZeroAfterTheConcert() {
        Item item = new Item(BACKSTAGE_PASSES_NAME, -1, 50);

        updateStrategy.update(item);

        assertEquals(-2, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void qualityMaximumValueIsFifty() {
        Item item = new Item(BACKSTAGE_PASSES_NAME, 4, 50);

        updateStrategy.update(item);

        assertEquals(3, item.sellIn);
        assertEquals(50, item.quality);
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
