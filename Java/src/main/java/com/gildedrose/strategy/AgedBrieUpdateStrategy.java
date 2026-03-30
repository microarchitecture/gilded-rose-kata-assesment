package com.gildedrose.strategy;

import com.gildedrose.Item;

/**
 * Strategy that updates Aged Brie items. Rules are the following:
 * - {@link Item#quality} increases by 1 each day.
 * - {@code sellIn} decreases by 1 each day.
 * - if {@link Item#sellIn} date passed, {@link Item#quality} increases by 1 again that same day.
 * - {@link Item#quality} never above 50.
 */
public class AgedBrieUpdateStrategy implements ItemUpdateStrategy {

    private final ItemUpdateRules updateRules;

    public AgedBrieUpdateStrategy(ItemUpdateRules updateRules) {
        this.updateRules = updateRules;
    }

    @Override
    public void update(Item item) {
        updateRules.incrementQuality(item);
        updateRules.decrementSellIn(item);
        if (updateRules.hasSellInDayPassed(item)) {
            updateRules.incrementQuality(item);
        }
    }
}
