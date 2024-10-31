package com.reyzerbit.yilongma.entities;

import java.util.function.Supplier;

import com.reyzerbit.yilongma.YilongMaMod;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

public class YilongEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, YilongMaMod.MODID);
	
	public static final Supplier<EntityType<YilongMa>> YILONG_MA = ENTITY_TYPES.register(YilongMa.ENTITY_ID, () -> EntityType.Builder.of(YilongMa::new, MobCategory.MISC).build(YilongMa.ENTITY_ID));
}
