package com.gildedrose.strategy;

import com.gildedrose.Item;

/**
 * Strategy that updates Normal items per default rules:
 * - each day, {@link Item#sellIn} and {@link Item#quality} decrease by 1.
 * - if {@link Item#sellIn} date passed, {@link Item#quality} decreases by 1 again that same day (twice faster).
 * - {@link Item#quality} never above 50. - {@link Item#quality} never goes below 0.
 */
public class NormalUpdateStrategy implements ItemUpdateStrategy {

    private final ItemUpdateRules updateRules;

    public NormalUpdateStrategy(ItemUpdateRules updateRules) {
        this.updateRules = updateRules;
    }

    @Override
    public void update(Item item) {
        updateRules.decrementQuality(item);
        updateRules.decrementSellIn(item);
        if (updateRules.hasSellInDayPassed(item)) {
            updateRules.decrementQuality(item);
        }
    }
}
