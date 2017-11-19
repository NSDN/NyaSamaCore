package club.nsdn.nyasamacore.event;

import club.nsdn.nyasamacore.item.*;
import club.nsdn.nyasamacore.network.NetworkWrapper;
import club.nsdn.nyasamacore.network.TrainPacket;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class ToolHandler {
    private static ToolHandler instance;
    public static TrainPacket controller8Bit;
    public static TrainPacket controller32Bit;

    public static ToolHandler instance() {
        if (instance == null)
            instance = new ToolHandler();
        return instance;
    }

    @SubscribeEvent
    public void onEntityInteract(MinecartInteractEvent event) {
        if (event.getPlayer() instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.getPlayer();
            Entity entity = event.getEntity();

            ItemStack stack = player.getHeldItemMainhand();
            if (stack != null) {
                if (stack.getItem() instanceof ItemToolBase)
                    event.setCanceled(true);

                if (stack.getItem() instanceof ItemNTP8Bit) {
                    if (player.isSneaking()) {
                        controller8Bit = new TrainPacket();
                        NetworkWrapper.packetSender.sendTo(ToolHandler.controller8Bit, player);
                        player.sendMessage(new TextComponentTranslation("info.ntp.cleared"));
                        return;
                    }
                    if (entity instanceof EntityMinecart) {
                        controller8Bit = new TrainPacket(player.getEntityId(), entity.getEntityId(), 0, 5, 0);
                        NetworkWrapper.packetSender.sendTo(controller8Bit, player);
                        player.sendMessage(new TextComponentTranslation("info.ntp.controlled"));
                    }
                }

                else if (stack.getItem() instanceof ItemNTP32Bit) {
                    if (controller32Bit == null) {
                        controller32Bit = new TrainPacket(-1, 0, 5, 0);
                    }
                    controller32Bit.isUnits = true;

                    if (player.isSneaking()) {
                        controller32Bit.trainUnits.clear();
                        controller32Bit.cartID = -1;
                        NetworkWrapper.packetSender.sendTo(controller32Bit, player);
                        player.sendMessage(new TextComponentTranslation("info.ntp.cleared"));
                        return;
                    }
                    if (entity instanceof EntityMinecart) {
                        controller32Bit.cartID = entity.getEntityId();
                        if (!controller32Bit.trainUnits.contains(controller32Bit.cartID)) {
                            controller32Bit.trainUnits.add(controller32Bit.cartID);
                        }
                        controller32Bit.playerID = player.getEntityId();
                        NetworkWrapper.packetSender.sendTo(controller32Bit, player);
                        player.sendMessage(new TextComponentTranslation("info.ntp.controlled"));
                    }
                }
            }
        }
    }

}
