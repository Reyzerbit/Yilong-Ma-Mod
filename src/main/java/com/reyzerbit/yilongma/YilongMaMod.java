package com.reyzerbit.yilongma;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.reyzerbit.yilongma.client.YilongMaRenderer;
import com.reyzerbit.yilongma.entities.YilongEntities;
import com.reyzerbit.yilongma.items.YilongItems;
import com.reyzerbit.yilongma.sounds.YilongSounds;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(YilongMaMod.MODID)
public class YilongMaMod
{
    public static final String MODID = "yilongma";
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public YilongMaMod(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);
        
        YilongItems.ITEMS.register(modEventBus);
        YilongEntities.ENTITY_TYPES.register(modEventBus);
        YilongSounds.SOUND_EVENTS.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HI.... EVERYONE... I'M YILONG MA!");
    }
}
