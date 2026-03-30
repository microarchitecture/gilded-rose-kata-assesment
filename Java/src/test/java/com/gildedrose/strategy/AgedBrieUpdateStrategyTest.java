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

    private final AgedBrieUpdateStrategy updateStrategy = new AgedBrieUpdateStrategy(new ItemUpdateRules());

    @Test
    void qualityIncreasesEachDayBeforeSellByDate() {
        Item item = new Item(AGED_BRIE_NAME, 5, 10);

        updateStrategy.update(item);

        assertEquals(4, item.sellIn);
        assertEquals(11, item.quality);
    }

    @Test
    void qualityIncreasesTwoTimesFasterAfterSellByDate() {
        Item item = new Item(AGED_BRIE_NAME, 0, 30);

        updateStrategy.update(item);

        assertEquals(-1, item.sellIn);
        assertEquals(32, item.quality);
    }

    @Test
    void qualityIsFiftyWhenStartingAtFortyNineAfterSellByDate() {
        Item item = new Item(AGED_BRIE_NAME, -1, 49);

        updateStrategy.update(item);

        assertEquals(-2, item.sellIn);
        assertEquals(50, item.quality);
    }

    @Test
    void qualityMaximumValueIsFifty() {
        Item item = new Item(AGED_BRIE_NAME, 10, 50);

        updateStrategy.update(item);

        assertEquals(9, item.sellIn);
        assertEquals(50, item.quality);
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
