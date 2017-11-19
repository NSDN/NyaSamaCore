package club.nsdn.nyasamacore.event;

import net.minecraftforge.common.MinecraftForge;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class EventRegister {

    public static void registerCommon() {
        MinecraftForge.EVENT_BUS.register(ToolHandler.instance());
        MinecraftForge.EVENT_BUS.register(TrainControlServerHandler.instance());
    }

    public static void registerServer() {

    }

    public static void registerClient() {
        MinecraftForge.EVENT_BUS.register(TrainControlClientHandler.instance());
        MinecraftForge.EVENT_BUS.register(ClientTickHandler.instance());
    }

}
