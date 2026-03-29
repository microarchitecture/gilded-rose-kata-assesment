package com.gildedrose;

class GildedRose {

    private static final String AGED_BRIE_NAME = "Aged Brie";
    private static final String BACKSTAGE_PASSES_NAME = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS_NAME = "Sulfuras, Hand of Ragnaros";
    private static final int QUALITY_MIN_VALUE = 0;
    private static final int QUALITY_MAX_VALUE = 50;
    private static final int QUALITY_DEFAULT_CHANGE = 1;
    private static final int BACKSTAGE_PASSES_FIRST_MILESTONE = 10;
    private static final int BACKSTAGE_PASSES_SECOND_MILESTONE = 5;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isAgedBrie(item)) {
                incrementQualityByDefault(item);
            } else if (isBackstagePasses(item)) {
                incrementQualityByDefault(item);
                incrementBackstagePassesQuality(item);
            } else {
                decrementQualityByDefaultExceptSulfuras(item);
            }
            decrementSellInExceptSulfuras(item);
            if (hasSellInDayPassed(item)) {
                if (isAgedBrie(item)) {
                    incrementQualityByDefault(item);
                } else if (isBackstagePasses(item)) {
                    item.quality = item.quality - item.quality;
                } else {
                    decrementQualityByDefaultExceptSulfuras(item);
                }
            }
        }
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE_NAME);
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals(BACKSTAGE_PASSES_NAME);
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS_NAME);
    }

    private boolean hasSellInDayPassed(Item item) {
        return item.sellIn < 0;
    }

    private void decrementQualityByDefaultExceptSulfuras(Item item) {
        if (item.quality > QUALITY_MIN_VALUE && !isSulfuras(item)) {
            item.quality = item.quality - QUALITY_DEFAULT_CHANGE;
        }
    }

    private void decrementSellInExceptSulfuras(Item item) {
        if (!isSulfuras(item)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void incrementQualityByDefault(Item item) {
        if (item.quality < QUALITY_MAX_VALUE) {
            item.quality = item.quality + QUALITY_DEFAULT_CHANGE;
        }
    }

    private void incrementBackstagePassesQuality(Item item) {
        if (item.sellIn <= BACKSTAGE_PASSES_FIRST_MILESTONE) {
            if (item.quality < QUALITY_MAX_VALUE) {
                item.quality = item.quality + QUALITY_DEFAULT_CHANGE;
            }
        }
        if (item.sellIn <= BACKSTAGE_PASSES_SECOND_MILESTONE) {
            if (item.quality < QUALITY_MAX_VALUE) {
                item.quality = item.quality + QUALITY_DEFAULT_CHANGE;
            }
        }
    }
}
