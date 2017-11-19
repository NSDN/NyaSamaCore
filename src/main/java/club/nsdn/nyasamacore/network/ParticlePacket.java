package club.nsdn.nyasamacore.network;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.Charset;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class ParticlePacket implements IMessage {

    public static class PacketStCHandler implements IMessageHandler<ParticlePacket, IMessage> {
        @Override
        public IMessage onMessage(ParticlePacket packet, MessageContext context) {
            EnumParticleTypes type = EnumParticleTypes.getByName(packet.type);
            if (type == null) type = EnumParticleTypes.BARRIER;
            Minecraft.getMinecraft().world.spawnParticle(
                    type,
                    packet.x, packet.y, packet.z,
                    packet.tX, packet.tY, packet.tZ
            );
            return null;
        }
    }

    public String type;
    public double x, y, z;
    public double tX, tY, tZ;

    public ParticlePacket() {
        type = "null";
        x = y = z = 0;
        tX = tY = tZ = 0;
    }

    public ParticlePacket(String type, double x, double y, double z, double tX, double tY, double tZ) {
        this.type = type;
        this.x = x; this.y = y; this.z = z;
        this.tX = tX; this.tY = tY; this.tZ = tZ;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(type.length());
        buf.writeBytes(type.getBytes());
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeDouble(tX);
        buf.writeDouble(tY);
        buf.writeDouble(tZ);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int length = buf.readInt();
        type = buf.readCharSequence(length, Charset.forName("UTF-8")).toString();
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        tX = buf.readDouble();
        tY = buf.readDouble();
        tZ = buf.readDouble();
    }

}
