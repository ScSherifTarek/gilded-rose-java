package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

class GildedRoseTest {
    private static final String ITEM_NAME_NORMAL = "Normal";

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideScenariosForUpdateQuality")
    void test_item_quality_changes(String scenario, Item item, int expectedQuality)
    {
        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expectedQuality, item.quality);
    }

    private static Stream<Arguments> provideScenariosForUpdateQuality() {
        return Stream.of(
            Arguments.of(
                "Normal item quality degrades when sellIn > 1", 
                new Item(ITEM_NAME_NORMAL, 5, 10), 9
            ),
            Arguments.of(
                "Normal item quality degrades by two when sellIn <= 0", 
                new Item(ITEM_NAME_NORMAL, 0, 10), 8
            ),
            Arguments.of(
                "Quality never goes to negative", 
                new Item(ITEM_NAME_NORMAL, 0, 0), 0
            ),
            Arguments.of(
                "Aged Brie quality increases by 1 when the sellIn > 0", 
                new Item(GildedRose.ITEM_NAME_AGED_BRIE, 1, 5), 6
            ),
            Arguments.of(
                "Aged Brie quality increases by 2 when the sellIn <= 0", 
                new Item(GildedRose.ITEM_NAME_AGED_BRIE, 0, 5), 7
            ),
            Arguments.of(
                "Item quality can't be more than 50", 
                new Item(GildedRose.ITEM_NAME_AGED_BRIE, 0, 50), 50
            ),
            Arguments.of(
                "Sulfuras quality never changes", 
                new Item(GildedRose.ITEM_NAME_SULFURAS, 0, 80), 80
            ),
            Arguments.of(
                "Backstage passes don't change if sellIn > 10 days", 
                new Item(GildedRose.ITEM_NAME_BACKSTAGE_PASSES, 11, 50), 50
            ),
            Arguments.of(
                "Backstage passes increases by 2 if 5 < sellIn <= 10 days: test 10", 
                new Item(GildedRose.ITEM_NAME_BACKSTAGE_PASSES, 10, 30), 32
            ),
            Arguments.of(
                "Backstage passes increases by 2 if 5 < sellIn <= 10 days: test 6", 
                new Item(GildedRose.ITEM_NAME_BACKSTAGE_PASSES, 6, 30), 32
            ),
            Arguments.of(
                "Backstage passes increases by 3 if 0 < sellIn <= 5 days: test 5", 
                new Item(GildedRose.ITEM_NAME_BACKSTAGE_PASSES, 5, 30), 33
            ),
            Arguments.of(
                "Backstage passes increases by 3 if 0 < sellIn <= 5 days: test 1", 
                new Item(GildedRose.ITEM_NAME_BACKSTAGE_PASSES, 1, 30), 33
            ),
            Arguments.of(
                "Backstage passes quality can't be more than 50", 
                new Item(GildedRose.ITEM_NAME_BACKSTAGE_PASSES, 1, 50), 50
            ),
            Arguments.of(
                "Backstage passes drops tp 0 if sellIn <= 0", 
                new Item(GildedRose.ITEM_NAME_BACKSTAGE_PASSES, 0, 30), 0
            )
        );
    }

    @Test
    void normal_item_sell_in_decreases_by_one() {
        Item item = new Item(ITEM_NAME_NORMAL, 5, 10);
        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, item.sellIn);
    }

    @Test
    void sulfuras_sell_in_never_changes() {
        Item item = new Item(GildedRose.ITEM_NAME_SULFURAS, 5, 10);
        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, item.sellIn);
    }
}
