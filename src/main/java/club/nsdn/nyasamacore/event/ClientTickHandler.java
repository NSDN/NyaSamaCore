package club.nsdn.nyasamacore.event;

import club.nsdn.nyasamacore.util.Util;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class ClientTickHandler {
    private static ClientTickHandler instance;

    public static ClientTickHandler instance() {
        if (instance == null)
            instance = new ClientTickHandler();
        return instance;
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event) {
        Util.setTitle();
    }
}
