package com.gildedrose.strategy;

import static com.gildedrose.ItemRuleConstants.QUALITY_DEFAULT_CHANGE;
import static com.gildedrose.ItemRuleConstants.QUALITY_MIN_VALUE;

import com.gildedrose.Item;

/**
 * Strategy that updates Normal items per default rules:
 * - each day, {@link Item#sellIn} and {@link Item#quality} decrease by 1.
 * - if {@link Item#sellIn} date passed, {@link Item#quality} decreases by 1 again that same day (twice faster).
 * - {@link Item#quality} never goes below 0.
 */
public class NormalUpdateStrategy implements ItemUpdateStrategy {

    @Override
    public void update(Item item) {
        decrementQualityByDefault(item);
        decrementSellIn(item);
        if (hasSellInDayPassed(item)) {
            decrementQualityByDefault(item);
        }
    }

    private void decrementQualityByDefault(Item item) {
        if (item.quality > QUALITY_MIN_VALUE) {
            item.quality = item.quality - QUALITY_DEFAULT_CHANGE;
        }
    }

    private void decrementSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private boolean hasSellInDayPassed(Item item) {
        return item.sellIn < 0;
    }
}
