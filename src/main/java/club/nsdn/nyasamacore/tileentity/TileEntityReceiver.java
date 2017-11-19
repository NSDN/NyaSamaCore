package club.nsdn.nyasamacore.tileentity;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import org.thewdj.telecom.IReceiver;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class TileEntityReceiver extends TileEntity implements IReceiver<TileEntityTransceiver> {

    public String senderX, senderY, senderZ;

    public TileEntityTransceiver getSender() {
        if (senderX.equals("null") || senderY.equals("null") || senderZ.equals("null")) return null;

        TileEntity tileEntity; int x, y, z;
        try {
            x = Integer.parseInt(senderX);
            y = Integer.parseInt(senderY);
            z = Integer.parseInt(senderZ);
        } catch (Exception e) {
            return null;
        }
        tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity == null) return null;
        if (!(tileEntity instanceof TileEntityTransceiver)) return null;

        return (TileEntityTransceiver) tileEntity;
    }

    public void setSender(TileEntityTransceiver sender) {
        if (sender == null) {
            senderX = "null";
            senderY = "null";
            senderZ = "null";
        } else {
            senderX = String.valueOf(sender.getPos().getX());
            senderY = String.valueOf(sender.getPos().getY());
            senderZ = String.valueOf(sender.getPos().getZ());
        }
    }

    @MethodsReturnNonnullByDefault
    public boolean senderIsPowered() {
        return false;
    }

    public TileEntityReceiver() {
        senderX = "null";
        senderY = "null";
        senderZ = "null";
    }

    public void fromNBT(NBTTagCompound tagCompound) {
        senderX = tagCompound.getString("receiverRailX");
        senderY = tagCompound.getString("receiverRailY");
        senderZ = tagCompound.getString("receiverRailZ");
    }

    public NBTTagCompound toNBT(NBTTagCompound tagCompound) {
        if (getSender() == null) {
            tagCompound.setString("receiverRailX", "null");
            tagCompound.setString("receiverRailY", "null");
            tagCompound.setString("receiverRailZ", "null");
        } else {
            tagCompound.setString("receiverRailX", senderX);
            tagCompound.setString("receiverRailY", senderY);
            tagCompound.setString("receiverRailZ", senderZ);
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

    public void updateTileEntity(TileEntity tileEntity) {
        if (tileEntity == null) return;
        tileEntity.getWorld().markBlockRangeForRenderUpdate(
                tileEntity.getPos(), tileEntity.getPos()
        );
    }

    public void onDestroy() {
        if (getSender() != null) {
            if (getSender() instanceof TileEntityMultiSender) {
                ((TileEntityMultiSender) getSender()).decTarget();
                updateTileEntity(getSender());
            }
        }
    }

}
