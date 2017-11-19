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
public class ItemNTP8Bit extends ItemToolBase {

    public ItemNTP8Bit() {
        super(ToolMaterial.IRON);
        setUnlocalizedName("ItemNTP8Bit");
        setRegistryName(NyaSamaCore.MODID, getRegisterID());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        player.swingArm(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> info, ITooltipFlag flag) {
        info.add(new TextComponentTranslation("info.ntp8.1").getFormattedText());
        info.add(new TextComponentTranslation("info.ntp8.2").getFormattedText());
        info.add(new TextComponentTranslation("info.ntp8.3").getFormattedText());
    }

    @Override
    public String getRegisterID() {
        return "ntp_8";
    }
}
