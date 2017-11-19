package club.nsdn.nyasamacore.tileentity;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class TileEntityMultiSender extends TileEntityTransceiver {

    public int targetCount;

    @Override
    public void fromNBT(NBTTagCompound tagCompound) {
        targetCount = tagCompound.getInteger("targetCount");
        super.fromNBT(tagCompound);
    }

    @Override
    public NBTTagCompound toNBT(NBTTagCompound tagCompound) {
        tagCompound.setInteger("targetCount", targetCount);
        return super.toNBT(tagCompound);
    }

    public void incTarget() {
        targetCount += 1;
    }

    public void decTarget() {
        targetCount = targetCount > 0 ? targetCount - 1 : 0;
    }

    @MethodsReturnNonnullByDefault
    public boolean setMetadata(int meta) {
        return false;
    }

    public TileEntityMultiSender() {
        targetCount = 0;
    }

}
