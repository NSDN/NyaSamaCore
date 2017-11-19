package club.nsdn.nyasamacore.item;

import club.nsdn.nyasamacore.NyaSamaCore;
import club.nsdn.nyasamacore.creativetab.CreativeTabLoader;
import club.nsdn.nyasamacore.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.LinkedHashMap;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class ItemNGT extends ItemWritableBook implements IItemBase {

    public ItemNGT() {
        super();
        setUnlocalizedName("ItemNGT");
        setMaxStackSize(1);
        setRegistryName(NyaSamaCore.MODID, getRegisterID());
        setCreativeTab(CreativeTabLoader.tabNyaSamaTelecom);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            runNGT(player.getHeldItem(hand), world, player);
        } else {
            showGUI(player.getHeldItem(hand), player);
        }

        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    public void runNGT(ItemStack itemStack, World world, EntityPlayer player) {
        if (player.isSneaking()) {
            NBTTagList list = Util.getTagListFromNGT(itemStack);
            if (list == null) return;
            String[][] code = NSASM.getCode(list);
            new NSASM(code) {
                @Override
                public World getWorld() {
                    return world;
                }

                @Override
                public double getX() {
                    return player.posX;
                }

                @Override
                public double getY() {
                    return player.posY + 1.5;
                }

                @Override
                public double getZ() {
                    return player.posZ;
                }

                @Override
                public EntityPlayer getPlayer() {
                    return player;
                }

                @Override
                public void loadFunc(LinkedHashMap<String, Operator> funcList) {
                    funcList.put("rnd", ((dst, src) -> {
                        if (src != null) return Result.ERR;
                        if (dst == null) return Result.ERR;
                        if (dst.readOnly) return Result.ERR;

                        dst.type = RegType.INT;
                        dst.data = Math.round(Math.random() * 255);
                        return Result.OK;
                    }));
                }
            }.run();
        }
    }

    @SideOnly(Side.CLIENT)
    public void showGUI(ItemStack itemStack, EntityPlayer player) {
        if (!player.isSneaking()) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiNGT(itemStack));
        }
    }

    @Override
    public String getRegisterID() {
        return "ngt";
    }
}
