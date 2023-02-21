package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if(item.name.equals(ItemNames.AGED_BRIE.toString())) {
                this.handleAgedBrie(item);
                continue;
            } else if (item.name.equals(ItemNames.BACKSTAGE_PASSES.toString())) {
                this.handleBackstagePasses(item);
                continue;
            } else if (item.name.equals(ItemNames.SULFURAS.toString())) {
                continue;
            } else {
                this.handleNormalItem(item);
            }
        }
    }

    private void handleAgedBrie(Item item) {
        if(item.quality < 50) {
            if (item.sellIn <= 0) {
                item.quality += 2;
            } else {
                item.quality += 1;
            }
            item.quality = Math.min(item.quality, 50);
        }

        item.sellIn -= 1;
    }

    private void handleBackstagePasses(Item item) {
        if(item.quality < 50) {
            if (item.sellIn <= 0) {
                item.quality = 0;
            } else if (item.sellIn <= 5) {
                item.quality += 3;
            } else if (item.sellIn <= 10) {
                item.quality += 2;
            }

            item.quality = Math.min(item.quality, 50);
        }

        item.sellIn -= 1;
    }

    private void handleNormalItem(Item item) {
        if(item.quality > 0) {
            if(item.sellIn > 0) {
                item.quality -= 1;
            } else {
                item.quality -= 2;
            }
            
            item.quality = Math.max(item.quality, 0);
        }

        item.sellIn -= 1;
    }
}