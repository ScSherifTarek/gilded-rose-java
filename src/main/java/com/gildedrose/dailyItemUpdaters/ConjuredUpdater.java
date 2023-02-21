package com.gildedrose.dailyItemUpdaters;

import com.gildedrose.Item;

public class ConjuredUpdater extends ItemUpdater {
    @Override
    protected void updateQuality(Item item) {
        if(item.quality <= 0) {
            return;
        }

        if(item.sellIn > 0) {
            item.quality -= 2;
        } else {
            item.quality -= 4;
        }
        
        item.quality = Math.max(item.quality, 0);
    }
}
