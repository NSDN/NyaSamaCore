package club.nsdn.nyasamacore.network;

import club.nsdn.nyasamacore.NyaSamaCore;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class NGTPacket implements IMessage {

    public static class PacketCtSHandler implements IMessageHandler<NGTPacket, IMessage> {
        @Override
        public IMessage onMessage(NGTPacket packet, MessageContext context) {
            EntityPlayerMP serverPlayer = context.getServerHandler().player;

            NBTTagCompound tagCompound = packet.stack.getTagCompound();
            if (serverPlayer.getHeldItemMainhand() != null)
                serverPlayer.getHeldItemMainhand().setTagCompound(tagCompound);

            return null;
        }
    }

    public ItemStack stack;

    public NGTPacket() {
        stack = null;
    }

    public NGTPacket(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        try {
            (new PacketBuffer(buf)).writeItemStack(stack);
        } catch (Exception e) {
            NyaSamaCore.log.error("Couldn't send NGT info", e);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            stack = (new PacketBuffer(buf)).readItemStack();
        } catch (Exception e) {
            NyaSamaCore.log.error("Couldn't receive NGT info", e);
        }
    }

}
