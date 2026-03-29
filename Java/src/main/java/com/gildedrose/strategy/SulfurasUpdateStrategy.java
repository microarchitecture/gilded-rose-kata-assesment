package com.gildedrose.strategy;

import com.gildedrose.Item;

/**
 * Strategy that updates Sulfuras items. Rules are the following:
 * - {@link Item#quality} is 80 and never changes.
 * - {@code sellIn} never changes.
 */
public class SulfurasUpdateStrategy implements ItemUpdateStrategy {

    @Override
    public void update(Item item) {
    }
}
