package com.gildedrose;

import java.util.HashMap;
import java.util.Map;

import com.gildedrose.dailyItemUpdaters.ItemUpdater;

public class ItemUpdatersRegistry {
    Map<String, ItemUpdater> updaters;

    ItemUpdater defaultUpdater;
    
    public ItemUpdatersRegistry(ItemUpdater defaultUpdater) {
        this.defaultUpdater = defaultUpdater;
        updaters = new HashMap<String, ItemUpdater>();
    }

    public void put(String itemName, ItemUpdater updater) {
        updaters.put(itemName, updater);
    }

    public ItemUpdater get(String itemName)
    {
        return updaters.getOrDefault(itemName, defaultUpdater);
    }
}
