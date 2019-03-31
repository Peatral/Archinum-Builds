package com.peatral.archinumbuilds.item;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.smells.SmellRegistry;
import com.peatral.archinumbuilds.smells.SmellType;
import com.peatral.archinumbuilds.util.Col;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.*;

public class ItemFragranceFlask extends Item {
    private IIcon icon_fluid, icon_flask;

    public ItemFragranceFlask() {
        this.setMaxStackSize(8);
        this.setMaxDamage(0);
    }

    @Override
    public void registerIcons(IIconRegister iIconRegister) {
        this.icon_fluid = iIconRegister.registerIcon(ArchinumBuilds.MODID + ":fragrance_flask_fluid");
        this.icon_flask = iIconRegister.registerIcon(ArchinumBuilds.MODID + ":fragrance_flask_flask");
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool) {
        Map<SmellType, Integer> result = getSmellsFromNBT(itemStack);
        for (SmellType s : result.keySet()) {
            list.add(s.getLocalizedName() + " " + result.get(s));
        }

    }

    public static Map<SmellType, Integer> getSmellsFromNBT(ItemStack itemStack) {
        Map<SmellType, Integer> result = new HashMap<SmellType, Integer>();
        if (itemStack.stackTagCompound == null) {
            itemStack.stackTagCompound = new NBTTagCompound();
            return result;
        }


        NBTTagList tagList = itemStack.stackTagCompound.getTagList("Smells", 10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            String name = tagCompound.getString("Name");
            int amplifier = tagCompound.getInteger("Amplifier");
            if (SmellRegistry.contains(name)) {
                result.put(SmellRegistry.get(name), amplifier);
            }
        }

        return result;
    }

    public static NBTTagCompound setSmellsToNBT(Map<SmellType, Integer> smells) {
        NBTTagCompound tagCompound = new NBTTagCompound();

        NBTTagList tagList = new NBTTagList();
        for (SmellType s : smells.keySet()) {
            NBTTagCompound tagCompound1 = new NBTTagCompound();
            tagCompound1.setString("Name", s.name);
            tagCompound1.setInteger("Amplifier", smells.get(s));
            tagList.appendTag(tagCompound1);

        }
        tagCompound.setTag("Smells", tagList);

        return tagCompound;
    }

    public static ItemStack getMixed(ItemStack itemStack1, ItemStack itemStack2){
        ItemStack stack = new ItemStack(ABItems.fragranceFlask);
        ItemStack i1;
        ItemStack i2;
        if(itemStack1.getItem() == ABItems.fragranceFlask && itemStack2.getItem() == ABItems.fragranceFlask){
            i1 = itemStack1;
            i2 = itemStack2;
        }
        else if(itemStack1.getItem() == ABItems.fragranceFlask && itemStack2.getItem() == ABItems.fragrancePure){
            i1 = itemStack1;
            i2 = itemStack2;
        }
        else if(itemStack1.getItem() == ABItems.fragrancePure && itemStack2.getItem() == ABItems.fragranceFlask){
            i1 = itemStack2;
            i2 = itemStack1;
        }
        else return null;

        NBTTagCompound nbt1 = i1.stackTagCompound;
        if (nbt1 == null) nbt1 = new NBTTagCompound();
        Map<SmellType, Integer> smells1 = new HashMap<SmellType, Integer>();
        if (nbt1.getTag("Smells") != null) {
            smells1 = ItemFragranceFlask.getSmellsFromNBT(i1);
        }

        if(i2.getItem() == ABItems.fragrancePure){
            if(i2.stackTagCompound != null){
                if(i2.stackTagCompound.getString("Smell") != null){
                    SmellType smell = SmellRegistry.get(i2.stackTagCompound.getString("Smell"));
                    if(smells1.containsKey(smell)){
                        smells1.put(smell, smells1.get(smell) + 1);
                    }
                    else {
                        smells1.put(smell, 1);
                    }
                }
            }
        }
        else{
            NBTTagCompound nbt2 = i2.stackTagCompound;
            if (nbt2 == null) nbt2 = new NBTTagCompound();
            Map<SmellType, Integer> smells2 = new HashMap<SmellType, Integer>();
            if (nbt2.getTag("Smells") != null) {
                smells2 = ItemFragranceFlask.getSmellsFromNBT(i2);
            }
            if (smells2.size() > 0) {
                for (SmellType smell : smells2.keySet()) {
                    if (smells1.containsKey(smell)) {
                        smells1.put(smell, smells1.get(smell) + smells2.get(smell));
                    } else {
                        smells1.put(smell, smells2.get(smell));
                    }
                }
            }
        }

        stack.stackTagCompound = ItemFragranceFlask.setSmellsToNBT(smells1);
        return stack;
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int pass) {
        if (pass == 0) {
            Set<SmellType> smells = getSmellsFromNBT(itemStack).keySet();
            if (smells.size() <= 0) return Col.fromHex("#354cc8").toIntFromHex();
            Col[] cols = new Col[smells.size()];
            Iterator<SmellType> itr = smells.iterator();
            int i = 0;
            while (itr.hasNext()) {
                cols[i] = Col.fromHexInDec(itr.next().color);
                i++;
            }
            return Col.getAverageOf(cols).toIntFromHex();
        } else {
            return super.getColorFromItemStack(itemStack, pass);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int meta, int pass) {
        return pass > 0 ? this.icon_flask : this.icon_fluid;
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }


    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }

    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (!entityPlayer.capabilities.isCreativeMode) {
            --itemStack.stackSize;
        }

        if (!world.isRemote) {
            Map<SmellType, Integer> smells = getSmellsFromNBT(itemStack);
            for (SmellType smell : smells.keySet()) {
                smell.applySmell(world, entityPlayer, smells.get(smell));
            }
        }

        if (!entityPlayer.capabilities.isCreativeMode) {
            if (itemStack.stackSize <= 0) {
                return new ItemStack(ABItems.fragranceFlaskEmpty);
            }

            entityPlayer.inventory.addItemStackToInventory(new ItemStack(ABItems.fragranceFlaskEmpty));
        }

        return itemStack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.drink;
    }
}
