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
            if (!item.name.equals(ITEM_NAME_AGED_BRIE)
                    && !item.name.equals(ITEM_NAME_BACKSTAGE_PASSES)) {
                if (item.quality > 0) {
                    if (!item.name.equals(ITEM_NAME_SULFURAS)) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.name.equals(ITEM_NAME_BACKSTAGE_PASSES)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            }

            if (!item.name.equals(ITEM_NAME_SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals(ITEM_NAME_AGED_BRIE)) {
                    if (!item.name.equals(ITEM_NAME_BACKSTAGE_PASSES)) {
                        if (item.quality > 0) {
                            if (!item.name.equals(ITEM_NAME_SULFURAS)) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = item.quality - item.quality;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }
}