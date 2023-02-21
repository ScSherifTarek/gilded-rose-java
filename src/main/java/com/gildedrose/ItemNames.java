package com.gildedrose;

public enum ItemNames {
    AGED_BRIE("Aged Brie"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert")
    ;

    private final String text;

    ItemNames(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
