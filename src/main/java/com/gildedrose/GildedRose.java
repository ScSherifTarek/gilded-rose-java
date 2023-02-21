package com.gildedrose;

class GildedRose {
    public static final String ITEM_NAME_AGED_BRIE = "Aged Brie";
    public static final String ITEM_NAME_SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String ITEM_NAME_BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if(item.name.equals(ITEM_NAME_AGED_BRIE)) {
                this.handleAgedBrie(item);
                continue;
            } else if (item.name.equals(ITEM_NAME_BACKSTAGE_PASSES)) {
                this.handleBackstagePasses(item);
                continue;
            } else if (item.name.equals(ITEM_NAME_SULFURAS)) {
                continue;
            } else {
                this.handleNormalItem(item);
            }
        }
    }

    private void handleAgedBrie(Item item) {
        if (item.sellIn <= 0) {
            item.quality += 2;
        } else {
            item.quality += 1;
        }

        item.sellIn -= 1;
        item.quality = Math.min(item.quality, 50);
    }

    private void handleBackstagePasses(Item item) {
        if (item.sellIn <= 0) {
            item.quality = 0;
        } else if (item.sellIn <= 5) {
            item.quality += 3;
        } else if (item.sellIn <= 10) {
            item.quality += 2;
        }

        item.sellIn -= 1;
        item.quality = Math.min(item.quality, 50);
    }

    private void handleNormalItem(Item item) {
        if(item.sellIn > 0) {
            item.quality -= 1;
        } else {
            item.quality -= 2;
        }

        item.sellIn -= 1;
        item.quality = Math.max(item.quality, 0);
    }
}