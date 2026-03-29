package com.gildedrose.strategy;

import static com.gildedrose.ItemRuleConstants.QUALITY_DEFAULT_CHANGE;
import static com.gildedrose.ItemRuleConstants.QUALITY_MAX_VALUE;

import com.gildedrose.Item;

/**
 * Strategy that updates Aged Brie items. Rules are the following:
 * - {@link Item#quality} increases by 1 each day.
 * - {@code sellIn} decreases by 1 each day.
 * - if {@link Item#sellIn} date passed, {@link Item#quality} increases by 1 again that same day.
 * - {@link Item#quality} never above 50.
 */
public class AgedBrieUpdateStrategy implements ItemUpdateStrategy {

    @Override
    public void update(Item item) {
        incrementQualityByDefault(item);
        decrementSellIn(item);
        if (hasSellInDayPassed(item)) {
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

    private boolean hasSellInDayPassed(Item item) {
        return item.sellIn < 0;
    }
}
