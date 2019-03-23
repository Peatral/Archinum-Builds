package com.peatral.archinumbuilds.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

public class Vec {
    public static final double maxLenth = 10;

    public double x;
    public double y;
    public double z;

    public Vec(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vec middleVec(Vec vec1, Vec vec2){
        Vec mid = vec2.add(vec1).divide(2);

        return mid;
    }

    public Vec subtract(Vec vec){
        return new Vec(this.x-vec.x, this.y-vec.y, this.z-vec.z);
    }

    public Vec add(Vec vec){
        return new Vec(this.x+vec.x, this.y+vec.y, this.z+vec.z);
    }

    public Vec divide(double val){
        return new Vec(this.x/val, this.y/val, this.z/val);
    }

    public Vec multiply(double val){
        return new Vec(this.x*val, this.y*val, this.z*val);
    }

    public Vec constrain(double val){
        return new Vec(Math.min(this.x, val), Math.min(this.y, val), Math.min(this.z, val));
    }

    public double distanceTo(Vec vec){
        Vec distVec = this.subtract(vec);
        double distXYZ = Math.sqrt(distVec.x*distVec.x + distVec.y*distVec.y + distVec.z*distVec.z);

        return distXYZ;
    }

    public Vec3 toVec3(){
        return Vec3.createVectorHelper(this.x, this.y, this.z);
    }

    public static Vec fromVec3(Vec3 vec){
        return new Vec(vec.xCoord, vec.yCoord, vec.zCoord);
    }

    public static Vec positionVec(Entity entity){
        return new Vec(entity.posX, entity.posY, entity.posZ);
    }

    public static Vec motionVec(Entity entity){
        return new Vec(entity.motionX, entity.motionY, entity.motionZ);
    }

    public static Vec rotationVec(Entity entity) {
        double xzLen = Math.cos(entity.rotationYaw);
        double x = xzLen * Math.cos(entity.rotationPitch);
        double y = Math.sin(entity.rotationPitch);
        double z = xzLen * Math.sin(-entity.rotationYaw);

        return new Vec(x, y, z);
    }

    public static void applyMotion(Entity entity, Vec vec){
        entity.motionX += vec.x;
        entity.motionY += vec.y;
        entity.motionZ += vec.z;
    }

    @Override
    protected Vec clone(){
        return new Vec(this.x, this.y, this.z);
    }

    @Override
    public String toString() {
        return "<"+this.x+","+this.y+"y"+this.z+">";
    }
}
