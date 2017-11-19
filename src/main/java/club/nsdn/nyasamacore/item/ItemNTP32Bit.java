package club.nsdn.nyasamacore.item;

import club.nsdn.nyasamacore.NyaSamaCore;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class ItemNTP32Bit extends ItemToolBase {

    public ItemNTP32Bit() {
        super(ToolMaterial.IRON);
        setUnlocalizedName("ItemNTP32Bit");
        setRegistryName(NyaSamaCore.MODID, getRegisterID());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        player.swingArm(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> info, ITooltipFlag flag) {
        info.add(new TextComponentTranslation("info.ntp32.1").getFormattedText());
        info.add(new TextComponentTranslation("info.ntp32.2").getFormattedText());
        info.add(new TextComponentTranslation("info.ntp32.3").getFormattedText());
    }

    @Override
    public String getRegisterID() {
        return "ntp_32";
    }
}
