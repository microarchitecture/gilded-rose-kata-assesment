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

    @Nested
    @DisplayName("Aged Brie suite")
    class AgedBrie {

        @Test
        void qualityIncreasesEachDayBeforeSellByDate() {
        }

        @Test
        void qualityIncreasesEachDayAfterSellByDate() {
        }

        @Test
        void qualityIsFiftyWhenStartingAtFortyNineAfterSellByDate() {
        }

        @Test
        void qualityMaximumValueIsFifty() {
        }
    }

    @Nested
    @DisplayName("Backstage passes")
    class BackstagePasses {

        @Test
        void qualityIncreasesWhenMoreThanTenDaysBeforeConcert() {
        }

        @Test
        void qualityIncreasesTwoTimesWhenTenDaysOrLessBeforeConcert() {
        }

        @Test
        void qualityIncreasesThreeTimesWhenFiveDaysOrLessBeforeConcert() {
        }

        @Test
        void qualityIsZeroAfterConcert() {
        }

        @Test
        void qualityMaximumValueIsFifty() {
        }
    }

    @Nested
    @DisplayName("Sulfuras suite")
    class Sulfuras {

        @Test
        void sellInNeverDecreases() {
        }

        @Test
        void qualityNeverDecreases() {
        }

        @Test
        void qualityMaximumValueIsEighty() {
        }
    }
}
