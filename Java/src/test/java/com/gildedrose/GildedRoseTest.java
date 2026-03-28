package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Nested
    @DisplayName("Normal Item suite")
    class NormalItem {

        @Test
        void qualityDegradesByOneBeforeSellByDate() {
        }

        @Test
        void qualityDegradesTwoTimesFasterAfterSellByDate() {
        }

        @Test
        void qualityMinimumValueIsZero() {
        }

        @Test
        void qualityMaximumValueIsFifty() {
        }
    }
}
