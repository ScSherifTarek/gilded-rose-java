package com.gildedrose.dailyItemUpdaters;

import com.gildedrose.Item;

public class NormalUpdater extends ItemUpdater {
    @Override
    protected void updateQuality(Item item) {
        if(item.quality <= 0) {
            return;
        }

        if(item.sellIn > 0) {
            item.quality -= 1;
        } else {
            item.quality -= 2;
        }
        
        item.quality = Math.max(item.quality, 0);
    }
}
