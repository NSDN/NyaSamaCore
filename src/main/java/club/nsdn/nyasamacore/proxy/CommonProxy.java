package club.nsdn.nyasamacore.proxy;

import club.nsdn.nyasamacore.block.BlockLoader;
import club.nsdn.nyasamacore.creativetab.CreativeTabLoader;
import club.nsdn.nyasamacore.event.EventRegister;
import club.nsdn.nyasamacore.item.ItemLoader;
import club.nsdn.nyasamacore.network.NetworkWrapper;
import net.minecraftforge.fml.common.event.*;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event)
    {
        new NetworkWrapper(event);
        new CreativeTabLoader(event);
        new ItemLoader(event);
        new BlockLoader(event);
    }

    public void init(FMLInitializationEvent event)
    {
        EventRegister.registerCommon();
    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }

}
