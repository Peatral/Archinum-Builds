package com.peatral.archinumbuilds.util;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindings {
	public static KeyBinding keyModeSwitch;
	public static KeyBinding key3DMGLaunch;
	public static KeyBinding key3DMGActivate;
	
	public static void mainRegistry(){
		
		keyModeSwitch = new KeyBinding("key.archinumbuilds.modeSwitch", Keyboard.KEY_M, "key.archinumbuilds.category");
		key3DMGLaunch = new KeyBinding("key.archinumbuilds.3DMGLaunch", Keyboard.KEY_LSHIFT, "key.archinumbuilds.category");
		key3DMGActivate = new KeyBinding("key.archinumbuilds.3DMGActivate", Keyboard.KEY_Q, "key.archinumbuilds.category");
		
		ClientRegistry.registerKeyBinding(keyModeSwitch);
		ClientRegistry.registerKeyBinding(key3DMGLaunch);
		ClientRegistry.registerKeyBinding(key3DMGActivate);
		
	}
}
