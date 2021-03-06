package club.nsdn.nyasamacore.util;

import club.nsdn.nyasamacore.network.TrainPacket;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import org.lwjgl.input.Keyboard;
import org.thewdj.physics.Dynamics;

/**
 * Created by drzzm32 on 2017.5.21.
 */
public class TrainController {

    public final static int MaxP = 20;

    @SideOnly(Side.CLIENT)
    public static class KeyInput {
        public final static KeyBinding keyPowerUp = new KeyBinding("ntp.control.power.up", Keyboard.KEY_RIGHT, "ntp.control.title");
        public final static KeyBinding keyPowerDown = new KeyBinding("ntp.control.power.down", Keyboard.KEY_LEFT, "ntp.control.title");
        public final static KeyBinding keyDirUp = new KeyBinding("ntp.control.dir.up", Keyboard.KEY_UP, "ntp.control.title");
        public final static KeyBinding keyDirDown = new KeyBinding("ntp.control.dir.down", Keyboard.KEY_DOWN, "ntp.control.title");
        public final static KeyBinding keyBrakeUp = new KeyBinding("ntp.control.brake.up", Keyboard.KEY_PERIOD, "ntp.control.title");
        public final static KeyBinding keyBrakeDown = new KeyBinding("ntp.control.brake.down", Keyboard.KEY_COMMA, "ntp.control.title");

        public static int DirUP = 0;
        public static int DirDOWN = 0;
        public static int PowerDown = 0;
        public static int PowerUp = 0;
        public static int BrakeDown = 0;
        public static int BrakeUp = 0;

