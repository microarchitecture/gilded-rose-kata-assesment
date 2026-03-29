package com.gildedrose.strategy;

import static com.gildedrose.ItemRuleConstants.QUALITY_DEFAULT_CHANGE;
import static com.gildedrose.ItemRuleConstants.QUALITY_MAX_VALUE;

import com.gildedrose.Item;

/**
 * Strategy that updates Backstage Passes items. Rules are the following:
 * - {@link Item#quality} increases by 1 each day, increases by 2 when {@code sellIn} is 10 days or
 *   less, and increases by 3 when {@code sellIn} 5 days or less.
 * - {@code sellIn} decreases by 1 each day.
 * - if {@link Item#sellIn} date passed, {@link Item#quality} becomes 0.
 * - {@link Item#quality} never above 50.
 */
public class BackstagePassesUpdateStrategy implements ItemUpdateStrategy {

    private static final int BACKSTAGE_PASSES_FIRST_QUALITY_THRESHOLD = 10;
    private static final int BACKSTAGE_PASSES_SECOND_QUALITY_THRESHOLD = 5;

    @Override
    public void update(Item item) {
        incrementBackstagePassesQuality(item);
        decrementSellIn(item);
        if (hasSellInDayPassed(item)) {
            decrementQuality(item);
        }
    }

    private void incrementBackstagePassesQuality(Item item) {
        incrementQualityByDefault(item);
        if (item.sellIn <= BACKSTAGE_PASSES_FIRST_QUALITY_THRESHOLD) {
            incrementQualityByDefault(item);
        }
        if (item.sellIn <= BACKSTAGE_PASSES_SECOND_QUALITY_THRESHOLD) {
            incrementQualityByDefault(item);
        }
    }

    private void incrementQualityByDefault(Item item) {
        if (item.quality < QUALITY_MAX_VALUE) {
            item.quality = item.quality + QUALITY_DEFAULT_CHANGE;
        }
    }

    private void decrementSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void decrementQuality(Item item) {
        item.quality = item.quality - item.quality;
    }

    private boolean hasSellInDayPassed(Item item) {
        return item.sellIn < 0;
    }
}
