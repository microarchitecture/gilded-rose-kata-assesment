package com.gildedrose.strategy;

import static com.gildedrose.ItemRuleConstants.QUALITY_MIN_VALUE;

import com.gildedrose.Item;

/**
 * Strategy that updates Conjured items. Rules are the following:
 *
 * - {@link Item#quality} degrades two times faster than normal item: up to two single-step decrements per day before
 *   the {@link Item#sellIn} date.
 * - if {@link Item#sellIn} date passed, {@link Item#quality} degrades two times faster again that same day.
 * - {@link Item#quality} never below 0.
 */
public class ConjuredUpdateStrategy implements ItemUpdateStrategy {

    private final ItemUpdateRules updateRules;

    public ConjuredUpdateStrategy(ItemUpdateRules updateRules) {
        this.updateRules = updateRules;
    }

    @Override
    public void update(Item item) {
        decrementQuality(item);
        updateRules.decrementSellIn(item);
        if (updateRules.hasSellInDayPassed(item)) {
            decrementQuality(item);
        }
    }

    public void decrementQuality(Item item) {
        updateRules.decrementQuality(item);
        if (item.quality > QUALITY_MIN_VALUE) {
            updateRules.decrementQuality(item);
        }
    }
}
