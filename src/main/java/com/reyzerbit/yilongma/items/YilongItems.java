package com.reyzerbit.yilongma.items;

import static com.reyzerbit.yilongma.YilongMaMod.MODID;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredRegister.Items;

public class YilongItems
{
    public static final Items ITEMS = DeferredRegister.createItems(MODID);
    
	public static final DeferredItem<Item> DOGE_COIN = ITEMS.registerItem(DogeCoin.ITEM_ID, resourceLocation -> new DogeCoin(new Item.Properties().stacksTo(16).rarity(Rarity.RARE)));
}
