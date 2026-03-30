package com.gildedrose.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class NormalUpdateStrategyTest {

    private static final String NORMAL_ITEM_NAME = "Normal Item";

    private final NormalUpdateStrategy updateStrategy = new NormalUpdateStrategy(new ItemUpdateRules());

    @Test
    void qualityDegradesByOneBeforeSellByDate() {
        Item item = new Item(NORMAL_ITEM_NAME, 5, 10);

        updateStrategy.update(item);

        assertEquals(4, item.sellIn);
        assertEquals(9, item.quality);
    }

    @Test
    void qualityDegradesTwoTimesFasterAtSellInDate() {
        Item item = new Item(NORMAL_ITEM_NAME, 0, 10);

        updateStrategy.update(item);

        assertEquals(-1, item.sellIn);
        assertEquals(8, item.quality);
    }

    @Test
    void qualityDegradesTwoTimesFasterAfterSellByDate() {
        Item item = new Item(NORMAL_ITEM_NAME, -1, 10);

        updateStrategy.update(item);

        assertEquals(-2, item.sellIn);
        assertEquals(8, item.quality);
    }

    @Test
    void qualityMinimumValueIsZero() {
        Item item = new Item(NORMAL_ITEM_NAME, 5, 0);

        updateStrategy.update(item);

        assertEquals(4, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void qualityMaximumValueIsFifty() {
        Item item = new Item(NORMAL_ITEM_NAME, 5, 50);

        updateStrategy.update(item);

        assertEquals(4, item.sellIn);
        assertEquals(49, item.quality);
    }
}
