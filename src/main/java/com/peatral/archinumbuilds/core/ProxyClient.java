package com.peatral.archinumbuilds.core;

import com.peatral.archinumbuilds.entity.Entity3DMGHook;
import com.peatral.archinumbuilds.entity.EntityEnergyBall;
import com.peatral.archinumbuilds.item.ABItems;
import com.peatral.archinumbuilds.client.Models.ModelArmor;
import com.peatral.archinumbuilds.client.Renders.EntityRenderer3DMGHook;
import com.peatral.archinumbuilds.client.Renders.EntityRendererEnergyBall;
import com.peatral.archinumbuilds.client.Renders.ItemRenderer3DMGSword;
import com.peatral.archinumbuilds.client.Renders.ItemRendererBladeOfOlympus;
import com.peatral.archinumbuilds.client.Renders.ItemRendererExcalibur;
import com.peatral.archinumbuilds.client.Renders.ItemRendererGun;
import com.peatral.archinumbuilds.client.Renders.ItemRendererThorsHammer;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ProxyClient extends Proxy {
	public static ModelArmor armorThorsChest;
	public static ModelArmor armorThors;

	public void registerRenderers(){
		MinecraftForgeClient.registerItemRenderer(ABItems.thorsHammer, new ItemRendererThorsHammer());
		MinecraftForgeClient.registerItemRenderer(ABItems.excalibur, new ItemRendererExcalibur());
		MinecraftForgeClient.registerItemRenderer(ABItems.bladeOlympus, new ItemRendererBladeOfOlympus());
		MinecraftForgeClient.registerItemRenderer(ABItems.gun, new ItemRendererGun());
		MinecraftForgeClient.registerItemRenderer(ABItems.threedmgSword, new ItemRenderer3DMGSword());

		RenderingRegistry.registerEntityRenderingHandler(EntityEnergyBall.class, new EntityRendererEnergyBall());
		RenderingRegistry.registerEntityRenderingHandler(Entity3DMGHook.class, new EntityRenderer3DMGHook());
		
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCatChest.class, specialRenderer);

	}
}
