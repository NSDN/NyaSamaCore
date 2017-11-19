package club.nsdn.nyasamacore;

import club.nsdn.nyasamacore.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by drzzm32 on 2017.11.19.
 */
@Mod(modid = NyaSamaCore.MODID, name = NyaSamaCore.NAME, version = NyaSamaCore.VERSION)
public class NyaSamaCore {

    public static final String NAME = "NyaSamaCore";
    public static final String MODID = "nyasamacore";
    public static final String VERSION = "1.0";
    public static Logger log = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = "club.nsdn.nyasamacore.proxy.ClientProxy",
            serverSide = "club.nsdn.nyasamacore.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static NyaSamaCore instance;
    public static NyaSamaCore getInstance() { return instance; }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

}
