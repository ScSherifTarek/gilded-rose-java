package com.gildedrose.dailyItemUpdaters;

import com.gildedrose.Item;

public class BackstagePassUpdater extends ItemUpdater {
    @Override
    protected void updateQuality(Item item) {
        if(item.quality >= 50) {
            return;
        }

        if (item.sellIn <= 0) {
            item.quality = 0;
        } else if (item.sellIn <= 5) {
            item.quality += 3;
        } else if (item.sellIn <= 10) {
            item.quality += 2;
        }

        item.quality = Math.min(item.quality, 50);
    }
}
