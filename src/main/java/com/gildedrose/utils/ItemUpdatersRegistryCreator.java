package com.gildedrose.utils;

import com.gildedrose.ItemNames;
import com.gildedrose.ItemUpdatersRegistry;
import com.gildedrose.dailyItemUpdaters.AgedBrieUpdater;
import com.gildedrose.dailyItemUpdaters.BackstagePassUpdater;
import com.gildedrose.dailyItemUpdaters.NormalUpdater;
import com.gildedrose.dailyItemUpdaters.SulfurasUpdater;

public class ItemUpdatersRegistryCreator {
    public static ItemUpdatersRegistry makeDefaultInstance()
    {
        ItemUpdatersRegistry registry = new ItemUpdatersRegistry(new NormalUpdater());
        registry.put(ItemNames.AGED_BRIE.toString(), new AgedBrieUpdater());
        registry.put(ItemNames.SULFURAS.toString(), new SulfurasUpdater());
        registry.put(ItemNames.BACKSTAGE_PASSES.toString(), new BackstagePassUpdater());
        return registry;
    }
}
