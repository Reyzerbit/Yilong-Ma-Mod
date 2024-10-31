package com.reyzerbit.yilongma.client;

import com.reyzerbit.yilongma.YilongMaMod;
import com.reyzerbit.yilongma.entities.YilongMa;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class YilongMaRenderer extends MobRenderer<YilongMa, PlayerModel<YilongMa>>
{	
	public YilongMaRenderer(Context context)
	{
		super(context, new PlayerModel<YilongMa>(context.bakeLayer(ModelLayers.PLAYER), false), 0.8f);
	}

	@Override
	public ResourceLocation getTextureLocation(YilongMa entity) { return ResourceLocation.fromNamespaceAndPath(YilongMaMod.MODID, "textures/entity/yilongma.png"); }
}
