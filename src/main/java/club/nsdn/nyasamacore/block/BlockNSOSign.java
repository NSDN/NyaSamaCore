package club.nsdn.nyasamacore.block;

import club.nsdn.nyasamacore.NyaSamaCore;
import club.nsdn.nyasamacore.creativetab.CreativeTabLoader;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class BlockNSOSign extends BlockGlass implements IBlockBase {

    public BlockNSOSign() {
        super(Material.GLASS, false);
        setUnlocalizedName("NSOSign");
        setRegistryName(NyaSamaCore.MODID, getRegisterID());
        setHardness(2.0F);
        setLightLevel(1);
        setSoundType(SoundType.GLASS);
        setResistance(10.0F);
        setCreativeTab(CreativeTabLoader.tabNyaSamaOptics);
    }

    @Override
    public String getRegisterID() {
        return "nso_sign";
    }
}
