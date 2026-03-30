package com.gildedrose.strategy;

import static com.gildedrose.ItemNames.CONJURED_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class ConjuredUpdateStrategyTest {

    private final ConjuredUpdateStrategy updateStrategy = new ConjuredUpdateStrategy(new ItemUpdateRules());

    @Test
    void qualityDegradesTwoTimesFasterThanNormalBeforeSellBy() {
        Item item = new Item(CONJURED_NAME, 10, 30);

        updateStrategy.update(item);

        assertEquals(9, item.sellIn);
        assertEquals(28, item.quality);
    }

    @Test
    void qualityDegradesTwoTimesFasterThanNormalAfterSellBy() {
        Item item = new Item(CONJURED_NAME, -1, 10);

        updateStrategy.update(item);

        assertEquals(-2, item.sellIn);
        assertEquals(6, item.quality);
    }

    @Test
    void qualityMinimumValueIsZero() {
        Item item = new Item(CONJURED_NAME, 0, 0);

        updateStrategy.update(item);

        assertEquals(-1, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void qualityMaximumValueIsFifty() {
        Item item = new Item(CONJURED_NAME, 10, 50);

        updateStrategy.update(item);

        assertEquals(9, item.sellIn);
        assertEquals(48, item.quality);
    }
}
