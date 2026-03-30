package com.gildedrose.strategy;

import com.gildedrose.Item;

/**
 * Strategy that updates Backstage Passes items. Rules are the following:
 * - {@link Item#quality} increases by 1 each day, increases by 2 when {@code sellIn} is 10 days or less, and increases
 * by 3 when {@code sellIn} 5 days or less.
 * - {@code sellIn} decreases by 1 each day.
 * - if {@link Item#sellIn} date passed, {@link Item#quality} becomes 0.
 * - {@link Item#quality} never above 50.
 */
public class BackstagePassesUpdateStrategy implements ItemUpdateStrategy {

    private static final int BACKSTAGE_PASSES_FIRST_QUALITY_THRESHOLD = 10;
    private static final int BACKSTAGE_PASSES_SECOND_QUALITY_THRESHOLD = 5;

    private final ItemUpdateRules updateRules;

    public BackstagePassesUpdateStrategy(ItemUpdateRules updateRules) {
        this.updateRules = updateRules;
    }

    @Override
    public void update(Item item) {
        incrementQuality(item);
        updateRules.decrementSellIn(item);
        if (updateRules.hasSellInDayPassed(item)) {
            resetQuality(item);
        }
    }

    private void incrementQuality(Item item) {
        updateRules.incrementQuality(item);
        if (item.sellIn <= BACKSTAGE_PASSES_FIRST_QUALITY_THRESHOLD) {
            updateRules.incrementQuality(item);
        }
        if (item.sellIn <= BACKSTAGE_PASSES_SECOND_QUALITY_THRESHOLD) {
            updateRules.incrementQuality(item);
        }
    }

    private void resetQuality(Item item) {
        item.quality = 0;
    }
}
