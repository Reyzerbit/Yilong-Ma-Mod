package com.reyzerbit.yilongma.event;

import com.reyzerbit.yilongma.YilongMaMod;
import com.reyzerbit.yilongma.entities.YilongEntities;
import com.reyzerbit.yilongma.entities.YilongMa;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = YilongMaMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents
{
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {
        event.put(YilongEntities.YILONG_MA.get(), YilongMa.createAttributes().build());
    }
}