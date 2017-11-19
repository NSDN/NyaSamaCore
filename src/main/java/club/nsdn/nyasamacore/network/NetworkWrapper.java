package club.nsdn.nyasamacore.network;

import club.nsdn.nyasamacore.NyaSamaCore;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class NetworkWrapper {

    public static SimpleNetworkWrapper packetSender;

    public NetworkWrapper(FMLPreInitializationEvent event) {
        packetSender = NetworkRegistry.INSTANCE.newSimpleChannel(NyaSamaCore.MODID);
        packetSender.registerMessage(TrainPacket.PacketStCHandler.class, TrainPacket.class, 0, Side.CLIENT);
        packetSender.registerMessage(TrainPacket.PacketCtSHandler.class, TrainPacket.class, 1, Side.SERVER);
        packetSender.registerMessage(NGTPacket.PacketCtSHandler.class, NGTPacket.class, 2, Side.SERVER);
        packetSender.registerMessage(ParticlePacket.PacketStCHandler.class, ParticlePacket.class, 3, Side.CLIENT);
    }

}
