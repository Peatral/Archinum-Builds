package com.peatral.archinumbuilds.item;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.smells.SmellRegistry;
import com.peatral.archinumbuilds.util.UtilsLang;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemFragrancePure extends Item {

    public IIcon icon_base;

    public ItemFragrancePure() {
    }

    @Override
    public void registerIcons(IIconRegister register) {
        icon_base = register.registerIcon(ArchinumBuilds.MODID + ":fragrance_pure_base");
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List itemList) {
        int i = 0;
        for (String s : SmellRegistry.getSmellMap().keySet()) {
            ItemStack stack = new ItemStack(this, 1, i);
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setString("Smell", s);
            stack.stackTagCompound = tagCompound;
            itemList.add(stack);
            i++;
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return "item.fragrancePure";
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        String s = itemStack.stackTagCompound.getString("Smell");
        if (SmellRegistry.contains(s)) {
            SmellRegistry.get(s).applySmell(world, entityPlayer, 1);
            if (!entityPlayer.capabilities.isCreativeMode) {
                --itemStack.stackSize;
            }
        }

        return itemStack;
    }


    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool) {
        String s = itemStack.stackTagCompound.getString("Smell");
        if (SmellRegistry.contains(s)) {
            list.add(UtilsLang.localize("smell") + ": " + SmellRegistry.get(s).getLocalizedName());
        }

    }

    @Override
    public int getColorFromItemStack(ItemStack item, int pass) {
        String s = item.stackTagCompound.getString("Smell");
        if (SmellRegistry.contains(s)) {
            return SmellRegistry.get(s).color;
        } else {
            return super.getColorFromItemStack(item, pass);
        }
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return icon_base;
    }
}
