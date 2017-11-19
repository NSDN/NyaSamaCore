package org.thewdj.physics;

import net.minecraft.entity.Entity;

/**
 * Created by drzzm32 on 2017.11.19.
 */
public class Vec3 extends net.minecraft.util.math.Vec3d {

    public Vec3(net.minecraft.util.math.Vec3d vec3d) {
        super(vec3d.x, vec3d.y, vec3d.z);
    }

    public Vec3(double x, double y, double z) {
        super(x, y, z);
    }

    public static Vec3 fromEntityPos(Entity entity) {
        return new Vec3(entity.posX, entity.posY, entity.posZ);
    }

    public static Vec3 fromEntityMotion(Entity entity) {
        return new Vec3(entity.motionX, entity.motionY, entity.motionZ);
    }

    public Vec3 addVector(Vec3 vec) {
        return new Vec3(this.x + vec.x, this.y + vec.y, this.z + vec.z);
    }

    public Vec3 dotProduct(double value) {
        return new Vec3(this.x * value, this.y * value, this.z * value);
    }
}