        public static void registerKeyBindings() {
            ClientRegistry.registerKeyBinding(keyPowerUp);
            ClientRegistry.registerKeyBinding(keyPowerDown);
            ClientRegistry.registerKeyBinding(keyDirUp);
            ClientRegistry.registerKeyBinding(keyDirDown);
            ClientRegistry.registerKeyBinding(keyBrakeUp);
            ClientRegistry.registerKeyBinding(keyBrakeDown);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void doControl(TrainPacket train, EntityPlayer player) {
        if (KeyInput.keyDirUp.isPressed() && KeyInput.DirUP == 0) {
            KeyInput.DirUP = 1;
        } else if (KeyInput.keyDirDown.isPressed() && KeyInput.DirDOWN == 0) {
            KeyInput.DirDOWN = 1;
        }
        if (KeyInput.keyPowerDown.isPressed() && KeyInput.PowerDown == 0) {
            KeyInput.PowerDown = 1;
        } else if (KeyInput.keyPowerUp.isPressed() && KeyInput.PowerUp == 0) {
            KeyInput.PowerUp = 1;
        }
        if (KeyInput.keyBrakeDown.isPressed() && KeyInput.BrakeDown == 0) {
            KeyInput.BrakeDown = 1;
        } else if (KeyInput.keyBrakeUp.isPressed() && KeyInput.BrakeUp == 0) {
            KeyInput.BrakeUp = 1;
        }

        if (KeyInput.DirUP == 1) {
            if (train.P > 0) {
                player.sendMessage(new TextComponentTranslation("info.ntp.nonzero"));
            } else {
                if (train.Dir == -1) train.Dir = 0;
                else if (train.Dir == 0) train.Dir = 1;
                player.sendMessage(new TextComponentTranslation("info.ntp.dir", train.Dir));
            }
            KeyInput.DirUP = 2;
        } else if (KeyInput.DirDOWN == 1) {
            if (train.P > 0) {
                player.sendMessage(new TextComponentTranslation("info.ntp.nonzero"));
            } else {
                if (train.Dir == 1) train.Dir = 0;
                else if (train.Dir == 0) train.Dir = -1;
                player.sendMessage(new TextComponentTranslation("info.ntp.dir", train.Dir));
            }
            KeyInput.DirDOWN = 2;
        }

        if (KeyInput.PowerDown == 1) {
            if (train.P > 0) train.P -= 1;
            if (train.P < 0) train.P = 0;
            player.sendMessage(new TextComponentTranslation("info.ntp.power", train.P));
            KeyInput.PowerDown = 2;
        } else if (KeyInput.PowerUp == 1) {
            if (train.P < MaxP) train.P += 1;
            if (train.P > MaxP) train.P = MaxP;
            player.sendMessage(new TextComponentTranslation("info.ntp.power", train.P));
            KeyInput.PowerUp = 2;
        }

        if (KeyInput.BrakeDown == 1) {
            if (train.R < 10) train.R += 1;
            if (train.R > 10) train.R = 10;
            player.sendMessage(new TextComponentTranslation("info.ntp.brake", 10 - train.R));
            KeyInput.BrakeDown = 2;
        } else if (KeyInput.BrakeUp == 1) {
            if (train.R > 1) train.R -= 1;
            if (train.R < 1) train.R = 1;
            player.sendMessage(new TextComponentTranslation("info.ntp.brake", 10 - train.R));
            KeyInput.BrakeUp = 2;
        }

        if (!KeyInput.keyDirUp.isPressed() && KeyInput.DirUP == 2) {
            KeyInput.DirUP = 0;
        } else if (!KeyInput.keyDirDown.isPressed() && KeyInput.DirDOWN == 2) {
            KeyInput.DirDOWN = 0;
        }
        if (!KeyInput.keyPowerDown.isPressed() && KeyInput.PowerDown == 2) {
            KeyInput.PowerDown = 0;
        } else if (!KeyInput.keyPowerUp.isPressed() && KeyInput.PowerUp == 2) {
            KeyInput.PowerUp = 0;
        }
        if (!KeyInput.keyBrakeDown.isPressed() && KeyInput.BrakeDown == 2) {
            KeyInput.BrakeDown = 0;
        } else if (!KeyInput.keyBrakeUp.isPressed() && KeyInput.BrakeUp == 2) {
            KeyInput.BrakeUp = 0;
        }

    }

    private static void calcYaw(TrainPacket train, Entity cart) {
        train.prevYaw = train.Yaw;
        train.Yaw = 180.0 - cart.rotationYaw;
    }

    public static void doMotion(TrainPacket train, Entity cart) {
        calcYaw(train, cart);

        if (train.P > 0 && train.Velocity < 0.005) {
            train.Velocity = 0.005;
        }

        if (train.R > 5) {
            if (train.isUnits) {
                train.nextVelocity = Dynamics.LocoMotions.calcVelocityUp(Math.abs(train.Velocity), 0.1, 1.0, train.P / 10.0, 0.02);
            } else {
                train.nextVelocity = Dynamics.LocoMotions.calcVelocityUp(Math.abs(train.Velocity), 0.1, 1.0, train.P / 50.0, 0.02);
            }

            if (train.Velocity < train.nextVelocity) {
                train.Velocity = train.nextVelocity;
            }
        }

        if (train.R < 10) {
            train.Velocity = Dynamics.LocoMotions.calcVelocityDown(Math.abs(train.Velocity), 0.1, 1.0, 0.1, 1.0, train.R / 10.0, 0.02);
            if (train.Velocity < 0.005) train.Velocity = 0;
        }

        if (train.Dir != 0) {
            cart.motionX = Math.cos(train.Yaw * Math.PI / 180.0) * train.Dir * train.Velocity;
            cart.motionZ = -Math.sin(train.Yaw * Math.PI / 180.0) * train.Dir * train.Velocity;
        } else {
            train.Velocity = Math.abs(cart.motionX / Math.cos(train.Yaw * Math.PI / 180.0));
        }
    }

    public static void doMotionWithAir(TrainPacket train, Entity cart) {
        calcYaw(train, cart);

        if (train.P > 0 && train.Velocity < 0.005) {
            train.Velocity = 0.005;
        }

        if (train.R > 1) {
            train.nextVelocity = Dynamics.LocoMotions.calcVelocityUpWithAir(Math.abs(train.Velocity), 0.1, 1.0, train.P / 10.0, 0.001);

            if (train.Velocity < train.nextVelocity) {
                train.Velocity = train.nextVelocity;
            }
        }

        if (train.R < 10) {
            train.Velocity = Dynamics.LocoMotions.calcVelocityDownWithAir(Math.abs(train.Velocity), 0.1, 1.0, 1.0, 1.0, train.R / 10.0, 0.001);
            if (train.Velocity < 0.005) train.Velocity = 0;
        }

        if (train.Dir != 0) {
            cart.motionX = Math.cos(train.Yaw * Math.PI / 180.0) * train.Dir * train.Velocity;
            cart.motionZ = -Math.sin(train.Yaw * Math.PI / 180.0) * train.Dir * train.Velocity;
        } else {
            train.Velocity = Math.abs(cart.motionX / Math.cos(train.Yaw * Math.PI / 180.0));
            if (Math.abs(cart.motionZ / Math.sin(train.Yaw * Math.PI / 180.0)) > train.Velocity)
                train.Velocity = Math.abs(cart.motionZ / Math.sin(train.Yaw * Math.PI / 180.0));
        }

    }

}
