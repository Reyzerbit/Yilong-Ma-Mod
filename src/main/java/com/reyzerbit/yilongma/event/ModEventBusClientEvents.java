package com.reyzerbit.yilongma.event;

import com.reyzerbit.yilongma.YilongMaMod;
import com.reyzerbit.yilongma.client.YilongMaRenderer;
import com.reyzerbit.yilongma.entities.YilongEntities;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = YilongMaMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        EntityRenderers.register(YilongEntities.YILONG_MA.get(), YilongMaRenderer::new);
    }
}