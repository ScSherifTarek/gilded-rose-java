package com.gildedrose;

import com.gildedrose.dailyItemUpdaters.ItemUpdater;

class GildedRose {
    Item[] items;
    ItemUpdatersRegistry updatersRegistry;

    public GildedRose(Item[] items, ItemUpdatersRegistry updatersRegistry) {
        this.items = items;
        this.updatersRegistry = updatersRegistry;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdater updater = updatersRegistry.get(item.name);
            updater.update(item);
        }
    }
}
