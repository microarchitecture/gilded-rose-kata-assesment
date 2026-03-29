package com.gildedrose.strategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains mapping between {@link com.gildedrose.Item#name} and {@link ItemUpdateStrategy} implementations.
 */
public final class ItemUpdateStrategyRegistry {

    private static final String AGED_BRIE_NAME = "Aged Brie";
    private static final String BACKSTAGE_PASSES_NAME = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS_NAME = "Sulfuras, Hand of Ragnaros";

    private final Map<String, ItemUpdateStrategy> strategiesByName;
    private final ItemUpdateStrategy defaultStrategy;

    public ItemUpdateStrategyRegistry() {
        Map<String, ItemUpdateStrategy> updateStrategies = new HashMap<>();
        defaultStrategy = new NormalUpdateStrategy();
        updateStrategies.put(null, defaultStrategy);
        updateStrategies.put(AGED_BRIE_NAME, new AgedBrieUpdateStrategy());
        updateStrategies.put(BACKSTAGE_PASSES_NAME, new BackstagePassesUpdateStrategy());
        updateStrategies.put(SULFURAS_NAME, new SulfurasUpdateStrategy());
        this.strategiesByName = Collections.unmodifiableMap(updateStrategies);
    }

    /**
     * Returns the strategy by the {@link com.gildedrose.Item#name}, or the {@link NormalUpdateStrategy} if none is
     * registered.
     */
    public ItemUpdateStrategy getStrategy(String itemName) {
        return strategiesByName.getOrDefault(itemName, defaultStrategy);
    }
}
