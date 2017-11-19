package club.nsdn.nyasamacore.proxy;

import club.nsdn.nyasamacore.block.BlockLoader;
import club.nsdn.nyasamacore.event.EventRegister;
import club.nsdn.nyasamacore.item.ItemLoader;
import club.nsdn.nyasamacore.util.TrainController;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        BlockLoader.preLoadModels();
        ItemLoader.preLoadModels();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        TrainController.KeyInput.registerKeyBindings();
        EventRegister.registerClient();
        BlockLoader.loadModels();
        ItemLoader.loadModels();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }

}
