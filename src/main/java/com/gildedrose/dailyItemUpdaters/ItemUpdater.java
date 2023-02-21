package com.gildedrose.dailyItemUpdaters;

import com.gildedrose.Item;

public abstract class ItemUpdater {
    public final void update(Item item) {
        this.updateQuality(item);
        this.updateSellIn(item);
    }

    protected void updateSellIn(Item item) {
        item.sellIn -= 1;
    }
    
    abstract protected void updateQuality(Item item);
}
