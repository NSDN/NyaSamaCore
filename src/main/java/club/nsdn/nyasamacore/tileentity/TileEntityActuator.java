package club.nsdn.nyasamacore.tileentity;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import org.thewdj.telecom.IRelay;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class TileEntityActuator extends TileEntityReceiver implements IRelay<TileEntity, TileEntityTransceiver> {

    public String targetX, targetY, targetZ;

    @Override
    public void fromNBT(NBTTagCompound tagCompound) {
        targetX = tagCompound.getString("targetX");
        targetY = tagCompound.getString("targetY");
        targetZ = tagCompound.getString("targetZ");
        super.fromNBT(tagCompound);
    }

    @Override
    public NBTTagCompound toNBT(NBTTagCompound tagCompound) {
        if (getTarget() == null) {
            tagCompound.setString("targetX", "null");
            tagCompound.setString("targetY", "null");
            tagCompound.setString("targetZ", "null");
        } else {
            tagCompound.setString("targetX", targetX);
            tagCompound.setString("targetY", targetY);
            tagCompound.setString("targetZ", targetZ);
        }
        return super.toNBT(tagCompound);
    }

    public TileEntity getTarget() {
        if (targetX.equals("null") || targetY.equals("null") || targetZ.equals("null")) return null;

        TileEntity tileEntity; int x, y, z;
        try {
            x = Integer.parseInt(targetX);
            y = Integer.parseInt(targetY);
            z = Integer.parseInt(targetZ);
        } catch (Exception e) {
            return null;
        }
        tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity == null) return null;

        return tileEntity;
    }

    public void setTarget(TileEntity target) {
        if (target == null) {
            targetX = "null";
            targetY = "null";
            targetZ = "null";
        } else {
            targetX = String.valueOf(target.getPos().getX());
            targetY = String.valueOf(target.getPos().getY());
            targetZ = String.valueOf(target.getPos().getZ());
        }
    }

    public void controlTarget(boolean state) {
        int meta = getTargetMetadata();
        if (meta < 0) return;
        setTargetMetadata((state && (meta & 0x8) == 0) ? meta | 0x8 : meta);
    }

    @MethodsReturnNonnullByDefault
    public int getTargetMetadata() {
        return -1;
    }

    @MethodsReturnNonnullByDefault
    public boolean setTargetMetadata(int meta) {
        return false;
    }

    public TileEntityActuator() {
        targetX = "null";
        targetY = "null";
        targetZ = "null";
    }

}
