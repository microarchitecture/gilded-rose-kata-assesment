package com.gildedrose.strategy;

import static com.gildedrose.ItemNames.SULFURAS_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class SulfurasUpdateStrategyTest {

    private final SulfurasUpdateStrategy updateStrategy = new SulfurasUpdateStrategy();

    @Test
    void sellInNeverDecreases() {
        Item item = new Item(SULFURAS_NAME, 15, 80);

        updateStrategy.update(item);

        assertEquals(15, item.sellIn);
        assertEquals(80, item.quality);
    }

    @Test
    void qualityNeverDecreases() {
        Item item = new Item(SULFURAS_NAME, -1, 80);

        updateStrategy.update(item);

        assertEquals(-1, item.sellIn);
        assertEquals(80, item.quality);
    }

    @Test
    void qualityConstantValueIsEighty() {
        Item item = new Item(SULFURAS_NAME, 0, 80);

        updateStrategy.update(item);

        assertEquals(0, item.sellIn);
        assertEquals(80, item.quality);
    }
}
