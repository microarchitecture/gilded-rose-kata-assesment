package com.gildedrose.strategy;

import com.gildedrose.Item;

/**
 * Defines how an {@link Item} is changed for one day for a particular item kind.
 */
public interface ItemUpdateStrategy {

    /**
     * Applies kind-applicable rules for one day, updating {@link Item#sellIn} and/or {@link Item#quality}.
     *
     * @param item the inventory to update. Must not be {@code null}.
     */
    void update(Item item);
}
