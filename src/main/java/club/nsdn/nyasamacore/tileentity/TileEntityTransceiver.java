package club.nsdn.nyasamacore.tileentity;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import org.thewdj.telecom.ITransceiver;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class TileEntityTransceiver extends TileEntity implements ITransceiver<TileEntityTransceiver> {

    public String transceiverX, transceiverY, transceiverZ;

    public TileEntityTransceiver getTransceiver() {
        if (transceiverX.equals("null") || transceiverY.equals("null") || transceiverZ.equals("null")) return null;

        TileEntity tileEntity; int x, y, z;
        try {
            x = Integer.parseInt(transceiverX);
            y = Integer.parseInt(transceiverY);
            z = Integer.parseInt(transceiverZ);
        } catch (Exception e) {
            return null;
        }
        tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity == null) return null;
        if (!(tileEntity instanceof TileEntityTransceiver)) return null;

        return (TileEntityTransceiver) tileEntity;
    }

    public void setTransceiver(TileEntityTransceiver transceiver) {
        if (transceiver == null) {
            transceiverX = "null";
            transceiverY = "null";
            transceiverZ = "null";
        } else {
            transceiverX = String.valueOf(transceiver.pos.getX());
            transceiverY = String.valueOf(transceiver.pos.getY());
            transceiverZ = String.valueOf(transceiver.pos.getZ());
        }
    }

    @MethodsReturnNonnullByDefault
    public boolean transceiverIsPowered() {
        return false;
    }

    public TileEntityTransceiver() {
        transceiverX = "null";
        transceiverY = "null";
        transceiverZ = "null";
    }

    public void fromNBT(NBTTagCompound tagCompound) {
        transceiverX = tagCompound.getString("transceiverRailX");
        transceiverY = tagCompound.getString("transceiverRailY");
        transceiverZ = tagCompound.getString("transceiverRailZ");
    }

    public NBTTagCompound toNBT(NBTTagCompound tagCompound) {
        if (getTransceiver() == null) {
            tagCompound.setString("transceiverRailX", "null");
            tagCompound.setString("transceiverRailY", "null");
            tagCompound.setString("transceiverRailZ", "null");
        } else {
            tagCompound.setString("transceiverRailX", transceiverX);
            tagCompound.setString("transceiverRailY", transceiverY);
            tagCompound.setString("transceiverRailZ", transceiverZ);
        }
        return tagCompound;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        toNBT(tagCompound);
        return super.writeToNBT(tagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        fromNBT(tagCompound);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        toNBT(tagCompound);
        return new SPacketUpdateTileEntity(pos, 1, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet) {
        NBTTagCompound tagCompound = packet.getNbtCompound();
        fromNBT(tagCompound);
    }

}
