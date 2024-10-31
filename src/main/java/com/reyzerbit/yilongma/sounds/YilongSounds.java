package com.reyzerbit.yilongma.sounds;

import java.util.function.Supplier;

import com.reyzerbit.yilongma.YilongMaMod;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class YilongSounds
{
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, YilongMaMod.MODID);

	public static final Supplier<SoundEvent> YILONG_PASSIVE_SOUND_EVENT = SOUND_EVENTS.register("yilongma_passive",
		() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(YilongMaMod.MODID, "yilongma_passive")));
	public static final Supplier<SoundEvent> YILONG_TRADE_SOUND_EVENT = SOUND_EVENTS.register("yilongma_trade",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(YilongMaMod.MODID, "yilongma_trade")));
}
