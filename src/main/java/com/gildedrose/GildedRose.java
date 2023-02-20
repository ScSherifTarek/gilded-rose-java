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
            }

            if (item.quality > 0) {
                if (!item.name.equals(ITEM_NAME_SULFURAS)) {
                    item.quality = item.quality - 1;
                }
            }

            if (!item.name.equals(ITEM_NAME_SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (item.quality > 0) {
                    if (!item.name.equals(ITEM_NAME_SULFURAS)) {
                        item.quality = item.quality - 1;
                    }
                }
            }
        }
    }

    private void handleAgedBrie(Item item) {
        
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }

        item.sellIn -= 1;
        if (item.sellIn < 0 && item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void handleBackstagePasses(Item item) {
        item.sellIn -= 1;
        if (item.sellIn < 0) {
            item.quality = 0;
        } else if (item.sellIn < 5) {
            item.quality = item.quality + 3;
        } else if (item.sellIn < 10) {
            item.quality = item.quality + 2;
        }

        if (item.quality > 50) {
            item.quality = 50;
        }
    }
}