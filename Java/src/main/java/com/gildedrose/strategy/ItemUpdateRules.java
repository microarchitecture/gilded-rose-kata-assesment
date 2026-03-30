package com.gildedrose.strategy;

import static com.gildedrose.ItemRuleConstants.QUALITY_DEFAULT_CHANGE;
import static com.gildedrose.ItemRuleConstants.QUALITY_MAX_VALUE;
import static com.gildedrose.ItemRuleConstants.QUALITY_MIN_VALUE;

import com.gildedrose.Item;

public final class ItemUpdateRules {

    public void incrementQuality(Item item) {
        if (item.quality < QUALITY_MAX_VALUE) {
            item.quality = item.quality + QUALITY_DEFAULT_CHANGE;
        }
    }

    public void decrementQuality(Item item) {
        if (item.quality > QUALITY_MIN_VALUE) {
            item.quality = item.quality - QUALITY_DEFAULT_CHANGE;
        }
    }

    public void decrementSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    public boolean hasSellInDayPassed(Item item) {
        return item.sellIn < 0;
    }
}
