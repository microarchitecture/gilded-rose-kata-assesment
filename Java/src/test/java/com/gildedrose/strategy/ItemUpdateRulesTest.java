package com.gildedrose.strategy;

import static com.gildedrose.ItemNames.AGED_BRIE_NAME;
import static com.gildedrose.ItemRuleConstants.QUALITY_MAX_VALUE;
import static com.gildedrose.ItemRuleConstants.QUALITY_MIN_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gildedrose.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ItemUpdateRulesTest {

    private final ItemUpdateRules rules = new ItemUpdateRules();

    @Nested
    @DisplayName("incrementQuality")
    class IncrementQuality {

        @Test
        void increasesQualityWhenBelowMaxValue() {
            Item item = new Item(AGED_BRIE_NAME, 5, 10);

            rules.incrementQuality(item);

            assertEquals(11, item.quality);
        }

        @Test
        void doesNotIncreaseQualityWhenEqualsMaxValue() {
            Item item = new Item(AGED_BRIE_NAME, 5, QUALITY_MAX_VALUE);

            rules.incrementQuality(item);

            assertEquals(QUALITY_MAX_VALUE, item.quality);
        }
    }

    @Nested
    @DisplayName("decrementQuality")
    class DecrementQuality {

        @Test
        void decreasesQualityWhenAboveMinValue() {
            Item item = new Item(AGED_BRIE_NAME, 5, 10);

            rules.decrementQuality(item);

            assertEquals(9, item.quality);
        }

        @Test
        void doesNotIncreaseQualityWhenEqualsMinValue() {
            Item item = new Item(AGED_BRIE_NAME, 5, QUALITY_MIN_VALUE);

            rules.decrementQuality(item);

            assertEquals(QUALITY_MIN_VALUE, item.quality);
        }
    }

    @Nested
    @DisplayName("decrementSellIn")
    class DecrementSellIn {

        @Test
        void decreasesByOne() {
            Item item = new Item(AGED_BRIE_NAME, 10, 0);

            rules.decrementSellIn(item);

            assertEquals(9, item.sellIn);
        }

        @Test
        void decreasesByOneForZero() {
            Item item = new Item(AGED_BRIE_NAME, 0, 0);

            rules.decrementSellIn(item);

            assertEquals(-1, item.sellIn);
        }

        @Test
        void decreasesByOneForNegative() {
            Item item = new Item(AGED_BRIE_NAME, -1, 0);

            rules.decrementSellIn(item);

            assertEquals(-2, item.sellIn);
        }
    }

    @Nested
    @DisplayName("hasSellInDayPassed")
    class HasSellInDayPassed {

        @Test
        void trueWhenSellInNegative() {
            Item item = new Item(AGED_BRIE_NAME, -1, 0);

            assertTrue(rules.hasSellInDayPassed(item));
        }

        @Test
        void falseWhenSellInZero() {
            Item item = new Item(AGED_BRIE_NAME, 0, 0);

            assertFalse(rules.hasSellInDayPassed(item));
        }

        @Test
        void falseWhenSellInPositive() {
            Item item = new Item(AGED_BRIE_NAME, 1, 0);

            assertFalse(rules.hasSellInDayPassed(item));
        }
    }
}
