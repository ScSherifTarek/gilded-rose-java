package com.gildedrose.dailyItemUpdaters;

import com.gildedrose.Item;

public class AgedBrieUpdater extends ItemUpdater {
    @Override
    protected void updateQuality(Item item) {
        if(item.quality >= 50) {
            return;
        }

        if (item.sellIn <= 0) {
            item.quality += 2;
        } else {
            item.quality += 1;
        }

        item.quality = Math.min(item.quality, 50);
    }
}
