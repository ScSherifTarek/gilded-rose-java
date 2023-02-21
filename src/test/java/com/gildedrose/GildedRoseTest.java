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
                new Item(ItemNames.AGED_BRIE.toString(), 1, 5), 6
            ),
            Arguments.of(
                "Aged Brie quality increases by 2 when the sellIn <= 0", 
                new Item(ItemNames.AGED_BRIE.toString(), 0, 5), 7
            ),
            Arguments.of(
                "Aged Brie quality can't increase to more than 50 if started with 50", 
                new Item(ItemNames.AGED_BRIE.toString(), 0, 50), 50
            ),
            Arguments.of(
                "Aged Brie quality can't increase to more than 50 if started with 50 and sellIn > 0", 
                new Item(ItemNames.AGED_BRIE.toString(), 1, 50), 50
            ),
            Arguments.of(
                "Aged Brie quality can't increase to more than 50 if started < 50", 
                new Item(ItemNames.AGED_BRIE.toString(), 0, 49), 50
            ),
            Arguments.of(
                "Sulfuras quality never changes", 
                new Item(ItemNames.SULFURAS.toString(), 0, 80), 80
            ),
            Arguments.of(
                "Backstage passes quality don't change if sellIn > 10 days", 
                new Item(ItemNames.BACKSTAGE_PASSES.toString(), 11, 50), 50
            ),
            Arguments.of(
                "Backstage passes quality increases by 2 if 5 < sellIn <= 10 days: test sellIn = 10", 
                new Item(ItemNames.BACKSTAGE_PASSES.toString(), 10, 30), 32
            ),
            Arguments.of(
                "Backstage passes quality increases by 2 if 5 < sellIn <= 10 days: test sellIn = 6", 
                new Item(ItemNames.BACKSTAGE_PASSES.toString(), 6, 30), 32
            ),
            Arguments.of(
                "Backstage passes quality increases by 3 if 0 < sellIn <= 5 days: test sellIn = 5", 
                new Item(ItemNames.BACKSTAGE_PASSES.toString(), 5, 30), 33
            ),
            Arguments.of(
                "Backstage passes quality increases by 3 if 0 < sellIn <= 5 days: test sellIn = 1", 
                new Item(ItemNames.BACKSTAGE_PASSES.toString(), 1, 30), 33
            ),
            Arguments.of(
                "Backstage passes quality can't be more than 50", 
                new Item(ItemNames.BACKSTAGE_PASSES.toString(), 1, 49), 50
            ),
            Arguments.of(
                "Backstage passes drops tp 0 if sellIn <= 0", 
                new Item(ItemNames.BACKSTAGE_PASSES.toString(), 0, 30), 0
            ),
            Arguments.of(
                "If Aged Brie quality started with more than 50 it should never increases more than", 
                new Item(ItemNames.AGED_BRIE.toString(), 0, 54), 54
            ),
            Arguments.of(
                "If Backstage passes quality started with more than 50 it should never increases more than", 
                new Item(ItemNames.BACKSTAGE_PASSES.toString(), 2, 54), 54
            ),
            Arguments.of(
                "If item quality started with more than 50 it should decrease normally", 
                new Item(ITEM_NAME_NORMAL, 5, 54), 53
            ),
            Arguments.of(
                "If item quality started with less than 0 it shouldn't decrease more than that", 
                new Item(ITEM_NAME_NORMAL, 5, -1), -1
            ),
            Arguments.of(
                "If Aged Brie quality started with less than 0 it should increase normally", 
                new Item(ItemNames.AGED_BRIE.toString(), 5, -1), 0
            )
            ,
            Arguments.of(
                "If Backstage passes quality started with less than 0 it should increase normally", 
                new Item(ItemNames.BACKSTAGE_PASSES.toString(),2, -5), -2
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
        Item item = new Item(ItemNames.SULFURAS.toString(), 5, 10);
        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, item.sellIn);
    }
}
