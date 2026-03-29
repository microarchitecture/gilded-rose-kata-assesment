package com.gildedrose;

import com.gildedrose.strategy.ItemUpdateStrategy;
import com.gildedrose.strategy.ItemUpdateStrategyRegistry;

public class GildedRose {

    Item[] items;
    private final ItemUpdateStrategyRegistry updateStrategyRegistry;

    public GildedRose(Item[] items) {
        this(items, new ItemUpdateStrategyRegistry());
    }

    public GildedRose(Item[] items, ItemUpdateStrategyRegistry strategyRegistry) {
        this.items = items;
        this.updateStrategyRegistry = strategyRegistry;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdateStrategy strategy = updateStrategyRegistry.getStrategy(item.name);
            strategy.update(item);
        }
    }
}
