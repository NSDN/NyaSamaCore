package club.nsdn.nyasamacore.creativetab;

import club.nsdn.nyasamacore.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.*;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class CreativeTabLoader {

    public static CreativeTabs tabNyaSamaBuilding;
    public static CreativeTabs tabNyaSamaCart;
    public static CreativeTabs tabNyaSamaElectricity;
    public static CreativeTabs tabNyaSamaOptics;
    public static CreativeTabs tabNyaSamaRailway;
    public static CreativeTabs tabNyaSamaTelecom;

    public CreativeTabLoader(FMLPreInitializationEvent event) {
        tabNyaSamaBuilding = new CreativeTabs("tabNyaSamaBuilding") {
            @Override
            public ItemStack getTabIconItem() {
                return Item.getItemFromBlock(BlockLoader.blocks.get(new BlockNSBLogo().getRegisterID())).getDefaultInstance();
            }
        };
        tabNyaSamaCart = new CreativeTabs("tabNyaSamaCart") {
            @Override
            public ItemStack getTabIconItem() {
                return Item.getItemFromBlock(BlockLoader.blocks.get(new BlockNSCLogo().getRegisterID())).getDefaultInstance();
            }
        };
        tabNyaSamaElectricity = new CreativeTabs("tabNyaSamaElectricity") {
            @Override
            public ItemStack getTabIconItem() {
                return Item.getItemFromBlock(BlockLoader.blocks.get(new BlockNSELogo().getRegisterID())).getDefaultInstance();
            }
        };
        tabNyaSamaOptics = new CreativeTabs("tabNyaSamaOptics") {
            @Override
            public ItemStack getTabIconItem() {
                return Item.getItemFromBlock(BlockLoader.blocks.get(new BlockNSOLogo().getRegisterID())).getDefaultInstance();
            }
        };
        tabNyaSamaRailway = new CreativeTabs("tabNyaSamaRailway") {
            @Override
            public ItemStack getTabIconItem() {
                return Item.getItemFromBlock(BlockLoader.blocks.get(new BlockNSRLogo().getRegisterID())).getDefaultInstance();
            }
        };
        tabNyaSamaTelecom = new CreativeTabs("tabNyaSamaTelecom") {
            @Override
            public ItemStack getTabIconItem() {
                return Item.getItemFromBlock(BlockLoader.blocks.get(new BlockNSTLogo().getRegisterID())).getDefaultInstance();
            }
        };
    }

}
