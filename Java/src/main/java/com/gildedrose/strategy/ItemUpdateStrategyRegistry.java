package com.gildedrose.strategy;

import static com.gildedrose.ItemNames.AGED_BRIE_NAME;
import static com.gildedrose.ItemNames.BACKSTAGE_PASSES_NAME;
import static com.gildedrose.ItemNames.CONJURED_NAME;
import static com.gildedrose.ItemNames.SULFURAS_NAME;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains mapping between {@link com.gildedrose.Item#name} and {@link ItemUpdateStrategy} implementations.
 */
public final class ItemUpdateStrategyRegistry {

    private final Map<String, ItemUpdateStrategy> strategiesByName;
    private final ItemUpdateStrategy defaultStrategy;

    public ItemUpdateStrategyRegistry() {
        ItemUpdateRules itemUpdateRules = new ItemUpdateRules();
        Map<String, ItemUpdateStrategy> updateStrategies = new HashMap<>();
        defaultStrategy = new NormalUpdateStrategy(itemUpdateRules);
        updateStrategies.put(null, defaultStrategy);
        updateStrategies.put(AGED_BRIE_NAME, new AgedBrieUpdateStrategy(itemUpdateRules));
        updateStrategies.put(BACKSTAGE_PASSES_NAME, new BackstagePassesUpdateStrategy(itemUpdateRules));
        updateStrategies.put(SULFURAS_NAME, new SulfurasUpdateStrategy());
        updateStrategies.put(CONJURED_NAME, new ConjuredUpdateStrategy(itemUpdateRules));
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
