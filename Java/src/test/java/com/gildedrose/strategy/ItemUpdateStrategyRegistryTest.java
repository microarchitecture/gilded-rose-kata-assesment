package com.gildedrose.strategy;

import static com.gildedrose.ItemNames.AGED_BRIE_NAME;
import static com.gildedrose.ItemNames.BACKSTAGE_PASSES_NAME;
import static com.gildedrose.ItemNames.CONJURED_NAME;
import static com.gildedrose.ItemNames.SULFURAS_NAME;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ItemUpdateStrategyRegistryTest {

    private final ItemUpdateStrategyRegistry registry = new ItemUpdateStrategyRegistry();

    @ParameterizedTest(name = "Registry returns {1} strategy by {0} name")
    @MethodSource("strategyByNameCases")
    void returnsStrategyByName(String name, Class<ItemUpdateStrategy> updateStrategyClass) {
        ItemUpdateStrategy itemUpdateStrategy = registry.getStrategy(name);

        assertTrue(itemUpdateStrategy.getClass().isAssignableFrom(updateStrategyClass));
    }

    private static Stream<Arguments> strategyByNameCases() {
        return Stream.of(Arguments.of("Normal Item", NormalUpdateStrategy.class),
            Arguments.of("Non Existing Item", NormalUpdateStrategy.class),
            Arguments.of(null, NormalUpdateStrategy.class),
            Arguments.of(AGED_BRIE_NAME, AgedBrieUpdateStrategy.class),
            Arguments.of(BACKSTAGE_PASSES_NAME, BackstagePassesUpdateStrategy.class),
            Arguments.of(SULFURAS_NAME, SulfurasUpdateStrategy.class),
            Arguments.of(CONJURED_NAME, ConjuredUpdateStrategy.class));
    }
}
