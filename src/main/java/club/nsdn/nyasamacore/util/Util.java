package club.nsdn.nyasamacore.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.Display;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class Util {

    @SideOnly(Side.CLIENT)
    public static void setTitle() {
        String prevTitle = Display.getTitle();
        if (!prevTitle.contains("NSDN-MC")) {
            Display.setTitle(prevTitle + " | using mods by NSDN-MC");
        }
    }

    public static NBTTagList getTagListFromNGT(ItemStack itemStack) {
        if (itemStack == null) return null;
        if (itemStack.getItem() instanceof ItemWritableBook &&
                itemStack.getItem().getClass().getSimpleName().equals("ItemNGT")) {
            if (itemStack.getTagCompound() == null) return null;
            return itemStack.getTagCompound().getTagList("pages", 8);
        }
        return null;
    }

}
