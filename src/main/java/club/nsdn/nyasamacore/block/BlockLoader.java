package club.nsdn.nyasamacore.block;

import club.nsdn.nyasamacore.NyaSamaCore;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.GameData;

import java.util.LinkedHashMap;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class BlockLoader {

    public static LinkedHashMap<String, Block> blocks;

    private static void register(Block block) {
        GameData.register_impl(block);
        String id = block.getUnlocalizedName();
        if (block.getRegistryName() != null) id = block.getRegistryName().toString();
        Item item = new ItemBlock(block).setRegistryName(id);
        GameData.register_impl(item);
        GameData.getBlockItemMap().put(block, item);
    }

    private static void addBlock(Block block) {
        if (block instanceof IBlockBase) {
            blocks.put(((IBlockBase) block).getRegisterID(), block);
        } else {
            blocks.put(block.getUnlocalizedName(), block);
        }
    }

    public BlockLoader(FMLPreInitializationEvent event) {
        blocks = new LinkedHashMap<>();

        addBlock(new BlockNSDNLogo());

        addBlock(new BlockNSBLogo());
        addBlock(new BlockNSCLogo());
        addBlock(new BlockNSELogo());
        addBlock(new BlockNSOLogo());
        addBlock(new BlockNSRLogo());
        addBlock(new BlockNSTLogo());

        addBlock(new BlockNSESign());
        addBlock(new BlockNSOSign());
        addBlock(new BlockNSRSign());
        addBlock(new BlockNSTSign());

        for (String id : blocks.keySet()) register(blocks.get(id));
    }

    @SideOnly(Side.CLIENT)
    public static void preLoadModels() {
        for (String id : blocks.keySet()) {
            Item item = Item.getItemFromBlock(blocks.get(id));
            if (item == null) item = new ItemBlock(blocks.get(id));
            ModelBakery.registerItemVariants(item, new ResourceLocation(NyaSamaCore.MODID, id));
        }
    }

    @SideOnly(Side.CLIENT)
    public static void loadModels() {
        for (String id : blocks.keySet()) {
            Item item = Item.getItemFromBlock(blocks.get(id));
            if (item == null) item = new ItemBlock(blocks.get(id));
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, (itemStack) ->
                    new ModelResourceLocation(NyaSamaCore.MODID + ":" + id, null)
            );
        }
    }

}