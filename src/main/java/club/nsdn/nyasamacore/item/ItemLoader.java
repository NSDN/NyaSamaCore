package club.nsdn.nyasamacore.item;

import club.nsdn.nyasamacore.NyaSamaCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.GameData;

import java.util.LinkedHashMap;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class ItemLoader {

    public static LinkedHashMap<String, Item> items;

    private static void register(Item item) {
        GameData.register_impl(item);
    }

    private static void addItem(Item item) {
        if (item instanceof IItemBase) {
            items.put(((IItemBase) item).getRegisterID(), item);
        } else {
            items.put(item.getUnlocalizedName(), item);
        }
    }

    public ItemLoader(FMLPreInitializationEvent event) {
        items = new LinkedHashMap<>();

        addItem(new ItemNTP8Bit());
        addItem(new ItemNTP32Bit());
        addItem(new ItemNGT());

        for (String id : items.keySet()) register(items.get(id));
    }

    @SideOnly(Side.CLIENT)
    public static void preLoadModels() {
        for (String id : items.keySet()) {
            ModelBakery.registerItemVariants(items.get(id), new ResourceLocation(NyaSamaCore.MODID, id));
        }
    }

    @SideOnly(Side.CLIENT)
    public static void loadModels() {
        for (String id : items.keySet()) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(items.get(id), (itemStack) ->
                    new ModelResourceLocation(NyaSamaCore.MODID + ":" + id, "inventory")
            );
        }
    }
}
